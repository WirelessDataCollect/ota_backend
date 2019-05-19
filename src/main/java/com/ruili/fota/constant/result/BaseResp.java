package com.ruili.fota.constant.result;

import com.ruili.fota.common.DateTools;

/**
 * Created by Yin on 2018/12/20
 */
public class BaseResp<T> {
    /**
     * 返回码
     */
    private int status;

    /**
     * 返回信息描述
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public BaseResp() {
    }

    /**
     * @param resultStatus 状态码
     * @param message      信息
     * @param data         数据
     */
    public BaseResp(ResultStatus resultStatus, String message, T data) {
        this.status = resultStatus.getErrorStatus();
        this.message = message;
        this.data = data;
    }

    /**
     * 不带数据的返回结果
     *
     * @param resultStatus
     */
    public BaseResp(ResultStatus resultStatus) {
        this.status = resultStatus.getErrorStatus();
        this.message = resultStatus.getErrorMsg();
        this.data = (T) DateTools.currentTime();
    }

    /**
     * 带数据的返回结果
     *
     * @param resultStatus
     * @param data
     */
    public BaseResp(ResultStatus resultStatus, T data) {
        this.status = resultStatus.getErrorStatus();
        this.message = resultStatus.getErrorMsg();
        this.data = data;
    }


}

