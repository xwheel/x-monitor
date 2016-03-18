package com.xwheel.xmonitor.portal.dao;

        import java.util.List;

        import com.xwheel.xmonitor.commons.beans.Page;
        import com.xwheel.xmonitor.portal.entity.SysAuthTree;
        import com.xwheel.xmonitor.portal.entity.SysRole;

/**
 * @author: leihang@live.cn
 * @date: 2016-03-16 11:38:15
 * @description: 一句话描述功能
 * 基本CRUD：新增、删除、修改、查询、导入、导出
 */
public interface SysRoleDao {

    public SysRole getSysRole(int recordId);

    public List<SysRole> getSysRoleListPage(Page<SysRole> page, SysRole sysRole);

    public long getSysRoleListPageCount(Page<SysRole> page, SysRole sysRole);

    public List<SysRole> getSysRoleListAll(SysRole sysRole);

    public void saveSysRole(SysRole sysRole);

    public void deleteSysRole(int recordId);

    public void updateSysRole(SysRole sysRole);

    public List<SysAuthTree> getSysRoleAuthTree(String roleCode);
}