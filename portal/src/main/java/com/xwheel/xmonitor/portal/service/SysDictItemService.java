package com.xwheel.xmonitor.portal.service;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.dao.SysDictItemDao;
import com.xwheel.xmonitor.portal.entity.SysDictItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: leihang@live.cn
 * @date: 2015-11-23 11:12:32
 * @description: 字典服务
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Service
public class SysDictItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysDictItemService.class);

    @Autowired
    private SysDictItemDao sysDictItemDao;

    public SysDictItem getSysDictItem(int recordId) {
        return sysDictItemDao.getSysDictItem(recordId);
    }

    public Page<SysDictItem> getSysDictItemListPage(Page<SysDictItem> page, SysDictItem sysDictItem) {
        page.setCurrentPageResult(sysDictItemDao.getSysDictItemListPage(page, sysDictItem));
        page.setTotalCount(sysDictItemDao.getSysDictItemListPageCount(page,sysDictItem));
        return page;
    }

    public List<SysDictItem> getSysDictItemListAll(SysDictItem sysDictItem) {
        return sysDictItemDao.getSysDictItemListAll(sysDictItem);
    }

    public JsonResult saveSysDictItem(SysDictItem sysDictItem) {
        sysDictItemDao.saveSysDictItem(sysDictItem);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult deleteSysDictItem(int recordId) {
        sysDictItemDao.deleteSysDictItem(recordId);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult updateSysDictItem(SysDictItem sysDictItem) {
        sysDictItemDao.updateSysDictItem(sysDictItem);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public void exportSysDictItem() {

    }

    public JsonResult importSysDictItem(SysDictItem sysDictItem) {
        JsonResult result = new JsonResult(true, JsonResult.OPERATE_SUCCESS);
        return result;
    }

}