package com.backend.ecommerce.Order.business.rules;

import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.EmailValidException;
import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.EmptyAreaException;
import com.backend.ecommerce.Order.business.requests.CreateOrderRequest;
import com.backend.ecommerce.Order.dataAccess.mongo.OrderRepository;
import com.backend.ecommerce.UpdatedCart.entities.entity.CartItem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class OrderRulesManager implements OrderRulesService {

    private OrderRepository orderRepository;

    @Override
    public Mono<Void> checkEmptyAreas(CreateOrderRequest createOrderRequest) {
        Set<String> excludedFields = new HashSet<>(Arrays.asList("discountCode"));

        return Flux.fromArray(CreateOrderRequest.class.getDeclaredFields())
                .filter(field -> !excludedFields.contains(field.getName()))
                .flatMap(field -> {
                    field.setAccessible(true);
                    try {
                        if (field.getType().equals(String.class)) {
                            String value = (String) field.get(createOrderRequest);
                            if (isNullOrEmpty(value)) {
                                return Mono.error(new EmptyAreaException(HttpStatus.NO_CONTENT.value(),
                                        field.getName() + " alanı boş bırakılmış. Lütfen ilgili alanı doldurunuz"));
                            }
                        }
                    } catch (IllegalAccessException e) {
                        return Mono.error(new EmptyAreaException(HttpStatus.NO_CONTENT.value(),
                                "Tüm alanların eksiksiz şekilde doldurulması gerekmektedir"));
                    }
                    return Mono.empty();
                }).then(Mono.defer(() -> {
                    return isProductListEmpty(createOrderRequest.getCartItemList())
                            .then(isEmailValid(createOrderRequest.getEmail()));
                }));
    }


    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private Mono<Void> isEmailValid(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        boolean isEmailValid = emailPattern.matcher(email).matches();

        return isEmailValid
                ? Mono.empty()
                : Mono.error(new EmailValidException(HttpStatus.BAD_REQUEST.value(), "Lütfen geçerli bir mail adresi giriniz"));
    }


    private Mono<Void> isProductListEmpty(List<CartItem> cartItemList) {
        return cartItemList.isEmpty() ?
                Mono.error(new EmptyAreaException(HttpStatus.NO_CONTENT.value(), "Sepet boş olamaz")) :
                Mono.empty();
    }
}
