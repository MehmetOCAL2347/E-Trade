package com.backend.ecommerce.Category.business.service;

import com.backend.ecommerce.Category.business.responses.CategoryResponse;
import reactor.core.publisher.Flux;

public interface CategoryService {

    Flux<CategoryResponse> getAllCategories();

}
