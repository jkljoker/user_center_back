package com.joker.user_center_back.service.impl;

import com.joker.user_center_back.constants.ErrorCode;
import com.joker.user_center_back.domain.User;
import com.joker.user_center_back.exception.CustomException;
import com.joker.user_center_back.mapper.UserMapper;
import com.joker.user_center_back.service.UserService;

import com.joker.user_center_back.utils.PasswordUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.joker.user_center_back.constants.Constants.USER_LOGIN_STATE;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Transactional(noRollbackFor = CustomException.class)
    @Override
    public void userRegister(String userName, String userPassword, String checkPassword) {

        /**
         * 判断用户名和密码是否符合规范
         */
        if (!checkUser(userName, userPassword)) {
            throw new CustomException(ErrorCode.USERNAME_PASSWORD_ERROR, ErrorCode.getMessage(ErrorCode.USERNAME_PASSWORD_ERROR));
        }
        /**
         * 判断校验密码是否符合规范
         */
        if (StringUtils.isAnyBlank(checkPassword)) {
            throw new CustomException(ErrorCode.CHECKPASSWORD_ERROR, ErrorCode.getMessage(ErrorCode.CHECKPASSWORD_ERROR));
        }
        /**
         * 密码和校验密码相同
         */
        if (!userPassword.equals(checkPassword)) {
            throw new CustomException(ErrorCode.PASSWORD_CHECK_NOTMATCH, ErrorCode.getMessage(ErrorCode.PASSWORD_CHECK_NOTMATCH));
        }
        /**
         * 用户名不能重复,对数据库的操作放在最后能减少对数据库的访问
         */
        if (userMapper.selectByName(userName) != null) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS, ErrorCode.getMessage(ErrorCode.USER_ALREADY_EXISTS));
        }
        /**
         * 对密码进行加密
         */
        String newPassword = PasswordUtils.encodePassword(userPassword);
        System.out.println(newPassword);
        /**
         * 将新用户存入数据库
         */
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(newPassword);
        userMapper.insert(user);

        System.out.println(user.getUserId());
        System.out.println(user.getUserName());
        System.out.println(user.getUserPassword());
        System.out.println(user.getUserRole());
        System.out.println(user.getIsDelete());
        throw new CustomException(ErrorCode.SUCCESS, ErrorCode.getMessage(ErrorCode.SUCCESS));
    }

    @Override
    public User userLogin(String userName, String userPassword, HttpServletRequest request) {
        /**
         * 对用户名和密码进行格式判断，如果不符合标准就不进行数据库的访问
         */
        if (!checkUser(userName, userPassword)) {
            throw new CustomException(ErrorCode.USERNAME_PASSWORD_ERROR, ErrorCode.getMessage(ErrorCode.USERNAME_PASSWORD_ERROR));
        }
        /**
         * 访问数据库，判断用户名是否存在
         */
        User user = userMapper.selectByName(userName);
        if (user == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND, ErrorCode.getMessage(ErrorCode.USER_NOT_FOUND));
        }
        // 使用 BCrypt 的 matches 方法进行密码验证
        if(!PasswordUtils.matches(userPassword, user.getUserPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_ERROR, ErrorCode.getMessage(ErrorCode.PASSWORD_ERROR));
        }

        /**
         * 对用户数据去敏,在此处是隐藏userPassword
         */
        User safeUser = new User();
        safeUser.setUserId(user.getUserId());
        safeUser.setUserName(user.getUserName());
        safeUser.setUserRole(user.getUserRole());

        request.getSession().setAttribute(USER_LOGIN_STATE, safeUser);
        return safeUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 0;
    }

    boolean checkUser(String userName, String userPassword) {
        /**
         * 判断是否非空
         */
        if (StringUtils.isAnyBlank(userName, userPassword)) {
            return false;
        }
        /**
         * 用户名长度不小于4位
         */
        if (userName.length() < 4) {
            return false;
        }
        /**
         * 密码长度不小于6位
         */
        if (userPassword.length() < 6) {
            return false;
        }

        /**
         * 用户名不能包含特殊字符
         */
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+ | {}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userName);
        if (matcher.find()) {
            return false;
        }
        return true;
    }
    @Override
    public Iterator<User> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super User> action) {
        UserService.super.forEach(action);
    }

    @Override
    public Spliterator<User> spliterator() {
        return UserService.super.spliterator();
    }
}
