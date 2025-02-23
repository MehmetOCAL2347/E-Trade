package com.backend.ecommerce.Product.business.rules;

import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.EntityNotFoundException;
import com.backend.ecommerce.Product.dataAccess.mongo.ProductRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.Product;
import com.backend.ecommerce.UpdatedCart.entities.entity.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductRulesManager implements ProductRulesService{

    @Autowired
    private ProductRepositoryMongo productRepositoryMongo;

    @Override
    public Mono<Void> areProductsExist(List<CartItem> cartItemList) {
        return Flux.fromIterable(cartItemList) // Listeyi Flux'a çeviriyoruz
                .flatMap(cartItem ->
                        productRepositoryMongo.findById(cartItem.getId())
                                .switchIfEmpty(Mono.error(new EntityNotFoundException(HttpStatus.NO_CONTENT.value(), "Sepetinizdeki Ürün Bulunamadı")))
                )
                .then(); // Mono<Void> döndürmek için
    }


    @Override
    public void isProductExist(String productId) {
        // TODO
    }
}
