package com.example.networkmodule.gsonfactory;

/**
 * class_name: BaseResult
 * package_name: com.basemodule.gsonfactory
 * author: lijun
 * time: 2018/8/27 10:05
 */
public class BaseResult<T> {
//根据不同项目后台返回的数据格式进行相应匹配

    private String code;
    private String msg;
    private boolean result;
    private T resultData;

    public BaseResult(String code, String msg, boolean result, T resultData) {
        this.code = code;
        this.msg = msg;
        this.result = result;
        this.resultData = resultData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                ", resultData=" + resultData +
                '}';
    }
}
