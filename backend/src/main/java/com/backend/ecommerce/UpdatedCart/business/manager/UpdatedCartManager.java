package com.backend.ecommerce.UpdatedCart.business.manager;

import com.backend.ecommerce.Discount.business.service.DiscountService;
import com.backend.ecommerce.Order.business.requests.CreateOrderRequest;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.UpdatedCart.business.rules.UpdatedCartRulesService;
import com.backend.ecommerce.UpdatedCart.business.service.UpdatedCartService;
import com.backend.ecommerce.UpdatedCart.dataAccess.mongo.UpdatedCartRepository;
import com.backend.ecommerce.UpdatedCart.entities.entity.UpdatedCart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class UpdatedCartManager implements UpdatedCartService {

    @Autowired
    private final UpdatedCartRepository updatedCartRepository;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final DiscountService discountService;
    @Autowired
    private final UpdatedCartRulesService updatedCartRulesService;


    @Override
    public Mono<Void> saveNewCart(CreateOrderRequest createOrderRequest, String orderId) {
        return productService.calculateUpdatedCartTotalPrice(createOrderRequest.getCartItemList())
                .flatMap(totalPrice -> {
                    // totalPrice burada hesaplandı, şimdi indirimleri hesaplayalım
                    int discountRatio = discountService.getDiscountValue(createOrderRequest.getDiscountCode().trim());
                    double totalDiscount = calculateTotalDiscount(totalPrice, discountRatio);
                    double totalAllPrice = calculateTotalAllPrice(totalPrice, totalDiscount);

                    // Cart'ı kaydedelim
                    return updatedCartRepository.save(
                            UpdatedCart.builder()
                                    .orderId(orderId)
                                    .cartItemList(createOrderRequest.getCartItemList())
                                    .discountCode(createOrderRequest.getDiscountCode().trim())
                                    .totalPrice(totalPrice)
                                    .totalDiscount(totalDiscount)
                                    .totalAllPrice(totalAllPrice)
                                    .build()
                    );
                })
                .flatMap(savedCart -> updatedCartRulesService.isCartExist(orderId.trim()))
                .then(); // Mono<Void> döndürmek için
    }


    private double calculateTotalDiscount(double totalPrice, int totalDiscount){
        return setScale(3, (totalPrice * totalDiscount) / 100);
    }

    private double calculateTotalAllPrice(double totalPrice, double totalDiscount){
        return setScale(3, totalPrice - totalDiscount);
    }

    private double setScale(int scale, double value){
        BigDecimal bd = new BigDecimal(value).setScale(scale, RoundingMode.HALF_DOWN);
        return bd.doubleValue();
    }
}
