package com.miskevich.ioc.exception;

public class BeanInstantiationException extends RuntimeException {
    public BeanInstantiationException(String message) {
        super(message);
    }

    public BeanInstantiationException(ReflectiveOperationException e) {
        super(e);
    }
}
