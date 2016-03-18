package com.xwheel.xmonitor.portal.entity;

import java.io.Serializable;
/**
* @author: leihang@live.cn
* @date:   2016-03-18 11:06:24
* @description: 角色权限关系表
*/
public class SysRoleAuth implements Serializable {

	//唯一标识
	private Integer recordId;

	//角色编码
	private String roleCode;

	//权限编码
	private String authCode;


    public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

    public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

    public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}