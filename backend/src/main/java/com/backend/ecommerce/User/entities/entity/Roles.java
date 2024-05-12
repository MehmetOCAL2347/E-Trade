package com.backend.ecommerce.User.entities.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "roles")
@Data
@EqualsAndHashCode(of="id")
public class Roles implements GrantedAuthority {

    private String id;
    private String authority;

    public Roles(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.authority;
    }
}
