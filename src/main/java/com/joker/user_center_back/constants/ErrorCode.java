package com.joker.user_center_back.constants;


public class ErrorCode {
    // 通用错误码
    public static final int SUCCESS = 20000;
    public static final int SYSTEM_ERROR = 50000;
    public static final int UNKNOWN_ERROR = 50100;

    // 认证错误码
    public static final int UNAUTHORIZED = 40100;
    public static final int REQUEST_IS_NULL = 400001;

    // 用户模块错误码
    public static final int USERNAME_PASSWORD_ERROR = 10001;
    public static final int USER_NOT_FOUND = 10002;
    public static final int USER_ALREADY_EXISTS = 10003;
    public static final int PASSWORD_CHECK_NOTMATCH = 10004;
    public static final int CHECKPASSWORD_ERROR = 10005;
    public static final int PASSWORD_ERROR = 10006;


    // 错误码描述
    public static String getMessage(int code) {
        switch (code) {
            case SUCCESS:
                return "请求成功";
            case SYSTEM_ERROR:
                return "系统内部错误";
            case UNKNOWN_ERROR:
                return "未知错误";
            case UNAUTHORIZED:
                return "未授权";
            case USERNAME_PASSWORD_ERROR:
                return "用户名或密码错误";
            case USER_NOT_FOUND:
                return "用户不存在";
            case USER_ALREADY_EXISTS:
                return "用户已存在";
            case PASSWORD_CHECK_NOTMATCH:
                return "密码和校验密码不匹配";
            case CHECKPASSWORD_ERROR:
                return "校验码错误";
            case PASSWORD_ERROR:
                return "密码错误";
            case REQUEST_IS_NULL:
                return "请求不能为空";
            default:
                return "未定义错误";
        }
    }
}
