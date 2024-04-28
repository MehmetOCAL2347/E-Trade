package com.backend.ecommerce.Product.webApi;

import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.requests.ProductSaveRequest;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
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

    @PostMapping("/saveNewProduct") // Sadece Admin!?
    public ResponseEntity<String> saveNewProduct(@RequestBody ProductSaveRequest productSaveRequest){
        return productService.saveNewProduct(productSaveRequest);
    }

    @GetMapping("/getProductWithIdDummy")
    public Mono<Product> getProductWithIdDummy(){
        return productService.getProductWithIdDummy("ID-B0CH8H1TT8");
    }
}
