package com.backend.ecommerce.Product.business.service;

import com.backend.ecommerce.Product.entities.entity.Image;

import java.util.List;

public interface ImageListService {
    String saveImage(List<Image> images, String productId);
}
