package com.leapmotor.xmly.model;


import com.leapmotor.baselib.net.http.HttpCode;

/**
 * good programmer.
 *
 * @date : 2020-11-30 19:12
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ErrorResponse extends XmlyError {

    /**
     * timestamp : 1573129208185
     * status : 500
     * error : 5003
     * message : sig verify failed!
     * path : /openapi-smart-device-api/play-history/get-albums-by-uid
     */
    private long timestamp;
    private int status = HttpCode.OK;
    private String error;
    private String message;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(Throwable e) {
        super(e);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFailed() {
        return status != HttpCode.OK && status != 0;
    }

    public boolean isSuccess() {
        return !isFailed() && !isError() && !isThrowable();
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", " + super.toString() +
                '}';
    }
}
