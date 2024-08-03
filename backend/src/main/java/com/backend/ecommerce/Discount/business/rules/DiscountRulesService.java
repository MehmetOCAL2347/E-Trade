package com.backend.ecommerce.Discount.business.rules;

public interface DiscountRulesService {
    void isDiscountExist(String discountCode);
    void isDiscountAlive(String discountCode);

}
