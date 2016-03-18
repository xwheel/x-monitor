package com.xwheel.xmonitor.portal.dao.impl;

import com.xwheel.xmonitor.commons.base.DaoException;
import com.xwheel.xmonitor.commons.base.MyBatisBaseDao;
import com.xwheel.xmonitor.portal.dao.SysRoleAuthDao;
import com.xwheel.xmonitor.portal.entity.SysAuthTree;
import com.xwheel.xmonitor.portal.entity.SysRoleAuth;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @author: leihang@live.cn
* @date:   2016-03-18 11:06:24
* @description:  一句话描述功能
* 基本操作方法：新增、删除、修改、查询、导入、导出
*/
@Component
public class SysRoleAuthDaoImpl extends MyBatisBaseDao<SysRoleAuth> implements SysRoleAuthDao {

    private static String mapperNameSpace ="com.xwheel.xmonitor.portal.dao.mappers.SysRoleAuthMapper";

    public SysRoleAuth getSysRoleAuth(int recordId) {
        return get(mapperNameSpace + ".getSysRoleAuth", recordId);
    }

    public List<SysAuthTree> getSysRoleAuthTree(String roleCode) {
        String key = mapperNameSpace + ".getSysRoleAuthTree";
        try {
            return getSqlSession().selectList(key, roleCode);
        } catch (DataAccessException e) {
            this.logger.error("getList error", e);
            throw new DaoException(e);
        }
    }

    public void saveSysRoleAuth(SysRoleAuth sysRoleAuth) {
        delete(mapperNameSpace + ".saveSysRoleAuth", sysRoleAuth);
    }

    public void saveSysRoleAuthList(List<SysRoleAuth> sysRoleAuths) {
        save(mapperNameSpace + ".saveSysRoleAuthList", sysRoleAuths);
    }

    public void deleteSysRoleAuth(int recordId) {
        delete(mapperNameSpace + ".deleteSysRoleAuth", recordId);
    }

    public void deleteSysRoleAuthByRoleCode(String roleCode) {
        delete(mapperNameSpace + ".deleteSysRoleAuthByRoleCode", roleCode);
    }

    public void updateSysRoleAuth(SysRoleAuth sysRoleAuth) {
        update(mapperNameSpace + ".updateSysRoleAuth", sysRoleAuth);
    }

}