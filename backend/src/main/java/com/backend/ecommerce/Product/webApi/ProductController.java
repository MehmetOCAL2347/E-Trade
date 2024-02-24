package com.backend.ecommerce.Product.webApi;

import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    /*@GetMapping()
    public Flux<ProductMainPageResponse> getAllProducts() {
        return productService.getAllProducts();
    }*/

    @GetMapping("/pd/{code}")
    public Mono<ProductDetailPageResponse> getProductDetail(@PathVariable("code") String code) {
        return productService.getProductDetail(code);
    }

    @GetMapping()
    public Flux<ProductMainPageResponse> getProductsWithParams(@ModelAttribute ProductFilterRequest filter) {
        return productService.getProductsWithParams(filter);
    }
}
