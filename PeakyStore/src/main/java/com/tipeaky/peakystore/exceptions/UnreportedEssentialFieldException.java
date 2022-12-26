package com.tipeaky.peakystore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnreportedEssentialFieldException extends RuntimeException {
    public UnreportedEssentialFieldException(String message) {
        super(message);
    }
}
