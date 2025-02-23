package com.backend.ecommerce.UpdatedCart.business.rules;

import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.EntityNotFoundException;
import com.backend.ecommerce.UpdatedCart.dataAccess.mongo.UpdatedCartRepository;
import com.backend.ecommerce.UpdatedCart.entities.entity.UpdatedCart;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdatedCartRulesManager implements UpdatedCartRulesService{

    private UpdatedCartRepository updatedCartRepository;

    @Override
    public Mono<Void> isCartExist(String orderId) {
        String trimmedOrderId = orderId.trim();

        return updatedCartRepository.findByOrderId(trimmedOrderId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(HttpStatus.NOT_FOUND.value(), "Sepete Ürünler Eklenirken Hata Oluştu")))
                .then(); // Mono<Void> döndürmek için
    }

}
