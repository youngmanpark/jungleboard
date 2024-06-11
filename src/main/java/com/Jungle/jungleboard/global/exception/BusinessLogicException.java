package com.Jungle.jungleboard.global.exception;


import com.Jungle.jungleboard.global.model.ResponseStatus;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {

    private ResponseStatus responseStatus;

    public BusinessLogicException(ResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.responseStatus = responseStatus;
    }

    public BusinessLogicException(String message) {
        super(message);
    }
}
