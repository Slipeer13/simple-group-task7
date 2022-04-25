package com.example.simplegrouptask7.exceptionHandling;

public class IsSuchProductException extends RuntimeException{
    public IsSuchProductException(String message) {
        super(message);
    }
}
