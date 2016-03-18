package com.xwheel.xmonitor.portal.service;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.dao.SysRoleAuthDao;
import com.xwheel.xmonitor.portal.dao.SysRoleDao;
import com.xwheel.xmonitor.portal.entity.SysAuthTree;
import com.xwheel.xmonitor.portal.entity.SysRole;
import com.xwheel.xmonitor.portal.entity.SysRoleAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: leihang@live.cn
 * @date: 2016-03-16 11:38:15
 * @description: 一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleAuthDao sysRoleAuthDao;

    public SysRole getSysRole(int recordId) {
        return sysRoleDao.getSysRole(recordId);
    }

    public Page<SysRole> getSysRoleListPage(Page<SysRole> page, SysRole sysRole) {
        page.setCurrentPageResult(sysRoleDao.getSysRoleListPage(page, sysRole));
        page.setTotalCount(sysRoleDao.getSysRoleListPageCount(page, sysRole));
        return page;
    }

    public List<SysRole> getSysRoleListAll(SysRole sysRole) {
        return sysRoleDao.getSysRoleListAll(sysRole);
    }

    public List<SysAuthTree> getSysRoleAuthTree(String roleCode) {
        return sysRoleAuthDao.getSysRoleAuthTree(roleCode);
    }

    public JsonResult saveSysRole(SysRole sysRole, String authCodes) {
        sysRoleDao.saveSysRole(sysRole);
        sysRoleAuthDao.saveSysRoleAuthList(getSysRoleAuthList(sysRole,authCodes));
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult deleteSysRole(int recordId) {
        sysRoleDao.deleteSysRole(recordId);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult updateSysRole(SysRole sysRole, String authCodes) {
        sysRoleDao.updateSysRole(sysRole);
        sysRoleAuthDao.deleteSysRoleAuthByRoleCode(sysRole.getRoleCode());
        sysRoleAuthDao.saveSysRoleAuthList(getSysRoleAuthList(sysRole,authCodes));
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public void exportSysRole() {

    }

    public JsonResult importSysRole(SysRole SysRole) {
        JsonResult result = new JsonResult(true, JsonResult.OPERATE_SUCCESS);
        return result;
    }

    private List<SysRoleAuth> getSysRoleAuthList(SysRole sysRole,String authCodes){
        List<SysRoleAuth> sysRoleAuths = new ArrayList<>();
        String[] authCodeArray = authCodes.split(",");
        for (String authCode : authCodeArray) {
            SysRoleAuth sysRoleAuth = new SysRoleAuth();
            sysRoleAuth.setRoleCode(sysRole.getRoleCode());
            sysRoleAuth.setAuthCode(authCode);
            sysRoleAuths.add(sysRoleAuth);
        }
        return  sysRoleAuths;
    }
}