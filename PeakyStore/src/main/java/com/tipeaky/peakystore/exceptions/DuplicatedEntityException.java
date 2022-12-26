package com.tipeaky.peakystore.exceptions;

public class DuplicatedEntityException extends RuntimeException{
    public DuplicatedEntityException(String msg) {
        super(msg);
    }
}
