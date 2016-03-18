package com.xwheel.xmonitor.portal.dao;

import java.util.List;

import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.entity.SysUser;

/**
 * @author: leihang@live.cn
 * @date: 2016-03-15 05:29:22
 * @description: 一句话描述功能
 * 基本CRUD：新增、删除、修改、查询、导入、导出
 */
public interface SysUserDao {

    public SysUser getSysUser(int recordId);

    public List<SysUser> getSysUserListPage(Page<SysUser> page, SysUser sysUser);

    public long getSysUserListPageCount(Page<SysUser> page, SysUser sysUser);

    public List<SysUser> getSysUserListAll(SysUser sysUser);

    public void saveSysUser(SysUser sysUser);

    public void deleteSysUser(int recordId);

    public void updateSysUser(SysUser sysUser);

    public SysUser getSysUserByUserIdOrEmail(String userId,String mail);
}