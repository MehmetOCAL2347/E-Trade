package com.backend.ecommerce.User.business.rules;

import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.UserFailedException;
import com.backend.ecommerce.User.dataAccess.mongo.UserRepositoryMongo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserRulesManager implements UserRulesService{

    private UserRepositoryMongo userRepositoryMongo;

    @Override
    public void isUserExist(String email) {
        boolean userExist = userRepositoryMongo.existsByEmail(email);
        if (userExist){
            throw new UserFailedException(HttpStatus.CONFLICT.value(), "User already registered");
        }

    }
}
