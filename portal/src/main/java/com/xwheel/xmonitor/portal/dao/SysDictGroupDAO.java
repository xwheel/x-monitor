package com.xwheel.xmonitor.portal.dao;


import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.entity.SysDictGroup;

import java.util.List;


/**
* @author: leihang@live.cn
* @date:   2015-08-27 22:22:30
* @description:  字典组，字典基础DAO
* 基本操作方法：新增、删除、修改、查询、导入、导出
*/
public interface SysDictGroupDao {

    public SysDictGroup getSysDictGroup(int recordId) ;

    public List<SysDictGroup> getSysDictGroupListPage(Page<SysDictGroup> page, SysDictGroup sysDictGroup);

    public long getSysDictGroupListPageCount(Page<SysDictGroup> page, SysDictGroup sysDictGroup);

    public List<SysDictGroup> getSysDictGroupListAll(SysDictGroup sysDictGroup);

    public void saveSysDictGroup(SysDictGroup sysDictGroup) ;

    public void deleteSysDictGroup(int recordId) ;

    public void updateSysDictGroup(SysDictGroup sysDictGroup) ;
}