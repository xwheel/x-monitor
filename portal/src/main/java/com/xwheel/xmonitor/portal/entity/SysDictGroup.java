package com.xwheel.xmonitor.portal.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: leihang@live.cn
 * @date: 2015-11-23 12:39:00
 * @description: 数据字典，存放枚举值信息
 */
public class SysDictGroup implements Serializable {

    //唯一标示
    private Integer recordId;

    //字典组编码
    private String dictGroupCode;

    //字典组名称
    private String dictGroupName;

    //字典组描述
    private String dictGroupNote;

    //(是否用户自定义，0为否，1为是；非空)
    private String allowCustomizationFlag;

    //启用开始时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date enableStartTime;

    //启用结束时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date enableEndTime;

    //语言(zh:中文;en:英文)
    private String language;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getDictGroupCode() {
        return dictGroupCode;
    }

    public void setDictGroupCode(String dictGroupCode) {
        this.dictGroupCode = dictGroupCode;
    }

    public String getDictGroupName() {
        return dictGroupName;
    }

    public void setDictGroupName(String dictGroupName) {
        this.dictGroupName = dictGroupName;
    }

    public String getDictGroupNote() {
        return dictGroupNote;
    }

    public void setDictGroupNote(String dictGroupNote) {
        this.dictGroupNote = dictGroupNote;
    }

    public String getAllowCustomizationFlag() {
        return allowCustomizationFlag;
    }

    public void setAllowCustomizationFlag(String allowCustomizationFlag) {
        this.allowCustomizationFlag = allowCustomizationFlag;
    }

    public Date getEnableStartTime() {
        return enableStartTime;
    }

    public void setEnableStartTime(Date enableStartTime) {
        this.enableStartTime = enableStartTime;
    }

    public Date getEnableEndTime() {
        return enableEndTime;
    }

    public void setEnableEndTime(Date enableEndTime) {
        this.enableEndTime = enableEndTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}