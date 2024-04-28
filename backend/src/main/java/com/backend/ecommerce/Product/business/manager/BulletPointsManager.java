package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.requests.BulletPointListRequest;
import com.backend.ecommerce.Product.business.service.BulletPointsService;
import com.backend.ecommerce.Product.dataAccess.mongo.BulletPointsRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.BulletPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulletPointsManager implements BulletPointsService {

    @Autowired
    private BulletPointsRepositoryMongo bulletPointsRepositoryMongo;

    @Override
    public String saveBulletPoints(List<String> bulletPoints, String productId) {

        BulletPoints bulletPoint = new BulletPoints();
        bulletPoint.setBulletPoints(bulletPoints);
        bulletPoint.setProductId(productId);

        BulletPoints savedObject = bulletPointsRepositoryMongo.save(bulletPoint);
        return savedObject.getBulletId();
    }
}
