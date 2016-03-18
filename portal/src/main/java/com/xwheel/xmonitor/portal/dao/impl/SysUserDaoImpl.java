package com.xwheel.xmonitor.portal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.commons.base.MyBatisBaseDao;
import com.xwheel.xmonitor.commons.utils.CamelCaseUtils;
import com.xwheel.xmonitor.portal.dao.SysUserDao;
import com.xwheel.xmonitor.portal.entity.SysUser;
import org.springframework.stereotype.Component;


/**
* @author: leihang@live.cn
* @date:   2016-03-15 05:29:22
* @description:  一句话描述功能
* 基本操作方法：新增、删除、修改、查询、导入、导出
*/
@Component
public class SysUserDaoImpl extends MyBatisBaseDao<SysUser> implements SysUserDao {

    private static String mapperNameSpace ="com.xwheel.xmonitor.portal.dao.mappers.SysUserMapper";

    public SysUser getSysUser(int recordId) {
        return get(mapperNameSpace+".getSysUser", recordId);
    }

    public List<SysUser> getSysUserListPage(Page<SysUser> page,SysUser sysUser){
        Map conditionMap = new HashMap();
        //其他条件
        conditionMap.put("mail", sysUser.getMail());
        conditionMap.put("operName", sysUser.getOperName());
        conditionMap.put("startIndex", page.getStartIndex());
        conditionMap.put("lastIndex", page.getLastIndex());
        conditionMap.put("orderBy", CamelCaseUtils.toUnderlineName(page.getOrderBy()));
        conditionMap.put("order", page.getOrder());
        return getList(mapperNameSpace+".getSysUserListPage", conditionMap);
    }

    public long getSysUserListPageCount(Page<SysUser> page,SysUser sysUser) {
        Map conditionMap = new HashMap();
        //其他条件
        conditionMap.put("mail", sysUser.getMail());
        conditionMap.put("operName", sysUser.getOperName());
        return getTotalCount(mapperNameSpace+".getSysUserListPageCount", conditionMap);
    }

    public List<SysUser> getSysUserListAll(SysUser sysUser) {
        Map conditionMap = new HashMap();
//        conditionMap.put("recordId", "");
        return getList(mapperNameSpace+".getSysUserListAll", conditionMap);
    }

    public void saveSysUser(SysUser sysUser) {
        delete(mapperNameSpace+".saveSysUser", sysUser);
    }

    public void deleteSysUser(int recordId) {
        delete(mapperNameSpace+".deleteSysUser", recordId);
    }

    public void updateSysUser(SysUser sysUser) {
        update(mapperNameSpace+".updateSysUser", sysUser);
    }

    @Override
    public SysUser getSysUserByUserIdOrEmail(String userId,String mail) {
        Map conditionMap = new HashMap();
        conditionMap.put("operId", userId);
        conditionMap.put("mail", mail);
        return get(mapperNameSpace+".getSysUserByUserIdOrEmail", conditionMap);
    }
}