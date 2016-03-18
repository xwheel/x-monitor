package com.xwheel.xmonitor.portal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.commons.base.MyBatisBaseDao;
import com.xwheel.xmonitor.commons.utils.CamelCaseUtils;
import com.xwheel.xmonitor.portal.dao.SysAuthDao;
import com.xwheel.xmonitor.portal.entity.SysAuth;
import org.springframework.stereotype.Component;


/**
 * @author: leihang@live.cn
 * @date: 2016-03-16 11:19:32
 * @description: 一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Component
public class SysAuthDaoImpl extends MyBatisBaseDao<SysAuth> implements SysAuthDao {


    private static String mapperNameSpace = "com.xwheel.xmonitor.portal.dao.mappers.SysAuthMapper";

    public SysAuth getSysAuth(int recordId) {
        return get(mapperNameSpace + ".getSysAuth", recordId);
    }

    @Override
    public SysAuth getSysAuthByAuthCode(String authCode) {
        Map conditionMap = new HashMap();
        conditionMap.put("authCode", authCode);
        return get(mapperNameSpace + ".getSysAuthByAuthCode", conditionMap);
    }

    @Override
    public List<SysAuth> getSysMenuListAll(SysAuth SysAuth) {
        return getList(mapperNameSpace + ".getSysMenuListAll", SysAuth);
    }

    @Override
    public List<SysAuth> getSysAuthListByRoleCode(String roleCode) {
        return getList(mapperNameSpace + ".getSysAuthListByRoleCode", roleCode);
    }

    public List<SysAuth> getSysAuthListPage(Page<SysAuth> page, SysAuth sysAuth) {
        Map conditionMap = new HashMap();
        //其他条件
        conditionMap.put("authCode", sysAuth.getAuthCode());
        conditionMap.put("authName", sysAuth.getAuthName());
        conditionMap.put("startIndex", page.getStartIndex());
        conditionMap.put("lastIndex", page.getLastIndex());
        conditionMap.put("orderBy", CamelCaseUtils.toUnderlineName(page.getOrderBy()));
        conditionMap.put("order", page.getOrder());
        return getList(mapperNameSpace + ".getSysAuthListPage", conditionMap);
    }

    public long getSysAuthListPageCount(Page<SysAuth> page, SysAuth sysAuth) {
        Map conditionMap = new HashMap();
        conditionMap.put("authCode", sysAuth.getAuthCode());
        conditionMap.put("authName", sysAuth.getAuthName());
        return getTotalCount(mapperNameSpace + ".getSysAuthListPageCount", conditionMap);
    }

    public List<SysAuth> getSysAuthListAll(SysAuth sysAuth) {
        Map conditionMap = new HashMap();
        return getList(mapperNameSpace + ".getSysAuthListAll", conditionMap);
    }

    public void saveSysAuth(SysAuth sysAuth) {
        delete(mapperNameSpace + ".saveSysAuth", sysAuth);
    }

    public void deleteSysAuth(int recordId) {
        delete(mapperNameSpace + ".deleteSysAuth", recordId);
    }

    public void updateSysAuth(SysAuth sysAuth) {
        update(mapperNameSpace + ".updateSysAuth", sysAuth);
    }
}