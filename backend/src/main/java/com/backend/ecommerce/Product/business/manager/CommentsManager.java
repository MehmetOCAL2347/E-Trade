package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.service.CommentsService;
import com.backend.ecommerce.Product.dataAccess.mongo.CommentsRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsManager implements CommentsService {

    @Autowired
    private CommentsRepositoryMongo commentsRepositoryMongo;

    @Override
    public Mono<String> saveComments(List<String> comments, String productId) {
        Comments commentEntity = Comments.builder()
                .comments(comments)
                .productId(productId)
                .build();

        return commentsRepositoryMongo.save(commentEntity).map(
                savedComment -> {
                    // TODO return savedCommentId, lambda could be change
                    return savedComment.getId();
                })
                .onErrorResume(ex -> {
                    // TODO Runtime exception could be updated
                    return Mono.error(new RuntimeException("Error saving comment: " + ex.getMessage()));
                });
    }
}
