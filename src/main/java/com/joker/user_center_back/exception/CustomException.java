package com.joker.user_center_back.exception;

public class CustomException extends RuntimeException{
    private int code;
    private String message;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    /**
     * 方便后续自定义返回信息
     * @return message
     */
    public String getMessage() {
        return message;
    }
}
