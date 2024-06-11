package com.Jungle.jungleboard.global.exception;


import com.Jungle.jungleboard.global.model.ResponseStatus;

public class WrongPasswordException extends BusinessLogicException {

    public WrongPasswordException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}