package com.example.simpleGroupTask7.exceptionHandling;

public class NoSuchProductException extends RuntimeException{

    public NoSuchProductException(String message) {
        super(message);
    }
}
