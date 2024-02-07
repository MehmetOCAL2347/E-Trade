package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    private List<ProductMainPageResponse> allProducts = new ArrayList<>();

    @Override
    public Flux<ProductMainPageResponse> getAllProducts() {
        /*
            Business kuralları konacak
        */

        allProducts.clear();

        for (int i = 0; i < 10; i++) {
            allProducts.add(ProductMainPageResponse.builder()
                    .code("PRD001")
                    .name("Mueller Pro-Series All-in-One, 12 Blade Mandoline Slicer, Vegetable Spiralizer, Cutter, Dicer, Food Chopper, Grater, Kitchen Gadgets Sets with Container")
                    .count(25)
                    .isActive(true)
                    .starPoint(4.5)
                    .price(25.99)
                    .priceType(PriceType.USD)
                    .url("https://m.media-amazon.com/images/W/MEDIAX_849526-T3/images/I/81qc9Uc5JBL.__AC_SX300_SY300_QL70_FMwebp_.jpg")
                    .categoryName("Kategori - 1")
                    .build());
        }

        for (int i = 0; i < 10; i++) {
            allProducts.add(ProductMainPageResponse.builder()
                    .code("PRD002")
                    .name("Mueller Pro-Series All-in-One, 12 Blade Mandoline Slicer, Vegetable Spiralizer, Cutter, Dicer, Food Chopper, Grater, Kitchen Gadgets Sets with Container")
                    .count(15)
                    .isActive(true)
                    .starPoint(3.2)
                    .price(50.99)
                    .priceType(PriceType.USD)
                    .url("https://m.media-amazon.com/images/W/MEDIAX_849526-T3/images/I/81m5GFr6aeL.__AC_SX300_SY300_QL70_FMwebp_.jpg")
                    .categoryName("Kategori - 2")
                    .build());
        }

        for (int i = 0; i < 10; i++) {
            allProducts.add(ProductMainPageResponse.builder()
                    .code("PRD003")
                    .name("VEGGURU Safer Mandoline Food Slicer, Thickness Adjustable Vegetable Chopper, Onion Chopper Potato Slicer Tomato Cutter Dicer, Multifunctional Kithcen Food Fruit Chopper(Blue)")
                    .count(55)
                    .isActive(true)
                    .starPoint(2.7)
                    .price(150.99)
                    .priceType(PriceType.USD)
                    .url("https://m.media-amazon.com/images/W/MEDIAX_849526-T3/images/I/813H7+vbq6L._AC_SY300_SX300_.jpg")
                    .categoryName("Kategori - 3")
                    .build());
        }

        return Flux.fromIterable(allProducts);
    }

    @Override
    public Mono<ProductDetailPageResponse> getProductDetail(String code) {

        List<String> list = new ArrayList<>();
        list.add("Professional Design: Our designers dismantled dozens of kinds of vegetable chopper on the market, and more customers did research, we understand the needs of customers, Designed this all-star vegetable chopper. This vegetable chopper in the major kitchen product exhibition received orders from all over the world, now we put it to the market.");
        list.add("Smart and Effective Design: Safety lock design, can better protect your fingers. High-quality knife holder boxes can be stored, saving more kitchen space. The simplest and most efficient shaft design is our patented, allowing for simpler and less effort disassembly while maintaining better stability. The detachable hand guard is also our highlight, making it easier and cleaner to clean. The Grid is also a snap design for easy and effortless disassembly. A variety of safety signs and instructions, so that you in the use of the process more safe.");
        list.add("Save time and effort: Make a meal for yourself and your family after work , versatile blade design allows you to cut the perfect Onions, tomatoes and peppers to make salsa in a short time, while also handling garlic, ginger, radish, cheese and other various vegetables. Life is always about enjoyment, so owning this vegetable chopper will save your time and enjoy life better for your family");
        list.add("Material safety: Comfortable handle using TPU material, safe and comfortable. 430J2 stainless steel blades are more durable than 304 blades and will not rust. The ABS base is designed to withstand even greater pressure and is also suitable for the upper part of the dishwasher, making cleaning easier. The non-slip silicone ring at the bottom increases friction and is suitable for dry or wet desktops. All materials are BPA-free, so you and your family can use them safely");
        list.add("The perfect kitchen gadget collection (18 in 1) : The vegetable chopper consists of 18 accessories, 2 dicing blades and 6 cutting blades, and comes with 2 knife holder boxes for storage. Offering you a variety of ways to slice, chop, grate and chop, the high-quality stainless steel blades cut perfect Onions, tomatoes and peppers for salsa in no time. 2L containers can hold more vegetables. Safe hand guards make you safer during use, save time and protect you from injury. Save more kitchen space");

        return Mono.just(
                ProductDetailPageResponse
                        .builder()
                        .code(code)
                        .name("Mueller Pro-Series All-in-One, 12 Blade Mandoline Slicer, Vegetable Spiralizer, Cutter, Dicer, Food Chopper, Grater, Kitchen Gadgets Sets with Container")
                        .starPoint(4.5)
                        .count(20)
                        .price(23.99)
                        .priceType(PriceType.USD)
                        .categoryName("Kategori - 1")
                        .sellerName("MANDE")
                        .bulletPoints(list)
                        .build()
        );

    }

    @Override
    public Flux<ProductMainPageResponse> getProductsWithParams(ProductFilterRequest filter) {
        return Flux.fromIterable(allProducts.stream()
                .filter(product -> (filter.getCategoryName() == null || product.getCategoryName().equals(filter.getCategoryName()))
                && (filter.getIsActive() == null || product.getIsActive().equals(filter.getIsActive())))
                .collect(Collectors.toList()));

        /*.filter(product -> (filter.getCategory() == null || product.getCategory().equals(filter.getCategory()))
                && (filter.getPrice() == null || product.getPrice() <= filter.getPrice()))
                .collect(Collectors.toList());*/
    }
}
