package com.example.simpleGroupTask7.exceptionHandling;

public class IsSuchProductException extends RuntimeException{
    public IsSuchProductException(String message) {
        super(message);
    }
}
