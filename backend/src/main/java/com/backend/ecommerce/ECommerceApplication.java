package com.backend.ecommerce;

import com.backend.ecommerce.User.dataAccess.mongo.RoleRepositoryMongo;
import com.backend.ecommerce.User.dataAccess.mongo.UserRepositoryMongo;
import com.backend.ecommerce.User.entities.entity.ApplicationUser;
import com.backend.ecommerce.User.entities.entity.Roles;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ECommerceApplication {

	/*
	    Login olurken aşağıdaki gibi request atarsak bu admin user'a ait
        {
            "username": "admin",
            "password": "password"
        }
	 */

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepositoryMongo roleRepository, UserRepositoryMongo userRepository, PasswordEncoder passwordEncode) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

            Roles adminRole = roleRepository.save(new Roles(0,"ADMIN"));
            roleRepository.save(new Roles(1,"USER"));

            Set<Roles> roles = new HashSet<>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles);

            userRepository.save(admin);
        };
    }

}
