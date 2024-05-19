package com.backend.ecommerce.User.entities.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    private String id;
    private String username;
    private String password;
    private String cartId;
    private Set<Roles> authorities; // List olmaz mı?
    private String email;
    private String resetToken;

    // email ve resetToken eklencek

    // Lombok ile yapılamaz mı?
    public User(String username, String password, String cartId, Set<Roles> authorities, String email) {
        this.username = username;
        this.password = password;
        this.cartId = cartId;
        this.authorities = authorities;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return (Collection<? extends GrantedAuthority>) this.authorities;
    }

    /* If you want account locking capabilities create variables and ways to set them for the methods below */
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
