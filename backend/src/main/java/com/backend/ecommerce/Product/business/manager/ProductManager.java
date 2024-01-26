package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    @Override
    public Flux<ProductMainPageResponse> getAllProducts() {
        /*
            Business kuralları konacak
        */

        return Flux.just(
                ProductMainPageResponse.builder()
                        .name("25 in 1 vegetable Chopper with container")
                        .sellerId("S1")
                        .categoryId("C1")
                        .brandId("B1")
                        .code("PRD1")
                        .count(20)
                        .isActive(true)
                        .starPoint(4.5)
                        .price(20.99)
                        .priceType(PriceType.USD)
                        .images(Image.builder()
                                .imageType(Image.ImageType.ONE)
                                .url("https://m.media-amazon.com/images/W/MEDIAX_849526-T3/images/I/81qc9Uc5JBL.__AC_SX300_SY300_QL70_FMwebp_.jpg")
                                .build())
                        .build()
        );

    }
}
