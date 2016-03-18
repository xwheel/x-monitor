package com.xwheel.xmonitor.portal.dao;


import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.entity.SysDictItem;

import java.util.List;

/**
* @author: leihang@live.cn
* @date:   2015-11-23 11:12:32
* @description:  一句话描述功能
* 基本操作方法：新增、删除、修改、查询、导入、导出
*/
public interface SysDictItemDao {

    public SysDictItem getSysDictItem(int recordId) ;

    public List<SysDictItem> getSysDictItemListPage(Page<SysDictItem> page, SysDictItem sysDictItem);

    public long getSysDictItemListPageCount(Page<SysDictItem> page, SysDictItem sysDictItem);

    public List<SysDictItem> getSysDictItemListAll(SysDictItem sysDictItem);

    public void saveSysDictItem(SysDictItem sysDictItem) ;

    public void deleteSysDictItem(int recordId) ;

    public void updateSysDictItem(SysDictItem sysDictItem) ;
}