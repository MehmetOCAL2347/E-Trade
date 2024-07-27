package com.backend.ecommerce.User.business.rules;

import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.UserFailedException;
import com.backend.ecommerce.User.dataAccess.mongo.UserRepositoryMongo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public void isEmailFormatCorrect(String email) {
        String regex = "^(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*"
                + "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
                + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
                + "@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}"
                + "|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
                + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*"
                + "[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]"
                + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new UserFailedException(HttpStatus.BAD_REQUEST.value(), "Your email format is not true");
        }
    }
}
