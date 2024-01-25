package com.backend.ecommerce.Product.entities.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PriceType {

    USD("Dolar", "$"),
    EUR("Euro", "€"),
    TL("Türk Lirası", "₺");

    private String name;
    private String symbol;

}
