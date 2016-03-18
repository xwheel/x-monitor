package com.xwheel.xmonitor.commons.beans;

import java.io.Serializable;

/**
 * @author: leihang@live.cn
 * @date: 2015-07-27 22:22:46
 * @description: 操作结果信息
 */
public class JsonResult implements Serializable {

    public static String OPERATE_SUCCESS="操作成功";

    public static String OPERATE_FAIL="操作失败";
    /**
     * 是否成功。true：成功；false：失败
     */
    private boolean success;

    /**
     * 返回编码
     */
    private String resultCode;

    /**
     * 返回信息
     */
    private String resultMessage;

    public JsonResult() {

    }

    public JsonResult(boolean success, String resultMessage) {
        this.success = success;
        this.resultMessage = resultMessage;
    }

    public JsonResult(boolean success, String resultCode, String resultMessage) {
        this.success = success;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
