package com.quizPortal.quizPortal.model.dto;

public class LoginSignupResponse {
    private String token;
    private String name;

    public LoginSignupResponse(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
