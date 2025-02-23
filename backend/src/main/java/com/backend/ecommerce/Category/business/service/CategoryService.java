package com.backend.ecommerce.Category.business.service;

import com.backend.ecommerce.Category.business.requests.CategorySaveRequest;
import com.backend.ecommerce.Category.business.responses.AllCategoriesResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<AllCategoriesResponse> getAllCategories();
    Mono<String> getCategoryName(String categoryId);
    Mono<String> getCategoryIdReactive(String categoryName);

    ResponseEntity addNewCategory(CategorySaveRequest categorySaveRequest);
    String getCategoryId(String categoryName);
    Boolean isCategoryExist(String categoryName);



}
