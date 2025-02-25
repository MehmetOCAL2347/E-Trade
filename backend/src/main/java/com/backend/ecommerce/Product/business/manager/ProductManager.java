package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Category.business.service.CategoryService;
import com.backend.ecommerce.Category.entities.entity.Category;
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
import com.backend.ecommerce.Product.entities.entity.*;
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

    /**
     *
     * @param productCode
     * @return
     * Getting product Details from DB with searching productCode value
     */
    @Override
    public Mono<ProductDetailPageResponse> getProductDetail(String productCode) {

        Mono<BulletPoints> bulletPointsMono = bulletPointsService.findBulletPoint(productCode);
        Mono<ImageList> imageListMono = imageListService.findImageList(productCode);
        Mono<Comments> commentsMono = commentsService.findComments(productCode);
        Mono<Product> productMono = productRepositoryMongo.findByCode(productCode);
        Mono<String> categoryMono = productMono.flatMap(product -> categoryService.getCategoryName(product.getCategoryId()));

        // Mono içerisinde zipleyerek asenkron değerleri gönderip tupple içerisinde kullanabiliriz
        return Mono.zip(productMono, bulletPointsMono, imageListMono, commentsMono, categoryMono)
                .map(tuple -> {
                    Product product = tuple.getT1();
                    BulletPoints bulletPoints = tuple.getT2();
                    ImageList imageList = tuple.getT3();
                    Comments comments = tuple.getT4();
                    String categoryName = tuple.getT5();

                    return ProductDetailPageResponse.builder()
                            .code(product.getCode())
                            .name(product.getName())
                            .starPoint(product.getStarPoint())
                            .count(product.getCount())
                            .price(product.getPrice())
                            .priceType(product.getPriceType())
                            .categoryName(categoryName)
                            .sellerName(product.getSellerId())
                            .bulletPoints(bulletPoints.getBulletPoints())
                            .images(imageList.getImages())
                            .comments(comments.getComments())
                            .build();
                });
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
