package com.creeperdch.jdmall.bean;
/*
 * Created by CREEPERDCH on 2017/8/4.
 */

public class ResultBean {
    private boolean success;
    private String errorMsg;//出错的信息
    private String result;//返回的JSON字符串

    public ResultBean() {
    }

    public ResultBean(boolean success, String errorMsg, String result) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
