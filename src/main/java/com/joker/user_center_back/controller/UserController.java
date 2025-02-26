package com.joker.user_center_back.controller;

import com.joker.user_center_back.constants.ErrorCode;
import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.dto.request.UserLogin;
import com.joker.user_center_back.dto.request.UserRegister;
import com.joker.user_center_back.exception.CustomException;
import com.joker.user_center_back.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.joker.user_center_back.constants.Constants.USER_LOGIN_STATE;

@RestController("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegister userRegister) {
        User user = userService.userRegister(userRegister.getUserName(), userRegister.getUserPassword(), userRegister.getCheckPassword());
        if (user == null) {
            throw new CustomException(ErrorCode.UNKNOWN_ERROR, ErrorCode.getMessage(ErrorCode.UNKNOWN_ERROR));
        } else {
            return ResponseEntity.ok("用户"+ user.getUserName() + "注册成功");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        // 调用 userService 进行登录验证
        return userService.userLogin(userLogin.getUserName(), userLogin.getUserPassword(), request);
    }

    @PostMapping("/logout")
    public Integer userLogout(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return userService.userLogout(request);
    }

    @RequestMapping("/profile")
    public String getProfile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(USER_LOGIN_STATE) == null) {
            return "Unauthorized";  // 没有登录
        }
        // 获取用户数据并返回
        return "User Profile";
    }

}
