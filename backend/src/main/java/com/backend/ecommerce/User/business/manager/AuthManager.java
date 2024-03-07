package com.backend.ecommerce.User.business.manager;

import com.backend.ecommerce.User.business.responses.LoginResponseDTO;
import com.backend.ecommerce.User.business.service.TokenService;
import com.backend.ecommerce.User.business.service.AuthServices;
import com.backend.ecommerce.User.dataAccess.mongo.RoleRepositoryMongo;
import com.backend.ecommerce.User.dataAccess.mongo.UserRepositoryMongo;
import com.backend.ecommerce.User.entities.entity.ApplicationUser;
import com.backend.ecommerce.User.entities.entity.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthManager implements AuthServices {

    /*@Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;*/
    @Autowired
    private final UserRepositoryMongo userRepository;
    @Autowired
    private final RoleRepositoryMongo roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final TokenService tokenService;

    @Override
    public ApplicationUser registerUser(String username, String password) {
        // TODO- Eğer kullanıcı adı daha önce varsa o zaman kontrol yapmalıyız
        String encodedPassword = passwordEncoder.encode(password);
        Roles userRole = roleRepository.findByAuthority("USER").get();

        System.out.println("UserRoleID: " + userRole.getRoleId());
        System.out.println("UserRoleAuthority: " + userRole.getAuthority());

        Set<Roles> authorities = new HashSet<>();

        authorities.add(userRole);
        // TODO-1 - UserId hep 0 olarak gönderilmemeli
        return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
    }

    @Override
    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new LoginResponseDTO(null, "");
        }
    }
}
