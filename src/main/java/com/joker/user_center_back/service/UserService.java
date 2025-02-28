package com.joker.user_center_back.service;

import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;

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
    ApiResponse<User> userLogin(String userName, String userPassword, HttpServletRequest request);

    /**
     * 实现退出登录的功能
     *
     * @return
     */
    ApiResponse<String> userLogout(HttpServletRequest request);

    /**
     * 实现删除用户的功能(仅管理员可删除)
     *
     * @return
     */
    ApiResponse<String> delete(String userName, HttpServletRequest request);

    /**
     * 查询当前用户信息
     */
    ApiResponse<User> current(HttpServletRequest request);
}
