package com.xwheel.xmonitor.portal.entity;

import java.io.Serializable;
import java.util.Date;
/**
* @author: leihang@live.cn
* @date:   2016-03-16 11:38:15
* @description: 操作用户角色表
*/
public class SysRole implements Serializable {

	//唯一标识
	private Integer recordId;

	//角色编码
	private String roleCode;

	//角色名称
	private String roleName;

	//角色描述
	private String remark;

	//角色状态
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

    public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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