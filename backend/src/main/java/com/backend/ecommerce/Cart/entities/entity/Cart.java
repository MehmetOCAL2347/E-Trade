package com.backend.ecommerce.Cart.entities.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Entity
@Document(collection = "carts")
@Data
public class Cart{
    @Id
    private Integer cartId;
    private String name;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name="cart_item_junction",
            joinColumns = {@JoinColumn(name="cart_id")},
            inverseJoinColumns = {@JoinColumn(name="cart_item_id")}
    )
    private Set<CartItem> cartItems;
    private Double totalPrice;
    private int totalCount;
    private String tokenSession;

    public Cart(){
        super();
        cartItems = new HashSet<>();
    }

    public Cart(Integer id, String name, Set<CartItem> cartItems, Double totalPrice, int totalCount, String tokenSession){
        super();
        this.cartId= id;
        this.name = name;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
        this.tokenSession = tokenSession;
    }
}
