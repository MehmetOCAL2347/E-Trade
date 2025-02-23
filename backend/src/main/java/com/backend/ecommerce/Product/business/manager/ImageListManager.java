package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.service.ImageListService;
import com.backend.ecommerce.Product.dataAccess.mongo.ImageListRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.ImageList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageListManager implements ImageListService {

    @Autowired
    private ImageListRepositoryMongo imageListRepositoryMongo;

    @Override
    public Mono<String> saveImage(List<Image> images, String productId) {
        ImageList imageEntity = ImageList.builder()
                .images(images)
                .productId(productId)
                .build();

        return imageListRepositoryMongo.save(imageEntity).map(
                savedImage -> {
                    // TODO return savedImageList, lambda could be change
                    return savedImage.getId();
                })
                .onErrorResume(ex -> {
                    // TODO
                    return Mono.error(new RuntimeException("Error saving images: " + ex.getMessage()));
                });
    }
}
