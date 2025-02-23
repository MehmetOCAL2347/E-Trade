package com.backend.ecommerce.Category.webApi;

import com.backend.ecommerce.Category.business.requests.CategorySaveRequest;
import com.backend.ecommerce.Category.business.responses.AllCategoriesResponse;
import com.backend.ecommerce.Category.business.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Flux<AllCategoriesResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/addNewCategory")
    public ResponseEntity addNewCategory(@RequestBody CategorySaveRequest categorySaveRequest){
        return categoryService.addNewCategory(categorySaveRequest);
    }

}
