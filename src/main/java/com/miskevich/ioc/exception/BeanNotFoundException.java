package com.miskevich.ioc.exception;

public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(String message){
        super(message);
    }
}
