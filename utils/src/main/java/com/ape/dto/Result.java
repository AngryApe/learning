package com.ape.dto;

import java.io.Serializable;

/**
 * AngryApe created at 2017-10-23
 */
public class Result<T> implements Serializable {

    /**
     * Request data
     */
    private T data;

    /**
     * Is request successful.
     */
    private boolean success;

    /**
     * Return message. When {@link #success} is false, value should not be null.
     */
    private ReturnMsg msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ReturnMsg getMsg() {
        return msg;
    }

    public void setMsg(ReturnMsg msg) {
        this.msg = msg;
    }

    /**
     *
     * 调用示例：if(result.copy(res)){return result}
     * @param res
     * @return true: 拷贝成功，即res不成功；false:拷贝不成功，即res 成功；
     */
    public boolean copy(Result res) {
        // false indicate
        if(res==null || res.isSuccess())
            return false;
        this.success = res.success;
        this.msg = res.msg;
        this.data = (T) res.data;
        return true;
    }

    public void setMsg(String code, String message) {
        if (msg == null)
            this.msg = new ReturnMsg(code, message);
        else {
            this.msg.setCode(code);
            this.msg.setMessage(message);
        }
    }

}
