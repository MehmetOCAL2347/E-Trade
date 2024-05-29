package com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomNotFoundException extends RuntimeException{
    private final String message;
}
