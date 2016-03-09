package com.yt.core.common;

/**
 * Created by 林平 on 2016/2/25.
 */
public class AppException extends Exception {
    private StaticErrorEnum error;

    public AppException(StaticErrorEnum error){
        super(error.errorText);

        this.error = error;

    }

    public String getCode() {
        return error.errorCode;
    }

    @Override
    public String getMessage() {
        return error.errorText;
    }
}
