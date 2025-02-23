package com.backend.ecommerce.Order.business.requests;

import com.backend.ecommerce.UpdatedCart.entities.entity.CartItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateOrderRequest {
    private String name;
    private String surName;
    private String email;
    private String phoneNumber;
    private String province;
    private String district;
    private String address;
    private String paymentMethod;
    private String discountCode;
    private List<CartItem> cartItemList;
}
