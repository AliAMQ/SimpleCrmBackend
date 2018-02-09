package com.practice1.config.security;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Alireza on 2/7/2018.
 */
public class JwtToken {

    private String token;

    private String firstname;

    private String role;

    public JwtToken(String token, String firstname, String role){
        this.token = token;
        this.firstname = firstname;
        this.role = role;
    }

    @JsonProperty("token")
    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }


    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
