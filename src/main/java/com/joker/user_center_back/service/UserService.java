package com.joker.user_center_back.service;

import com.joker.user_center_back.domain.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends Iterable<User>{

    /**
     * 实现注册功能
     * @param userName 用户名
     * @param userPassword 密码
     * @param checkPassword 校验密码
     * @return 用户id
     */
    long userRegister(String userName, String userPassword, String checkPassword);

    /**
     * 实现注册功能
     * @param userName 用户名
     * @param userPassword 密码
     */
    User userLogin(String userName, String userPassword, HttpServletRequest request);
}
