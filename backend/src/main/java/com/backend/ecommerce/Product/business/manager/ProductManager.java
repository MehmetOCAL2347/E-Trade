package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    @Override
    public Flux<ProductMainPageResponse> getAllProducts() {
        /*
            Business kuralları konacak
        */
        List<ProductMainPageResponse> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(ProductMainPageResponse.builder()
                    .code("PRD001")
                    .name("Mueller Pro-Series All-in-One, 12 Blade Mandoline Slicer, Vegetable Spiralizer, Cutter, Dicer, Food Chopper, Grater, Kitchen Gadgets Sets with Container")
                    .count(25)
                    .isActive(true)
                    .starPoint(4.5)
                    .price(25.99)
                    .priceType(PriceType.USD)
                    .url("https://m.media-amazon.com/images/W/MEDIAX_849526-T3/images/I/81qc9Uc5JBL.__AC_SX300_SY300_QL70_FMwebp_.jpg")
                    .build());
        }

        return Flux.fromIterable(list);
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
                        .categoryName("Home & Kitchen")
                        .sellerName("MANDE")
                        .bulletPoints(list)
                        .build()
        );

    }
}
