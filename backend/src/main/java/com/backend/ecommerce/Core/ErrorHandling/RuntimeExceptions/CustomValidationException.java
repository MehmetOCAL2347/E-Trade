package com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomValidationException extends RuntimeException{
    private final String field;
    private final String message;
}
