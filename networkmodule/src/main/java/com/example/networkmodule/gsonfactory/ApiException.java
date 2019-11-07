package com.example.networkmodule.gsonfactory;

/**
 * class_name: ApiException
 * package_name: com.basemodule.gsonfactory
 * author: lijun
 * time: 2018/8/27 10:13
 */
public class ApiException extends RuntimeException {
    private int errorCode;
    private String msg;

    public ApiException(int code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "errorCode=" + errorCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
