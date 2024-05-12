package com.backend.ecommerce.Category.startUp;

import com.backend.ecommerce.Category.business.service.CategoryService;
import com.backend.ecommerce.Category.dataAccess.mongo.CategoryMongoRepository;
import com.backend.ecommerce.Category.entities.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryDemoData {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMongoRepository categoryMongoRepository;

    private final String DEFAULT_CATEGORY_1 = "Home & Kitchen";
    private final String DEFAULT_CATEGORY_2 = "Technology";

    @EventListener(ApplicationReadyEvent.class)
    public void createDemoData() {

        if(!categoryService.isCategoryExist(DEFAULT_CATEGORY_1)){
            categoryMongoRepository.save(new Category(DEFAULT_CATEGORY_1)).block();
        }
        if (!categoryService.isCategoryExist(DEFAULT_CATEGORY_2)){
            categoryMongoRepository.save(new Category(DEFAULT_CATEGORY_2)).block();
        }
    }

}
