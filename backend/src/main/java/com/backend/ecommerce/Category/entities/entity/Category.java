package com.backend.ecommerce.Category.entities.entity;

import lombok.*;

//@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of="id")
public class Category {

    private String id;
    private String name;
    private String code;

}
