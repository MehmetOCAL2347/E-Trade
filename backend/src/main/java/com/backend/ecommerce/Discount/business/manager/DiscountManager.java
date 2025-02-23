package com.backend.ecommerce.Discount.business.manager;

import com.backend.ecommerce.Discount.business.requests.DiscountRequest;
import com.backend.ecommerce.Discount.business.responses.DiscountResponse;
import com.backend.ecommerce.Discount.business.rules.DiscountRulesService;
import com.backend.ecommerce.Discount.business.service.DiscountService;
import com.backend.ecommerce.Discount.dataAccess.mongo.DiscountRepositoryMongo;
import com.backend.ecommerce.Discount.entities.entity.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountManager implements DiscountService {

    @Autowired
    private DiscountRulesService discountRulesService;
    @Autowired
    private DiscountRepositoryMongo discountRepositoryMongo;

    @Override
    public Mono<DiscountResponse> getAndCheckDiscount(DiscountRequest discountRequest) {

        String discountCode = discountRequest.getDiscountCode();
        // Business Rules
        discountRulesService.isDiscountExist(discountCode);
        discountRulesService.isDiscountAlive(discountCode);

        Optional<Discount> discount = discountRepositoryMongo.findByDiscountCode(discountCode);

        return Mono.just(
                DiscountResponse.builder()
                        .discountValue(discount.get().getDiscountValue())
                        .build());
    }

    @Override
    public Integer getDiscountValue(String discountCode) {
        Integer discountValue = 0;
        String trimmedDiscountCode = discountCode.trim();
        if(!Objects.equals(trimmedDiscountCode, "") || !trimmedDiscountCode.isEmpty()) {
            discountRulesService.isDiscountExist(trimmedDiscountCode);
            discountRulesService.isDiscountAlive(trimmedDiscountCode);
            discountValue = discountRepositoryMongo.findByDiscountCode(trimmedDiscountCode).get().getDiscountValue();
        }
        return discountValue;
    }
}
