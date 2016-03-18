package com.xwheel.xmonitor.portal.service;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.dao.SysDictGroupDao;
import com.xwheel.xmonitor.portal.entity.SysDictGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: leihang@live.cn
 * @date: 2015-08-27 22:22:30
 * @description: 字典组服务
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Service
public class SysDictGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysDictGroupService.class);

    @Autowired
    private SysDictGroupDao sysDictGroupDao;

    public SysDictGroup getSysDictGroup(int recordId) {
        return sysDictGroupDao.getSysDictGroup(recordId);
    }

    public Page<SysDictGroup> getSysDictGroupListPage(Page<SysDictGroup> page, SysDictGroup sysDictGroup) {
        page.setCurrentPageResult(sysDictGroupDao.getSysDictGroupListPage(page, sysDictGroup));
        page.setTotalCount(sysDictGroupDao.getSysDictGroupListPageCount(page, sysDictGroup));
        return page;
    }

    public List<SysDictGroup> getSysDictGroupListAll(SysDictGroup sysDictGroup) {
        return sysDictGroupDao.getSysDictGroupListAll(sysDictGroup);
    }

    public JsonResult saveSysDictGroup(SysDictGroup sysDictGroup) {
        sysDictGroupDao.saveSysDictGroup(sysDictGroup);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult deleteSysDictGroup(int recordId) {
        sysDictGroupDao.deleteSysDictGroup(recordId);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult updateSysDictGroup(SysDictGroup sysDictGroup) {
        sysDictGroupDao.updateSysDictGroup(sysDictGroup);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public void exportSysDictGroup() {

    }

    public JsonResult importSysDictGroup(SysDictGroup sysDictGroup) {
        JsonResult result = new JsonResult(true, JsonResult.OPERATE_SUCCESS);
        return result;
    }

}