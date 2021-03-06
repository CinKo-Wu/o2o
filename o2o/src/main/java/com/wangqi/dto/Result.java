package com.wangqi.dto;

public class Result<T> {
    //是否成功标志
    private boolean success;
    //成功时返回的数据
    private T data;
    //错误信息
    private String errorMsg;
    //错误码
    private int errorCode;

    public Result() {
    }

    //操作成功时使用的构造器
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    //操作失败时使用的构造器
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
