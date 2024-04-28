package com.backend.ecommerce.Product.entities.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "bullet_points")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of="bulletId")
public class BulletPoints {

    @Id
    private String bulletId;
    private List<String> bulletPoints;
    private String productId;
}
