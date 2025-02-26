package com.joker.user_center_back.exception;

import com.joker.user_center_back.constants.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException ex) {
        HttpStatus status = (ex.getCode() == ErrorCode.SUCCESS) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        Map<String, Object> response = new HashMap<>();
        if (ex.getCode() == ErrorCode.SUCCESS) {
            response.put("message", "操作成功");
        } else {
            response.put("errorCode", ex.getCode());
            response.put("errorMessage", ex.getMessage());
        }

        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", ErrorCode.SYSTEM_ERROR);
        response.put("errorMessage", "系统错误");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
