package com.imaginationunlimited.sniper.utils;

/**
 * Created by KarlSW on 2016/10/24.
 */

public class HttpResponse<T> {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private int code;//1
    private String msg;
    private T data;
}