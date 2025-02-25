package com.joker.user_center_back.controller;

import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.dto.request.UserLogin;
import com.joker.user_center_back.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
    @Resource
    private UserService userService;

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
}
