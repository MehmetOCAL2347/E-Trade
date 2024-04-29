package com.backend.ecommerce.Product.business.requests;

import com.backend.ecommerce.Product.entities.entity.BulletPoints;
import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductSaveRequest {

    private String id;
    private String name;
    private String sellerId;
    private String categoryName;
    private String code;
    private int count;
    private Boolean isActive;
    private Double starPoint;
    private double price;
    private PriceType priceType;
    private List<String> bulletPoints;
    private List<Image> images;

}
