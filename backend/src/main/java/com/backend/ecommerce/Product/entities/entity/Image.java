package com.backend.ecommerce.Product.entities.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private ImageType imageType;
    private String url;

    public enum ImageType {
        ONE, TWO, THEREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN
    }

}
