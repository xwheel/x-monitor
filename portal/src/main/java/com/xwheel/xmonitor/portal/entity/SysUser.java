package com.xwheel.xmonitor.portal.entity;

import java.io.Serializable;
import java.util.Date;
/**
* @author: leihang@live.cn
* @date:   2016-03-15 05:29:22
* @description: 操作用户表
*/
public class SysUser implements Serializable {

	//唯一标识
	private Integer recordId;

	//登陆ID
	private String operId;

	//操作员名称
	private String operName;

	//登陆密码
	private String operPassword;

	//邮箱
	private String mail;

	//手机号码
	private String mobile;

	//账号状态(0:正常;1:锁定;)
	private String status;

	//创建者ID
	private String creatorId;

	//创建者名称
	private String creatorName;

	//创建时间
	private Date createTime;


    public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

    public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

    public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

    public String getOperPassword() {
		return operPassword;
	}

	public void setOperPassword(String operPassword) {
		this.operPassword = operPassword;
	}

    public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}