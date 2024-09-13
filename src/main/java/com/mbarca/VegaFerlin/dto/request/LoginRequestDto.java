package com.mbarca.VegaFerlin.dto.request;

public class LoginRequestDto {
    String userName;
    String password;

    public LoginRequestDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
