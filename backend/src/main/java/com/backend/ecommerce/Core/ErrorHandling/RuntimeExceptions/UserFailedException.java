package com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserFailedException extends RuntimeException{
    private final int statusCode;
    private final String message;
}
