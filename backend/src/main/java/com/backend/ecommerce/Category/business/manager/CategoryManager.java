package com.backend.ecommerce.Category.business.manager;

import com.backend.ecommerce.Category.business.requests.CategorySaveRequest;
import com.backend.ecommerce.Category.business.responses.AllCategoriesResponse;
import com.backend.ecommerce.Category.business.service.CategoryService;
import com.backend.ecommerce.Category.dataAccess.mongo.CategoryMongoRepository;
import com.backend.ecommerce.Category.entities.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {

    @Autowired
    private CategoryMongoRepository categoryMongoRepository;

    @Override
    public Flux<AllCategoriesResponse> getAllCategories() {

        List<Category> allCategories = categoryMongoRepository.findAll().toStream().toList();
        List<AllCategoriesResponse> responseList = new ArrayList<>();

        for (Category category: allCategories) {
            responseList.add(AllCategoriesResponse.builder().name(category.getName()).build());
        }

        return Flux.fromIterable(responseList);

    }

    @Override
    public ResponseEntity addNewCategory(CategorySaveRequest categorySaveRequest) {
        Category category = new Category();
        category.setName(categorySaveRequest.getName());

        categoryMongoRepository.save(category).block();
        return ResponseEntity.ok("New Category Created");
    }

    @Override
    public String getCategoryId(String categoryName) {
        // TODO-3 - Exception ile burada hata olduğu tespit edilmeli
        Category category = categoryMongoRepository.findByName(categoryName).block();
        if (category != null){
            return category.getId();
        }else {
            return "";
        }
    }

    @Override
    public Boolean isCategoryExist(String categoryName) {
        Category category = categoryMongoRepository.findByName(categoryName).block();
        if (category !=null) {
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * @param categoryName
     * @return
     * Getting categoryId with CategoryName
     * If categoryName is not exist so it will be return "Unknown Category"
     */
    @Override
    public Mono<String> getCategoryIdReactive(String categoryName) {
        return categoryMongoRepository.findByName(categoryName)
                .map(Category::getId)
                .defaultIfEmpty("Unknown Category");
    }

    /**
     *
     * @param categoryId
     * @return
     * Getting categoryName with CategoryId
     * If categoryId is not exist so it will be return "Unknown Category"
     */
    @Override
    public Mono<String> getCategoryName(String categoryId) {
        return categoryMongoRepository.findById(categoryId)
                .map(Category::getName)
                .defaultIfEmpty("Unknown Category");
    }
}
