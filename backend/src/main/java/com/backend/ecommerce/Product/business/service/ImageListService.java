package com.backend.ecommerce.Product.business.service;

import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.ImageList;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ImageListService {
    Mono<String> saveImage(List<Image> images, String productId);
    Mono<ImageList> findImageList(String productCode);
}
