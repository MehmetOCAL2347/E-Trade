package com.backend.ecommerce.Discount.business.rules;

import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.DiscountNotAlive;
import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.DiscountNotFound;
import com.backend.ecommerce.Discount.dataAccess.mongo.DiscountRepositoryMongo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DiscountRulesManager implements DiscountRulesService{

    private DiscountRepositoryMongo discountRepositoryMongo;

    @Override
    public void isDiscountExist(String discountCode) {
        boolean isDiscountExist = discountRepositoryMongo.findByDiscountCode(discountCode).isPresent();
        if (!isDiscountExist) {
            // Discount Code varmı
            throw new DiscountNotFound(HttpStatus.NOT_FOUND.value(),"Girilen Kupon Geçerli Değildir");
        }
    }

    @Override
    public void isDiscountAlive(String discountCode) {
        boolean isDiscountAlive = discountRepositoryMongo.findByDiscountCode(discountCode).get().isAlive();

        if (!isDiscountAlive) {
            // Discount Alive mı
            throw new DiscountNotAlive(HttpStatus.FORBIDDEN.value(), "Girilen Kuponun Süresi Dolmuştur");
        }
    }
}
