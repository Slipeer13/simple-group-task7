package com.example.simplegrouptask7.exceptionHandling;

public class NoSuchProductException extends RuntimeException{

    public NoSuchProductException(String message) {
        super(message);
    }
}
