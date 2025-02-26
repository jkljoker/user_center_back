package com.joker.user_center_back.service;

import com.joker.user_center_back.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService extends Iterable<User>{

    /**
     * 实现注册功能
     *
     * @param userName      用户名
     * @param userPassword  密码
     * @param checkPassword 校验密码
     * @return
     */
    User userRegister(String userName, String userPassword, String checkPassword);

    /**
     * 实现注册功能
     *
     * @param userName     用户名
     * @param userPassword 密码
     */
    ResponseEntity<User> userLogin(String userName, String userPassword, HttpServletRequest request);

    /**
     * 实现注销登录的功能
     */
    int userLogout(HttpServletRequest request);
}
