package com.backend.ecommerce.Order.business.manager;

import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.OrderIdConflict;
import com.backend.ecommerce.Order.business.requests.CreateOrderRequest;
import com.backend.ecommerce.Order.business.responses.CreateOrderResponse;
import com.backend.ecommerce.Order.business.rules.OrderRulesService;
import com.backend.ecommerce.Order.business.service.OrderService;
import com.backend.ecommerce.Order.dataAccess.mongo.OrderRepository;
import com.backend.ecommerce.Order.entities.entity.OrderDetail;
import com.backend.ecommerce.UpdatedCart.business.service.UpdatedCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.ValidationException;
import java.net.URI;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderManager implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderRulesService orderRulesService;
    @Autowired
    private UpdatedCartService updatedCartService;

    @Override
    public Mono<ResponseEntity<CreateOrderResponse>> createOrder(CreateOrderRequest createOrderRequest) {
        return createOrderId()
                .flatMap(orderId -> validateOrder(createOrderRequest) //
                        .then(saveCart(createOrderRequest, orderId))
                        .then(saveOrder(orderId, createOrderRequest))
                        .thenReturn(orderId)
                )
                .map(orderId -> ResponseEntity.ok(new CreateOrderResponse(orderId.toString())))
                .onErrorResume(e -> handleError(e));
    }


    /**
     *
     * @param request
     * @return
     * Check create order request has any empty area or not
     * Order Rules Service >> checkEmptyArea(CreateOrderRequest) method came
     * validateOrder method is using inside createOrder method
     */
    private Mono<Void> validateOrder(CreateOrderRequest request) {
        return orderRulesService.checkEmptyAreas(request);
    }

    /**
     *
     * @param request
     * @param orderId
     * @return
     * For using saving a new cart
     * Updated Cart Service >> saveNewCart method came and needs to CreateOrderRequest and orderId
     */
    private Mono<Void> saveCart(CreateOrderRequest request, String orderId) {
        return updatedCartService.saveNewCart(request, orderId);
    }

    /**
     *
     * @param orderId
     * @param request
     * @return
     * Creating OrderDetail Object from CreateOrderRequest Object and save to DB
     * saveOrder method is using createOrderDetailFromRequest(orderId, CreateOrderRequest)
     * This method is creating OrderDetail object
     */
    private Mono<Void> saveOrder(String orderId, CreateOrderRequest request) {
        OrderDetail orderDetail = createOrderDetailFromRequest(orderId, request);
        return orderRepository.save(orderDetail).then();
    }

    /**
     *
     * @param orderId
     * @param request
     * @return
     * Create OrderDetail object from CreateOrderRequest object
     */
    private OrderDetail createOrderDetailFromRequest(String orderId, CreateOrderRequest request){
        return OrderDetail.builder()
                .orderId(orderId)
                .paymentMethod(request.getPaymentMethod())
                .name(request.getName())
                .surName(request.getSurName())
                .email(request.getEmail())
                .address(request.getAddress())
                .province(request.getProvince())
                .district(request.getDistrict())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    // TODO - Burasını daha farklı yönetebiliriz
    private Mono<ResponseEntity<CreateOrderResponse>> handleError(Throwable e) {
        if (e instanceof OrderIdConflict) {
            return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new CreateOrderResponse("Order ID conflict: " + e.getMessage())));
        } else if (e instanceof ValidationException) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CreateOrderResponse("Validation error: " + e.getMessage())));
        }
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CreateOrderResponse("An unexpected error occurred." + e.getLocalizedMessage())));
    }

    private Mono<String> createOrderId() {
        String createdOrderId = UUID.randomUUID().toString();
        return orderRepository.findByOrderId(createdOrderId)
                .flatMap(order -> {
                    return createOrderId();
                })
                .switchIfEmpty(Mono.just(createdOrderId));
    }
}
