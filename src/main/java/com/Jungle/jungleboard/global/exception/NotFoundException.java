package com.Jungle.jungleboard.global.exception;


import com.Jungle.jungleboard.global.model.ResponseStatus;

/**
 * 요청한 리소스가 존재하지 않을 때 사용하는 예외
 */
public class NotFoundException extends BusinessLogicException {

    public NotFoundException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
