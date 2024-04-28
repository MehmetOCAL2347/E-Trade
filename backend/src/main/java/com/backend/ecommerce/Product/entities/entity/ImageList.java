package com.backend.ecommerce.Product.entities.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "image_lists")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of="id")
public class ImageList {

    @Id
    private String id;
    private List<Image> images;
    private String productId;
}
