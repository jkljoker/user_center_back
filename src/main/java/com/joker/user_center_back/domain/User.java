package com.joker.user_center_back.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;


public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private int userStatus;
    private int userRole;

    public User(int userId, String userName, String userPassword, int userStatus, int userRole, int isDelete) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userRole = userRole;
        this.isDelete = isDelete;
    }

    public User() {
    }

    @TableLogic
    private int isDelete;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
