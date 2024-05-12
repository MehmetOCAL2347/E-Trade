package com.backend.ecommerce.Category.entities.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of="id")
public class Category {

    private String id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
