package com.joker.user_center_back.mapper;

import com.joker.user_center_back.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    // 插入用户
    @Insert("INSERT INTO user (userName, userPassword, userRole, isDelete) " +
            "VALUES (#{userName}, #{userPassword}, #{userRole}, #{isDelete})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "userId")
    void insert(User user);

    // 根据用户名删除用户
    @Delete("DELETE FROM user WHERE userName = #{userName}")
    Integer deleteByUserName(String userName);

    // 根据用户名和角色删除用户
    @Delete("DELETE FROM user WHERE userName = #{userName} AND userRole = #{userRole}")
    void deleteByUserNameAndUserRole(@Param("userName") String userName, @Param("userRole") String userRole);

    // 根据 ID 查询用户
    @Select("SELECT * FROM user WHERE userId = #{userId}")
    User selectById(int userId);

    // 根据 userName 查询用户
    @Select("SELECT * FROM user WHERE userName = #{userName}")
    User selectByName(String userName);

    //根据 userName 和 userPassword 查询用户
    @Select("SELECT * from user WHERE userName = #{userName} AND userPassword = #{userPassword}")
    User selectUser(String userName, String userPassword);

    // 查询所有用户
    @Select("SELECT * FROM user")
    List<User> selectAll();
}
