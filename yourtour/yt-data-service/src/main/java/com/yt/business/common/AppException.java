package com.yt.business.common;

/**
 * Created by 林平 on 2016/2/25.
 */
public class AppException extends Exception {
    private String code, message;

    public AppException(String message){
        super(message);

        this.message = message;
    }

    public AppException(String code, String message){
        super(message);

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
