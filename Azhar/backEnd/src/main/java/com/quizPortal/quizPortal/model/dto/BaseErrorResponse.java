package com.quizPortal.quizPortal.model.dto;

public class BaseErrorResponse  {
    private Integer code;
    private String message;

    public BaseErrorResponse(int code, String message) {
        this.message = message;
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
