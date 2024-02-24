package com.backend.ecommerce.Product.startUp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDemoData {

    @EventListener(ApplicationReadyEvent.class)
    public void createDemoData() {
        // TODO-DB bağlantıları yapıldıktan sonra olacak
    }

}
