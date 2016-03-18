package com.xwheel.xmonitor.portal.dao;

import java.util.List;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.entity.SysAuth;

/**
* @author: leihang@live.cn
* @date:   2016-03-16 11:19:32
* @description:  一句话描述功能
* 基本CRUD：新增、删除、修改、查询、导入、导出
*/
public interface SysAuthDao{

    public SysAuth getSysAuth(int recordId) ;

    //通过权限ID查询权限信息
    public SysAuth getSysAuthByAuthCode(String authCode);

    public List<SysAuth> getSysMenuListAll(SysAuth SysAuth);

    public List<SysAuth> getSysAuthListByRoleCode(String roleCode);

    public List<SysAuth> getSysAuthListPage(Page<SysAuth> page,SysAuth sysAuth);

    public long getSysAuthListPageCount(Page<SysAuth> page,SysAuth sysAuth);

    public List<SysAuth> getSysAuthListAll(SysAuth sysAuth);

    public void saveSysAuth(SysAuth sysAuth) ;

    public void deleteSysAuth(int recordId) ;

    public void updateSysAuth(SysAuth sysAuth) ;
}