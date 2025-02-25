package com.joker.user_center_back.dto.request;

public class UserRegister {
    String userName;
    String userPassword;
    String checkPassword;

    public UserRegister() {
    }

    public UserRegister(String userName, String userPassword, String checkPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.checkPassword = checkPassword;
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

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
