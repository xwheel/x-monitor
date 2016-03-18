package com.xwheel.xmonitor.portal.dao.impl;

import com.xwheel.xmonitor.commons.base.MyBatisBaseDao;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.commons.utils.CamelCaseUtils;
import com.xwheel.xmonitor.portal.dao.SysDictGroupDao;
import com.xwheel.xmonitor.portal.entity.SysDictGroup;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* @author: leihang@live.cn
* @date:   2015-08-27 22:22:30
* @description:  字典组，字典基础DAO 实现
* 基本操作方法：新增、删除、修改、查询、导入、导出
*/
@Component
public class SysDictGroupDaoImpl extends MyBatisBaseDao<SysDictGroup> implements SysDictGroupDao {

    private static String mapperNameSpace ="com.xwheel.xmonitor.portal.dao.mappers.SysDictGroupMapper";

    public SysDictGroup getSysDictGroup(int recordId) {
        return get(mapperNameSpace+".getSysDictGroup", recordId);
    }

    public List<SysDictGroup> getSysDictGroupListPage(Page<SysDictGroup> page, SysDictGroup sysDictGroup) {
        Map conditionMap = new HashMap();
        conditionMap.put("dictGroupCode", sysDictGroup.getDictGroupCode());
        conditionMap.put("dictGroupName", sysDictGroup.getDictGroupName());
        conditionMap.put("startIndex", page.getStartIndex());
        conditionMap.put("lastIndex", page.getLastIndex());
        conditionMap.put("orderBy", CamelCaseUtils.toUnderlineName(page.getOrderBy()));
        conditionMap.put("order", page.getOrder());
        return getList(mapperNameSpace+".getSysDictGroupListPage", conditionMap);
    }

    public long getSysDictGroupListPageCount(Page<SysDictGroup> page,SysDictGroup sysDictGroup) {
        Map conditionMap = new HashMap();
        conditionMap.put("dictGroupCode", sysDictGroup.getDictGroupCode());
        conditionMap.put("dictGroupName", sysDictGroup.getDictGroupName());
        return getTotalCount(mapperNameSpace+".getSysDictGroupListPageCount", conditionMap);
    }

    public List<SysDictGroup> getSysDictGroupListAll(SysDictGroup sysDictGroup) {
        Map conditionMap = new HashMap();
        conditionMap.put("recordId", "");
        return getList(mapperNameSpace+".getSysDictGroupListAll", conditionMap);
    }

    public void saveSysDictGroup(SysDictGroup sysDictGroup) {
        delete(mapperNameSpace+".saveSysDictGroup", sysDictGroup);
    }

    public void deleteSysDictGroup(int recordId) {
        delete(mapperNameSpace+".deleteSysDictGroup", recordId);
    }

    public void updateSysDictGroup(SysDictGroup sysDictGroup) {
        update(mapperNameSpace+".updateSysDictGroup", sysDictGroup);
    }
}