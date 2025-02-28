package com.joker.user_center_back.controller;

import com.joker.user_center_back.constants.ErrorCode;
import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.dto.request.Delete;
import com.joker.user_center_back.dto.request.UserLogin;
import com.joker.user_center_back.dto.request.UserRegister;
import com.joker.user_center_back.service.UserService;
import com.joker.user_center_back.utils.ApiResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import static com.joker.user_center_back.constants.Constants.USER_LOGIN_STATE;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody UserRegister userRegister) {
        User user = userService.userRegister(userRegister.getUserName(), userRegister.getUserPassword(), userRegister.getCheckPassword());
        return ApiResponse.success(user);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        // 调用 userService 进行登录验证
        return userService.userLogin(userLogin.getUserName(), userLogin.getUserPassword(), request);
    }

    @PostMapping("/logout")
    public ApiResponse<String> userLogout(HttpServletRequest request) {
        if (request == null) {
            return ApiResponse.error(ErrorCode.REQUEST_IS_NULL, ErrorCode.getMessage(ErrorCode.REQUEST_IS_NULL));
        }
        userService.userLogout(request);
        return userService.userLogout(request);
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
    public ApiResponse<String> delete(@RequestBody Delete delete, HttpServletRequest request) {
        return userService.delete(delete.getUserName(), request);
    }

    @GetMapping("/current")  //
    public ApiResponse<User> getCurrentUser(HttpServletRequest request) {
        return userService.current(request);
    }

}
