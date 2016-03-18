package com.xwheel.xmonitor.portal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: leihang@live.cn
 * @date: 2016-03-16 11:19:32
 * @description: 菜单权限表
 */
public class SysAuth implements Serializable {

    //唯一标识
    private Integer recordId;

    //上级权限编码
    private String parentCode;

    //权限编码（自动编码，00开始）
    private String authCode;

    //权限名称
    private String authName;

    //权限类型（1:菜单，2：权限）
    private String authType;

    //菜单URL
    private String menuUrl;

    //菜单样式
    private String menuClass;

    //菜单图片来源源
    private String menuImageUrl;

    //显示序号
    private Integer displayNo;

    //描述
    private String remark;

    //创建者ID
    private String creatorId;

    //创建者名称
    private String creatorName;

    //创建时间
    private Date createTime;

    //子权限
    private List<SysAuth> subSysAuth = new ArrayList<>();

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuClass() {
        return menuClass;
    }

    public void setMenuClass(String menuClass) {
        this.menuClass = menuClass;
    }

    public String getMenuImageUrl() {
        return menuImageUrl;
    }

    public void setMenuImageUrl(String menuImageUrl) {
        this.menuImageUrl = menuImageUrl;
    }

    public Integer getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(Integer displayNo) {
        this.displayNo = displayNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SysAuth> getSubSysAuth() {
        return subSysAuth;
    }

    public void setSubSysAuth(List<SysAuth> subSysAuth) {
        this.subSysAuth = subSysAuth;
    }
}