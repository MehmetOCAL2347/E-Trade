package com.backend.ecommerce.Category.webApi;

import com.backend.ecommerce.Category.business.responses.CategoryResponse;
import com.backend.ecommerce.Category.business.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public Flux<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
