package com.leapmotor.baselib.model;

import com.google.gson.annotations.SerializedName;
import com.leapmotor.baselib.net.http.HttpCode;

import java.io.Serializable;
import java.util.Locale;

/**
 * good programmer.
 *
 * @date : 2019-01-23 上午 11:51
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :实体基类:各模块根据api的response自定义
 */
public class BaseResult extends BaseThrowable implements Serializable {
    public static final int OK = 0;

    @SerializedName(
            value = "code",
            alternate = {"result", "status"}
    )
    protected int code = HttpCode.ERROR;
    @SerializedName(
            value = "errorCode",
            alternate = {"error_code"}
    )
    protected int errorCode = HttpCode.ERROR;
    @SerializedName(
            value = "msg",
            alternate = {"error", "error_msg", "message", "error_desc"}
    )
    protected String msg;

    public BaseResult() {
    }

    public BaseResult(Throwable e) {
        super(e);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isOk() {
        return code == OK || errorCode == OK;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return String.format(Locale.getDefault(), "code : %d and msg : %s", code, msg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", errorCode=" + errorCode +
                ", msg='" + msg + '\'' +
                "} " + super.toString();
    }
}
