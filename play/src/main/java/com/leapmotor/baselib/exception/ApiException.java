package com.leapmotor.baselib.exception;

/**
 * good programmer.
 *
 * @date : 2018-12-28 下午 05:59
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ApiException extends RuntimeException {
    public static final String NO_DATA = "noData";

    private final String errorCode;

    public ApiException(String errorMsg) {
        super(errorMsg);
        this.errorCode = NO_DATA;
    }

    public ApiException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public boolean isNoData() {
        return errorCode.equals(NO_DATA);
    }
}