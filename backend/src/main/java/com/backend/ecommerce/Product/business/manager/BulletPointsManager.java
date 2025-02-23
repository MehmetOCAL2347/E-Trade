package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.service.BulletPointsService;
import com.backend.ecommerce.Product.dataAccess.mongo.BulletPointsRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.BulletPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulletPointsManager implements BulletPointsService {

    @Autowired
    private BulletPointsRepositoryMongo bulletPointsRepositoryMongo;

    @Override
    public Mono<String> saveBulletPoints(List<String> bulletPoints, String productId) {

        BulletPoints bulletPointsEntity = BulletPoints.builder()
                .productId(productId)
                .bulletPoints(bulletPoints)
                .build();

        return bulletPointsRepositoryMongo.save(bulletPointsEntity)
                .map(savedBulletPoints -> {
                    // Return the productCode after saving
                    return savedBulletPoints.getBulletId();
                })
                .onErrorResume(ex -> {
                    // Handle error if the save operation fails
                    return Mono.error(new RuntimeException("Error saving bullet points: " + ex.getMessage()));
                });
    }

    /*
    @Override
    public String saveBulletPoints(List<String> bulletPoints, String productId) {

        BulletPoints bulletPoint = new BulletPoints();
        bulletPoint.setBulletPoints(bulletPoints);
        bulletPoint.setProductId(productId);

        BulletPoints savedObject = bulletPointsRepositoryMongo.save(bulletPoint);
        return savedObject.getBulletId();
    }

     */
}
