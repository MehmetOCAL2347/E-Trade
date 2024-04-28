package com.backend.ecommerce.Product.business.service;

import java.util.List;

public interface BulletPointsService {
    String saveBulletPoints(List<String> bulletPoints, String productId);
}
