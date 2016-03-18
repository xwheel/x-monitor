package com.xwheel.xmonitor.portal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xwheel.xmonitor.commons.base.DaoException;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.commons.base.MyBatisBaseDao;
import com.xwheel.xmonitor.commons.utils.CamelCaseUtils;
import com.xwheel.xmonitor.portal.dao.SysRoleDao;
import com.xwheel.xmonitor.portal.entity.SysAuthTree;
import com.xwheel.xmonitor.portal.entity.SysRole;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;


/**
 * @author: leihang@live.cn
 * @date: 2016-03-16 11:38:15
 * @description: 一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Component
public class SysRoleDaoImpl extends MyBatisBaseDao<SysRole> implements SysRoleDao {


    private static String mapperNameSpace = "com.xwheel.xmonitor.portal.dao.mappers.SysRoleMapper";

    public SysRole getSysRole(int recordId) {
        return get(mapperNameSpace + ".getSysRole", recordId);
    }

    public List<SysRole> getSysRoleListPage(Page<SysRole> page, SysRole sysRole) {
        Map conditionMap = new HashMap();
        conditionMap.put("roleName",sysRole.getRoleName());
        //其他条件
        conditionMap.put("startIndex", page.getStartIndex());
        conditionMap.put("lastIndex", page.getLastIndex());
        conditionMap.put("orderBy", CamelCaseUtils.toUnderlineName(page.getOrderBy()));
        conditionMap.put("order", page.getOrder());
        return getList(mapperNameSpace + ".getSysRoleListPage", conditionMap);
    }

    public long getSysRoleListPageCount(Page<SysRole> page, SysRole sysRole) {
        Map conditionMap = new HashMap();
        conditionMap.put("roleName",sysRole.getRoleName());
        //其他条件
        return getTotalCount(mapperNameSpace + ".getSysRoleListPageCount", conditionMap);
    }

    public List<SysRole> getSysRoleListAll(SysRole sysRole) {
        Map conditionMap = new HashMap();
        conditionMap.put("recordId", sysRole.getRecordId());
        conditionMap.put("roleCode", sysRole.getRoleCode());
        conditionMap.put("roleName", sysRole.getRoleName());
        conditionMap.put("remark", sysRole.getRemark());
        conditionMap.put("status", sysRole.getStatus());
        return getList(mapperNameSpace + ".getSysRoleListAll", conditionMap);
    }

    public void saveSysRole(SysRole sysRole) {
        delete(mapperNameSpace + ".saveSysRole", sysRole);
    }

    public void deleteSysRole(int recordId) {
        delete(mapperNameSpace + ".deleteSysRole", recordId);
    }

    public void updateSysRole(SysRole sysRole) {
        update(mapperNameSpace + ".updateSysRole", sysRole);
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
}