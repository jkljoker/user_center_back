package com.joker.user_center_back.controller;

import com.joker.user_center_back.constants.ErrorCode;
import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.dto.request.Delete;
import com.joker.user_center_back.dto.request.UserLogin;
import com.joker.user_center_back.dto.request.UserRegister;
import com.joker.user_center_back.exception.CustomException;
import com.joker.user_center_back.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> userLogout(HttpServletRequest request) {
        if (request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求无效");
        }
        userService.userLogout(request);
        return ResponseEntity.ok("退出登录成功");
    }

    @RequestMapping("/profile")
    public String getProfile(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) {
            return "Unauthorized";  // 没有登录
        }
        // 获取用户数据并返回
        return user.getUserName();
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Delete delete, HttpServletRequest request) {
        Integer result = userService.delete(delete.getUserName(), request);
        if (result == 1) {
            return ResponseEntity.ok("账户" + delete.getUserName() + "删除成功");
        } else {
            throw new CustomException(ErrorCode.UNKNOWN_ERROR, ErrorCode.getMessage(ErrorCode.UNKNOWN_ERROR));
        }
    }

}
