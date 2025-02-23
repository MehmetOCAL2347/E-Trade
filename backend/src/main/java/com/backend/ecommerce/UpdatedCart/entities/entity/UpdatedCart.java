package com.backend.ecommerce.UpdatedCart.entities.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "updated_cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdatedCart {
    private String orderId;
    private String discountCode;
    private List<CartItem> cartItemList;
    private double totalPrice;
    private double totalDiscount;
    private double totalAllPrice;
}
