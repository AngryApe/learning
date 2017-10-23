package com.ape.dto;

/**
 * AngryApe created at 2017-10-23
 */
public class ReturnMsg {

    private String code;
    private String message;

    public ReturnMsg(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
