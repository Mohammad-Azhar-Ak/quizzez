package com.quizPortal.quizPortal.model.dto;

public class BaseResponse<T> extends BaseErrorResponse{

    private T data;

    public BaseResponse(Integer code, String message) {
        super(code, message);
    }

    public BaseResponse(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
