package com.Jungle.jungleboard.global.handler;


import com.Jungle.jungleboard.global.exception.BusinessLogicException;
import com.Jungle.jungleboard.global.model.CommonResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//전역적으로 예외 처리를 담당하는 클래스
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    protected CommonResponse<?> handleBusinessLogicException(BusinessLogicException e, HttpServletResponse response) {
        log.warn(e.getClass().getName(), e);
        response.setStatus(e.getResponseStatus().getStatusCode().value());
        return CommonResponse.fail(e.getResponseStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    protected CommonResponse<?> handleRuntimeException(RuntimeException e, HttpServletResponse response) {
        log.warn(e.getClass().getName(), e);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return CommonResponse.fail(HttpStatus.BAD_REQUEST, e.getMessage());

    }
}