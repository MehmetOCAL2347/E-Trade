package com.backend.ecommerce.Product.webApi;

import com.backend.ecommerce.Product.business.requests.ProductCartPageRequest;
import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.requests.ProductSaveRequest;
import com.backend.ecommerce.Product.business.responses.ProductCartPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductSaveResponse;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.Product.entities.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/pd/{code}")  // TODO - burası dummy sonuç dönüyor
    public Mono<ProductDetailPageResponse> getProductDetail(@PathVariable("code") String code) {
        return productService.getProductDetail(code);
    }

    @GetMapping()
    public Flux<ProductMainPageResponse> getProductsWithParams(@ModelAttribute ProductFilterRequest filter) {
        return productService.getProductsWithParams(filter);
        // Bunu findByDynamicFilters metodu ile yapıcaz
    }

    @PostMapping("/saveNewProduct") // TODO Sadece Admin!!!
    public Mono<ProductSaveResponse> saveNewProduct(@RequestBody ProductSaveRequest productSaveRequest){
        return productService.saveNewProduct(productSaveRequest);
    }

    @PostMapping("/cartDetail")
    public Flux<ProductCartPageResponse> getCartDetails(@RequestBody ProductCartPageRequest productCartPageRequest){
        return productService.getCartDetails(productCartPageRequest);
    }
}
