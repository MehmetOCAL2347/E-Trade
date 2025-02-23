package com.backend.ecommerce.Order.entities.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetail {
    @Indexed(unique = true)
    private String orderId;
    private String name;
    private String surName;
    private String email;
    private String phoneNumber;
    private String province;
    private String district;
    private String address;
    private String paymentMethod;
}
