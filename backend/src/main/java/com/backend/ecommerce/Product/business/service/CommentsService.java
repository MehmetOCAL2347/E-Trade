package com.backend.ecommerce.Product.business.service;

import java.util.List;

public interface CommentsService {
    String saveComments(List<String> comments, String productId);
}
