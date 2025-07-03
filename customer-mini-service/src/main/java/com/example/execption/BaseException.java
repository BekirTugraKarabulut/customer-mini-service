package com.example.execption;

public class BaseException extends RuntimeException {

    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.prepareErrorMessage());
    }
}
