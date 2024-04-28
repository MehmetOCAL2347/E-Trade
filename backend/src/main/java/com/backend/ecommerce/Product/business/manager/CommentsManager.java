package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.service.CommentsService;
import com.backend.ecommerce.Product.dataAccess.mongo.CommentsRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsManager implements CommentsService {

    @Autowired
    private CommentsRepositoryMongo commentsRepositoryMongo;

    @Override
    public String saveComments(List<String> comments, String productId) {

        Comments comment = new Comments();
        comment.setComments(comments);
        comment.setProductId(productId);

        Comments savedComments = commentsRepositoryMongo.save(comment);

        return savedComments.getId();

    }
}
