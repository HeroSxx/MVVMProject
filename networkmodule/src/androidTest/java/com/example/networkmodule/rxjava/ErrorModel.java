package com.example.networkmodule.rxjava;

/**
 * class_name: ErrorModel
 * package_name: com.basemodule.rx
 * author: lijun
 * time: 2018/8/27 10:26
 */
public class ErrorModel {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
