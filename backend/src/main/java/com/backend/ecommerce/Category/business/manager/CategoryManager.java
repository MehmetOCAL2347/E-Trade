package com.backend.ecommerce.Category.business.manager;

import com.backend.ecommerce.Category.business.responses.CategoryResponse;
import com.backend.ecommerce.Category.business.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {

    @Override
    public Flux<CategoryResponse> getAllCategories() {

        List<CategoryResponse> list = new ArrayList<>();
        CategoryResponse cd001 = CategoryResponse.builder()
                .code("CD001")
                .name("Kategori - 1")
                .build();

        CategoryResponse cd002 = CategoryResponse.builder()
                .code("CD002")
                .name("Kategori - 2")
                .build();

        CategoryResponse cd003 = CategoryResponse.builder()
                .code("CD003")
                .name("Kategori - 3")
                .build();

        list.add(cd001);
        list.add(cd002);
        list.add(cd003);

        return Flux.fromIterable(list);

    }
}
