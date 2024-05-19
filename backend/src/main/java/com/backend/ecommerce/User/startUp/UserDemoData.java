package com.backend.ecommerce.User.startUp;

import com.backend.ecommerce.Cart.business.service.CartService;
import com.backend.ecommerce.Cart.entities.entity.Cart;
import com.backend.ecommerce.User.dataAccess.mongo.RoleRepositoryMongo;
import com.backend.ecommerce.User.dataAccess.mongo.UserRepositoryMongo;
import com.backend.ecommerce.User.entities.entity.Roles;
import com.backend.ecommerce.User.entities.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDemoData {

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;

    @Autowired
    private RoleRepositoryMongo roleRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    // TODO-4 Belki enum bir class ile yönetebiliriz burayı
    private final String DEFAULT_ROLE_1 = "ADMIN";
    private final String DEFAULT_ROLE_2 = "USER";
    private final String DEFAULT_USER_NAME = "admin";
    private final String DEFAULT_USER_PASSWORD = "admin";

    @EventListener(ApplicationReadyEvent.class)
    public void createDemoData() {

        if (roleRepository.findByAuthority(DEFAULT_ROLE_1).isPresent()) return;

        Roles role_1 = roleRepository.save(new Roles(DEFAULT_ROLE_1));
        Roles role_2 = roleRepository.save(new Roles(DEFAULT_ROLE_2));

        Set<Roles> roles = new HashSet<>();
        roles.add(role_1);
        roles.add(role_2);

        userRepositoryMongo.save(
                User.builder()
                        .username(DEFAULT_USER_NAME)
                        .password(passwordEncoder.encode(DEFAULT_USER_PASSWORD))
                        .cartId(cartService.getCartId(new Cart()))
                        .authorities(roles)
                        .email("ocal.mehmet@outlook.com.tr")
                        .resetToken("")
                        .build()
        );
    }

}
