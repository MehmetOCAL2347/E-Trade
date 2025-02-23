package com.backend.ecommerce.Order.webApi;

import com.backend.ecommerce.Order.business.requests.CreateOrderRequest;
import com.backend.ecommerce.Order.business.responses.CreateOrderResponse;
import com.backend.ecommerce.Order.business.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-order")
    public Mono<ResponseEntity<CreateOrderResponse>> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return orderService.createOrder(createOrderRequest);
    }

    @PostMapping("/create-order2")
    public String createOrder2(@RequestBody CreateOrderRequest createOrderRequest){
        System.out.println(createOrderRequest);
        return "createOrderRequest";
    }

    /*
    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return orderService.createOrder(createOrderRequest);
    }
    */

    // OrderId, phone number veya email yada isim soyisimle aratma yapılabilsin

}
