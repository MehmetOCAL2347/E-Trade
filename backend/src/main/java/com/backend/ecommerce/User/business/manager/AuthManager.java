package com.backend.ecommerce.User.business.manager;

import com.backend.ecommerce.Cart.business.requests.AddToCartRequest;
import com.backend.ecommerce.Cart.business.service.CartService;
import com.backend.ecommerce.Cart.entities.entity.Cart;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.User.business.requests.UserRequestDTO;
import com.backend.ecommerce.User.business.responses.UserResponseDTO;
import com.backend.ecommerce.User.business.service.AuthServices;
import com.backend.ecommerce.User.business.service.TokenService;
import com.backend.ecommerce.User.dataAccess.mongo.RoleRepositoryMongo;
import com.backend.ecommerce.User.dataAccess.mongo.UserRepositoryMongo;
import com.backend.ecommerce.User.entities.entity.Roles;
import com.backend.ecommerce.User.entities.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthManager implements AuthServices {

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
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<UserResponseDTO> registerUser(UserRequestDTO registrationDto) {
        // TODO- Eğer kullanıcı adı daha önce varsa o zaman kontrol yapmalıyız
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
        Roles userRole = roleRepository.findByAuthority("USER").get();

        Set<Roles> authorities = new HashSet<>();

        authorities.add(userRole);
        // TODO-1 - UserId hep 0 olarak gönderilmemeli
        //return userRepository.save(new ApplicationUser(registrationDto.getUsername(), encodedPassword,  2,authorities));

        User savedUser = userRepository.save(
                User.builder()
                        .username(registrationDto.getUsername())
                        .password(encodedPassword)
                        .cartId(cartService.getCartId(new Cart()))
                        .authorities(authorities)
                        .build()
        );

        if (savedUser != null) {
            String token = tokenService.generateJwt(savedUser);
            return ResponseEntity.ok(new UserResponseDTO(token));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponseDTO());
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> loginUser(String username, String password) {

        String token;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            token = tokenService.generateJwt(auth);

            return ResponseEntity.ok(new UserResponseDTO(token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponseDTO());
        }
    }

    @Override
    public ResponseEntity<String> addProductToCart(String token, AddToCartRequest addToCartRequest) throws ParseException {

        String userId;
        String splittedToken = tokenService.getToken(token);

        if (!tokenService.isUserExpire(splittedToken)) {
            userId = tokenService.getUserIdFromJwt(splittedToken);
            String cartId = userRepository.findById(userId).get().getCartId();
            Cart cart = cartService.getCart(cartId).get();

            // update methodu ile de yapılabilir mi? service DB'ye eriştiğinde findAndUpdate() gibi bir method olabilir mongoda
            cart.setProductIds(addToCartRequest.getProductIds());
            cart.setTotalCount(addToCartRequest.getProductIds().size());
            cart.setTotalPrice(productService.calculateTotalPriceForCart(addToCartRequest.getProductIds()));

            cartService.updateCart(cart);
            return ResponseEntity.ok("Products Added To Cart");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Id bulunamadı");
        }
    }
}
