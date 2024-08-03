package com.backend.ecommerce.Discount.webApi;

import com.backend.ecommerce.Discount.business.requests.DiscountRequest;
import com.backend.ecommerce.Discount.business.responses.DiscountResponse;
import com.backend.ecommerce.Discount.business.service.DiscountService;
import com.backend.ecommerce.Product.business.requests.ProductSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping("/query-check-discount")
    public Mono<DiscountResponse> getAndCheckDiscount(@RequestBody DiscountRequest discountRequest){
        return discountService.getAndCheckDiscount(discountRequest);
    }

}
