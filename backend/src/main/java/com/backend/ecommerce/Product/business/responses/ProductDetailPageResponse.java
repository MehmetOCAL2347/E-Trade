package com.backend.ecommerce.Product.business.responses;

import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDetailPageResponse {

    private String code;
    private String name;
    private double starPoint;
    private int count;
    private double price;
    private PriceType priceType;
    private String categoryName; // CategoryId'den category name getirilir
    private String sellerName; // SellerId'den seller name getirilir
    private List<String> bulletPoints;
    private List<Image> images;
    private List<String> comments;

}
