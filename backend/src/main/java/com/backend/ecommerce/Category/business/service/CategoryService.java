package com.backend.ecommerce.Category.business.service;

import com.backend.ecommerce.Category.business.requests.CategorySaveRequest;
import com.backend.ecommerce.Category.business.responses.AllCategoriesResponse;
import com.backend.ecommerce.Category.entities.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<AllCategoriesResponse> getAllCategories();
    ResponseEntity addNewCategory(CategorySaveRequest categorySaveRequest);
    String getCategoryId(String categoryName);
    //void getAllCategories();
    /*String addNewCategory(CategorySaveRequest categorySaveRequest);
    Mono<Category> findCategoryId(String categoryName);*/

}
