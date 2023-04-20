package com.leapmotor.lpradio.model;


import com.leapmotor.baselib.model.BaseResult;
import com.leapmotor.baselib.net.http.HttpCode;

import java.io.Serializable;

/**
 * good programmer.
 *
 * @date : 2022/9/8 16:50
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class LpRadioResult<T> extends BaseResult implements Serializable {

    private T data;

    public LpRadioResult() {
    }

    public LpRadioResult(Throwable e) {
        super(e);
    }

    @Override
    public boolean isOk() {
        return (code == HttpCode.OK && !isThrowable());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LpRadioResult{" +
                "data=" + data +
                "} " + super.toString();
    }
}