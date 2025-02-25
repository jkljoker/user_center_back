package com.joker.user_center_back;

import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.mapper.UserMapper;
import com.joker.user_center_back.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCenterBackApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @Test
    public void testAddUser() {
        User user = new User();

        user.setUserName("xiaoli");
        user.setUserPassword("123456");

        userService.userRegister(user.getUserName(), user.getUserPassword(), "123456");
    }

    @Test
    public void testDeleteUser() {
        userMapper.deleteByUserName("xiaohuang");
    }


}
