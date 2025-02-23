package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Category.business.service.CategoryService;
import com.backend.ecommerce.Product.business.requests.ProductCartPageRequest;
import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.requests.ProductSaveRequest;
import com.backend.ecommerce.Product.business.responses.ProductCartPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductSaveResponse;
import com.backend.ecommerce.Product.business.rules.ProductRulesService;
import com.backend.ecommerce.Product.business.service.BulletPointsService;
import com.backend.ecommerce.Product.business.service.CommentsService;
import com.backend.ecommerce.Product.business.service.ImageListService;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.Product.dataAccess.mongo.ProductRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import com.backend.ecommerce.Product.entities.entity.Product;
import com.backend.ecommerce.UpdatedCart.entities.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    @Autowired
    private ProductRepositoryMongo productRepositoryMongo;

    @Autowired
    private BulletPointsService bulletPointsService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private ImageListService imageListService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRulesService productRulesService;

    /**
     *
     * @param request
     * @return
     * Save a new product with ProductSaveRequest
     * 1. BulletPoints Object created and save to DB reactively
     * 2. Comments Object created and save to DB reactively
     * 3. ImageList Object created and save to DB reactively
     * 4. Existing CategoryID finding with categoryName reactively
     * Return the value of ProductSaveResponse object
     */
    @Override
    public Mono<ProductSaveResponse> saveNewProduct(ProductSaveRequest request) {
        return createProductCode()
                .flatMap(productCode -> {
                    Mono<String> bulletPointIdMono = bulletPointsService.saveBulletPoints(request.getBulletPoints(), productCode);
                    Mono<String> commentIdMono = commentsService.saveComments(new ArrayList<>(), productCode);
                    Mono<String> imageListIdMono = imageListService.saveImage(request.getImages(), productCode);
                    Mono<String> categoryIdMono = categoryService.getCategoryIdReactive(request.getCategoryName());

                    // Tekrar tekrar flatMap kullanmamak için bu şekilde ziplenerek ilerledik.
                    return Mono.zip(bulletPointIdMono, commentIdMono, imageListIdMono, categoryIdMono)
                            .flatMap(tuple -> {
                                String bulletPointId = tuple.getT1();
                                String commentId = tuple.getT2();
                                String imageListId = tuple.getT3();
                                String categoryId = tuple.getT4();

                                Product product = Product.builder()
                                        .code(productCode)
                                        .categoryId(categoryId)
                                        .bulletPointId(bulletPointId)
                                        .commentsId(commentId)
                                        .imagesId(imageListId)
                                        .name(request.getName())
                                        .sellerId(request.getSellerId())
                                        .priceType(request.getPriceType())
                                        .price(request.getPrice())
                                        .count(request.getCount())
                                        .isActive(request.getIsActive())
                                        .starPoint(request.getStarPoint())
                                        .build();
                                return productRepositoryMongo.save(product);
                            });
                })
                .map(savedProduct -> new ProductSaveResponse(savedProduct.getCode()))
                // TODO burası merkezi bir errorhandling ile yönetilebilir
                .onErrorResume(e -> Mono.error(new RuntimeException("Ürün kaydetme sırasında hata oluştu", e)));
    }

    @Override
    public Mono<Double> calculateTotalPriceForCart(List<CartItem> cartItemList) {
        List<Mono<Double>> priceMonos = cartItemList.stream()
                .map(listItem -> productRepositoryMongo.findById(listItem.getId())
                        .map(product -> product.getPrice()))
                .collect(Collectors.toList());

        return Mono.zip(priceMonos, (Object[] prices) -> {
            double totalPrice = 0.0;
            for (Object price : prices) {
                totalPrice += (Double) price;
            }
            return totalPrice;
        });
    }

    @Override
    public Mono<ProductDetailPageResponse> getProductDetail(String code) {

        List<String> list = new ArrayList<>();
        list.add("Yüksek hızlı fırçasız motor, 120AW / 21000Pa'lık güçlü bir emme kuvveti sağlar");
        list.add("Mande'nin kablosuz araç elektrikli süpürgesi, emsallerimizi 15 dakika kadar aşan, 30 dakikaya varan ultra uzun menzile sahip yepyeni bir çift pil tasarımına sahiptir.");
        list.add("Mande mini araba elektrikli süpürgesi yalnızca 2,6 pound ağırlığındadır ve elde taşınır ve kablosuz bir tasarıma sahiptir");
        list.add("Düz nozullar ve fırçalar boşlukları ve tozu temizler. Çok yüzeyli nozullar evcil hayvan tüylerinin üstesinden gelir.");
        list.add("Araba elektrikli süpürgemiz bir arabayı ve bir odayı 15 dakikada temizleyebilir, temizlik süresini ve sıklığını büyük ölçüde azaltır");

        List<Image> images = new ArrayList<>();
        images.add(new Image(Image.ImageType.ONE, "https://m.media-amazon.com/images/I/71moOe5vAiL._AC_SL1500_.jpg"));
        images.add(new Image(Image.ImageType.TWO, "https://m.media-amazon.com/images/I/719cyx2DOFL._AC_SL1500_.jpg"));
        images.add(new Image(Image.ImageType.THEREE, "https://m.media-amazon.com/images/I/61YrTQGu-XL._AC_SL1500_.jpg"));
        images.add(new Image(Image.ImageType.FOUR, "https://m.media-amazon.com/images/I/71LfR1OqsaL._AC_SL1500_.jpg"));

        return Mono.just(
                ProductDetailPageResponse
                        .builder()
                        .code(code)
                        .name("Mande El Araç Süpürgesi Kablosuz, 21000pa Yüksek Güçlü Emişli Taşınabilir Elektrikli Süpürge")
                        .starPoint(4.5)
                        .count(20)
                        .price(23.99)
                        .priceType(PriceType.TL)
                        .categoryName("Kategori - 1")
                        .sellerName("MANDE")
                        .bulletPoints(list)
                        .images(images)
                        .build()
        );

    }

    @Override
    public Flux<ProductCartPageResponse> getCartDetails(ProductCartPageRequest productCartPageRequest) {
        // TODO Liste boş ise ona göre bir exception >> Business rule
        return calculateToProductCartPageResponse(productCartPageRequest.getCodes());
    }


    /**
     *
     * @return
     * Creating product code automatically with using UUID
     */
    public Mono<String> createProductCode() {
        String createdProductCode = UUID.randomUUID().toString();
        return productRepositoryMongo.findByCode(createdProductCode)
                .flatMap(product -> {
                    // Eğer unique değilse aşağıdaki retry sayesinde 10 kere max çalışır
                    return createProductCode();
                })
                .switchIfEmpty(Mono.just(createdProductCode)).retry(10);
    }

    /**
     * @param filter
     * @return Get All Products from DB with some query params
     */
    @Override
    public Flux<ProductMainPageResponse> getProductsWithParams(ProductFilterRequest filter) {
        return productRepositoryMongo.findAll()
                .flatMap(product -> categoryService.getCategoryName(product.getCategoryId())
                        .map(categoryName -> new AbstractMap.SimpleEntry<>(product, categoryName)))
                .filter(entry -> filter.getCategoryName() == null || filter.getCategoryName().isEmpty() || filter.getCategoryName().contains(entry.getValue()))
                .map(entry -> entry.getKey())
                .filter(product -> filter.getIsActive() == null || product.getIsActive().equals(filter.getIsActive()))
                .filter(product -> filter.getStarPoint() == null || product.getStarPoint() >= filter.getStarPoint())
                .filter(product -> filter.getMinPriceValue() == null || product.getPrice() >= filter.getMinPriceValue())
                .filter(product -> filter.getMaxPriceValue() == null || product.getPrice() <= filter.getMaxPriceValue())
                .flatMap(this::convertToResponse);
    }

    /**
     * @param cartItemList
     * @return To create Cart total Price Method
     */
    @Override
    public Mono<Double> calculateUpdatedCartTotalPrice(List<CartItem> cartItemList) {
        return Flux.fromIterable(cartItemList)
                .flatMap(cartItem -> productRepositoryMongo.findById(cartItem.getId())
                        .map(product -> product.getPrice() * cartItem.getQuantity()) // Ürün fiyatı ile çarp
                )
                .reduce(0.0, Double::sum); // Toplam fiyatı hesapla
    }

    /**
     * @param product
     * @return Mapping from Product to ProductMainPageResponse
     */
    private Mono<ProductMainPageResponse> convertToResponse(Product product) {
        return categoryService.getCategoryName(product.getCategoryId())
                .map(categoryName -> ProductMainPageResponse.builder()
                        .code(product.getCode())
                        .name(product.getName())
                        .url("https://freshcart.codescandy.com/assets/images/products/product-img-4.jpg") // TODO
                        .priceType(product.getPriceType())
                        .price(product.getPrice())
                        .count(product.getCount())
                        .categoryName(categoryName)
                        .isActive(product.getIsActive())
                        .starPoint(product.getStarPoint())
                        .build()
                );
    }

    /**
     * @param codes
     * @return Getting Product from DB and convert to ProductCartPageResponse Object
     */
    private Flux<ProductCartPageResponse> calculateToProductCartPageResponse(List<String> codes) {
        return productRepositoryMongo.findByCodeIn(codes)
                .map(product -> ProductCartPageResponse.builder()
                        .code(product.getCode())
                        .name(product.getName())
                        .price(product.getPrice())
                        .priceType(product.getPriceType())
                        .url("https://via.placeholder.com/100") // TODO
                        .build());
    }
}
