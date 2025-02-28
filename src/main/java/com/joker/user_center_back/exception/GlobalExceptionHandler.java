package com.joker.user_center_back.exception;
import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.utils.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ApiResponse<User> handleCustomException(CustomException ex) {
        return ApiResponse.error(ex.getCode(), ex.getMessage());
    }

}
