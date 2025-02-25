package com.joker.user_center_back.controller;

import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.dto.request.UserLogin;
import com.joker.user_center_back.dto.request.UserRegister;
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
    public String register(@RequestBody UserRegister userRegister) {
        long result = userService.userRegister(userRegister.getUserName(), userRegister.getUserPassword(), userRegister.getCheckPassword());
        if(result == -1) {
            return "注册失败";
        } else {
            return "注册成功";
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        // 调用 userService 进行登录验证
        User user = userService.userLogin(userLogin.getUserName(), userLogin.getUserPassword(), request);

        if (user != null) {
            // 如果登录成功，返回 200 OK 和用户信息
            return ResponseEntity.ok(user);
        } else {
            // 如果登录失败，返回 401 Unauthorized
            return ResponseEntity.status(401).build();
        }
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
