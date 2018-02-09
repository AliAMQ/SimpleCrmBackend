package com.practice1.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Alireza on 2/7/2018.
 */
public class LoginDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String username, String password, Boolean rememberMe, Boolean admin) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
