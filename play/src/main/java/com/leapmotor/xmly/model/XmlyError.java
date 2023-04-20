package com.leapmotor.xmly.model;

import com.google.gson.annotations.SerializedName;
import com.leapmotor.baselib.model.BaseThrowable;
import com.leapmotor.baselib.net.http.HttpCode;

/**
 * good programmer.
 *
 * @date : 2020-11-30 14:49
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class XmlyError extends BaseThrowable {
    @SerializedName("error_no")
    private int mErrorNo = HttpCode.OK;
    @SerializedName("error_code")
    private String mErrorCode;
    @SerializedName("error_desc")
    private String mErrorDesc;

    public XmlyError() {
    }

    public XmlyError(Throwable e) {
        super(e);
    }

    public int getErrorNo() {
        return this.mErrorNo;
    }

    public void setErrorNo(int mErrorNo) {
        this.mErrorNo = mErrorNo;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(String mErrorCode) {
        this.mErrorCode = mErrorCode;
    }

    public String getErrorDesc() {
        return this.mErrorDesc;
    }

    public void setErrorDesc(String mErrorDesc) {
        this.mErrorDesc = mErrorDesc;
    }

    public boolean isError() {
        return mErrorNo != HttpCode.OK && mErrorNo != 0;
    }

    @Override
    public String toString() {
        return "XmlyError{" +
                "mErrorNo=" + mErrorNo +
                ", mErrorCode='" + mErrorCode + '\'' +
                ", mErrorDesc='" + mErrorDesc + '\'' +
                ", " + super.toString() +
                '}';
    }
}
