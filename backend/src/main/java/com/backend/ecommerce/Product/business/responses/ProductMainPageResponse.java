package com.backend.ecommerce.Product.business.responses;

import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductMainPageResponse {

    /*
        id, description, comments kaldırıldı
        image için sadece ana resim getirilsin şeklinde ayarlancak
     */

    private String name;
    private String sellerId;
    private String categoryId;
    private String brandId;
    private String code;
    private int count;  // Stok sayısı
    private boolean isActive;
    private double starPoint;
    private double price;
    private PriceType priceType;
    private Image images;


}
