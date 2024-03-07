package com.backend.ecommerce.User.business.responses;

import com.backend.ecommerce.User.entities.entity.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private ApplicationUser user;
    private String jwt;
}
