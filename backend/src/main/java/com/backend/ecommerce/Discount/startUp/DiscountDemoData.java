package com.backend.ecommerce.Discount.startUp;

import com.backend.ecommerce.Discount.dataAccess.mongo.DiscountRepositoryMongo;
import com.backend.ecommerce.Discount.entities.entity.Discount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscountDemoData {

    @Autowired
    private final DiscountRepositoryMongo discountRepositoryMongo;

    @EventListener(ApplicationReadyEvent.class)
    public void createDemoData() {

        Discount discount = new Discount("DSC001", "HELALOLSUNKAPTAN", 10, true);
        Discount discount1 = new Discount("DSC002", "ESKIBIRKOD", 20, false);
        Discount discount2 = new Discount("DSC003", "AGUSTOSOZEL", 25, true);

        if (discountRepositoryMongo.findByDiscountCode("DSC001").isPresent()) return;
        if (discountRepositoryMongo.findByDiscountCode("DSC002").isPresent()) return;
        if (discountRepositoryMongo.findByDiscountCode("DSC003").isPresent()) return;

        discountRepositoryMongo.save(discount);
        discountRepositoryMongo.save(discount1);
        discountRepositoryMongo.save(discount2);
    }
}
