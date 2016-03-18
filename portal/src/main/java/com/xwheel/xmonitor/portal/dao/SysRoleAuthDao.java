package com.xwheel.xmonitor.portal.dao;

import java.util.List;

import com.xwheel.xmonitor.portal.entity.SysAuthTree;
import com.xwheel.xmonitor.portal.entity.SysRoleAuth;

/**
 * @author: leihang@live.cn
 * @date: 2016-03-18 11:06:24
 * @description: 一句话描述功能
 * 基本CRUD：新增、删除、修改、查询、导入、导出
 */
public interface SysRoleAuthDao {

    public SysRoleAuth getSysRoleAuth(int recordId);

    public List<SysAuthTree> getSysRoleAuthTree(String roleCode);

    public void saveSysRoleAuth(SysRoleAuth sysRoleAuth);

    public void saveSysRoleAuthList(List<SysRoleAuth> sysRoleAuths);

    public void deleteSysRoleAuth(int recordId);

    public void deleteSysRoleAuthByRoleCode(String roleCode);

    public void updateSysRoleAuth(SysRoleAuth sysRoleAuth);
}

