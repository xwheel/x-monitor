package com.xwheel.xmonitor.portal.service;

import java.util.List;

import com.xwheel.xmonitor.portal.dao.SysAuthDao;
import com.xwheel.xmonitor.portal.entity.SysAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.Page;


/**
 * @author: leihang@live.cn
 * @date: 2016-03-16 11:19:32
 * @description: 一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Service
public class SysAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysAuthService.class);

    @Autowired
    private SysAuthDao sysAuthDao;

    public SysAuth getSysAuth(int recordId) {
        return sysAuthDao.getSysAuth(recordId);
    }

    public SysAuth getSysAuthByAuthCode(String authCode) {
        return sysAuthDao.getSysAuthByAuthCode(authCode);
    }

    public Page<SysAuth> getSysAuthListPage(Page<SysAuth> page, SysAuth sysAuth) {
        page.setCurrentPageResult(sysAuthDao.getSysAuthListPage(page, sysAuth));
        page.setTotalCount(sysAuthDao.getSysAuthListPageCount(page, sysAuth));
        return page;
    }

    public List<SysAuth> getSysMenuListAll(SysAuth sysAuth) {
        return sysAuthDao.getSysMenuListAll(sysAuth);
    }

    public List<SysAuth> getSysAuthListAll(SysAuth sysAuth) {
        return sysAuthDao.getSysAuthListAll(sysAuth);
    }

    public JsonResult saveSysAuth(SysAuth sysAuth) {
        sysAuthDao.saveSysAuth(sysAuth);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult deleteSysAuth(int recordId) {
        sysAuthDao.deleteSysAuth(recordId);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult updateSysAuth(SysAuth sysAuth) {
        sysAuthDao.updateSysAuth(sysAuth);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public void exportSysAuth() {

    }

    public JsonResult importSysAuth(SysAuth sysAuth) {
        JsonResult result = new JsonResult(true, JsonResult.OPERATE_SUCCESS);
        return result;
    }

}