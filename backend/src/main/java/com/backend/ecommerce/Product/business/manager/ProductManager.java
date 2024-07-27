package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Category.business.service.CategoryService;
import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.requests.ProductSaveRequest;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.service.BulletPointsService;
import com.backend.ecommerce.Product.business.service.CommentsService;
import com.backend.ecommerce.Product.business.service.ImageListService;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.Product.dataAccess.mongo.ProductRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import com.backend.ecommerce.Product.entities.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public ResponseEntity<String> saveNewProduct(ProductSaveRequest productSaveRequest) {

        String bulletPointId = bulletPointsService
                .saveBulletPoints(
                        productSaveRequest.getBulletPoints(),
                        productSaveRequest.getCode()
                );

        String commentsId = commentsService
                .saveComments(
                        new ArrayList<>(),
                        productSaveRequest.getCode()
                );

        String imagesId = imageListService
                .saveImage(
                  productSaveRequest.getImages(),
                  productSaveRequest.getCode()
                );


        String categoryId = categoryService.getCategoryId(productSaveRequest.getCategoryName());


        Product product = new Product(
                productSaveRequest.getId(),
                productSaveRequest.getName(),
                productSaveRequest.getSellerId(),
                categoryId,
                productSaveRequest.getCode(),
                productSaveRequest.getCount(),
                productSaveRequest.getIsActive(),
                productSaveRequest.getStarPoint(),
                productSaveRequest.getPrice(),
                productSaveRequest.getPriceType(),
                bulletPointId,
                commentsId,
                imagesId
        );
        productRepositoryMongo.save(product);

        return ResponseEntity.ok("Product Saved Succesfully");

    }

    @Override
    public Mono<Product> getProductWithIdDummy(String id) {
        return Mono.just(productRepositoryMongo.findById(id));
    }

    @Override
    public Double calculateTotalPriceForCart(List<String> productIds) {
        double totalPrice = 0.0;
        for (String productId : productIds) {
            totalPrice+= productRepositoryMongo.findById(productId).getPrice();
        }
        return totalPrice;
    }

    private List<ProductMainPageResponse> allProducts = new ArrayList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void createDemoData() {
        allProducts.clear();

        for (int i = 0; i < 1; i++) {
            allProducts.add(ProductMainPageResponse.builder()
                    .code("PRD001")
                    .name("Mande El Araç Süpürgesi Kablosuz, 21000pa Yüksek Güçlü Emişli Taşınabilir Elektrikli Süpürge")
                    .count(20)
                    .isActive(true)
                    .starPoint(4.5)
                    .price(23.99)
                    .priceType(PriceType.TL)
                    .url("https://m.media-amazon.com/images/I/71moOe5vAiL._AC_SL1500_.jpg")
                    .categoryName("Kategori - 1")
                    .build());
        }

        for (int i = 0; i < 10; i++) {
            allProducts.add(ProductMainPageResponse.builder()
                    .code("PRD002")
                    .name("Ürün-2 Mueller Pro-Series All-in-One, 12 Blade Mandoline Slicer, Vegetable Spiralizer, Cutter, Dicer, Food Chopper, Grater, Kitchen Gadgets Sets with Container")
                    .count(15)
                    .isActive(true)
                    .starPoint(3.2)
                    .price(50.99)
                    .priceType(PriceType.TL)
                    .url("https://freshcart.codescandy.com/assets/images/products/product-img-2.jpg")
                    .categoryName("Kategori - 2")
                    .build());
        }

        for (int i = 0; i < 10; i++) {
            allProducts.add(ProductMainPageResponse.builder()
                    .code("PRD003")
                    .name("Ürün-3 VEGGURU Safer Mandoline Food Slicer, Thickness Adjustable Vegetable Chopper, Onion Chopper Potato Slicer Tomato Cutter Dicer, Multifunctional Kithcen Food Fruit Chopper(Blue)")
                    .count(55)
                    .isActive(true)
                    .starPoint(2.7)
                    .price(150.99)
                    .priceType(PriceType.TL)
                    .url("https://freshcart.codescandy.com/assets/images/products/product-img-3.jpg")
                    .categoryName("Kategori - 3")
                    .build());
        }

        for (int i = 0; i < 4; i++) {
            allProducts.add(ProductMainPageResponse.builder()
                    .code("PRD004")
                    .name("Ürün-4 12 in 1 food chopper, Veggie Chopper With Container, Time-Saving Meal Prep, 8 Blades food chopper with Container - Chopper Vegetable Cutter For Salad and Food")
                    .count(3)
                    .isActive(false)
                    .starPoint(1.2)
                    .price(17.99)
                    .priceType(PriceType.TL)
                    .url("https://freshcart.codescandy.com/assets/images/products/product-img-4.jpg")
                    .categoryName("Kategori - 2")
                    .build());
        }
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
    public Flux<ProductMainPageResponse> getProductsWithParams(ProductFilterRequest filter) {
        return Flux.fromIterable(allProducts.stream()
                .filter(product -> (filter.getCategoryName() == null || filter.getCategoryName().isEmpty() || filter.getCategoryName().contains(product.getCategoryName())))
                .filter(product -> (filter.getIsActive() == null || product.getIsActive().equals(filter.getIsActive())))
                .filter(product -> (filter.getStarPoint() == null || product.getStarPoint() >= filter.getStarPoint()))
                .filter(product -> (filter.getMinPriceValue() == null || product.getPrice() >= filter.getMinPriceValue())) // Add min price filter
                .filter(product -> (filter.getMaxPriceValue() == null || product.getPrice() <= filter.getMaxPriceValue())) // Add max price filter
                .collect(Collectors.toList()));
    }
}
