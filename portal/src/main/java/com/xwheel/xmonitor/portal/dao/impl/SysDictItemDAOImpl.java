package com.xwheel.xmonitor.portal.dao.impl;

import com.xwheel.xmonitor.commons.base.MyBatisBaseDao;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.commons.utils.CamelCaseUtils;
import com.xwheel.xmonitor.portal.dao.SysDictItemDao;
import com.xwheel.xmonitor.portal.entity.SysDictItem;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: leihang@live.cn
 * @date: 2015-11-23 11:12:32
 * @description: 一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Component
public class SysDictItemDaoImpl extends MyBatisBaseDao<SysDictItem> implements SysDictItemDao {

    private static String mapperNameSpace = "com.xwheel.xmonitor.portal.dao.mappers.SysDictItemMapper";

    public SysDictItem getSysDictItem(int recordId) {
        return get(mapperNameSpace + ".getSysDictItem", recordId);
    }

    public List<SysDictItem> getSysDictItemListPage(Page<SysDictItem> page, SysDictItem sysDictItem) {
        Map conditionMap = new HashMap();
        conditionMap.put("dictItemCode", sysDictItem.getDictItemCode());
        conditionMap.put("dictItemName", sysDictItem.getDictItemName());
        conditionMap.put("dictGroupCode", sysDictItem.getDictGroupCode());
        conditionMap.put("startIndex", page.getStartIndex());
        conditionMap.put("lastIndex", page.getLastIndex());
        conditionMap.put("orderBy", CamelCaseUtils.toUnderlineName(page.getOrderBy()));
        conditionMap.put("order", page.getOrder());
        return getList(mapperNameSpace + ".getSysDictItemListPage", conditionMap);
    }

    public long getSysDictItemListPageCount(Page<SysDictItem> page, SysDictItem sysDictItem) {
        Map conditionMap = new HashMap();
        conditionMap.put("dictItemCode", sysDictItem.getDictItemCode());
        conditionMap.put("dictItemName", sysDictItem.getDictItemName());
        conditionMap.put("dictGroupCode", sysDictItem.getDictGroupCode());
        return getTotalCount(mapperNameSpace + ".getSysDictItemListPageCount", conditionMap);
    }

    public List<SysDictItem> getSysDictItemListAll(SysDictItem sysDictItem) {
        Map conditionMap = new HashMap();
        if (sysDictItem != null) {
            if (sysDictItem.getDictGroupCode() != null) {
                conditionMap.put("dictGroupCode", sysDictItem.getDictGroupCode());
            }
        }
        return getList(mapperNameSpace + ".getSysDictItemListAll", conditionMap);
    }

    public void saveSysDictItem(SysDictItem sysDictItem) {
        delete(mapperNameSpace + ".saveSysDictItem", sysDictItem);
    }

    public void deleteSysDictItem(int recordId) {
        delete(mapperNameSpace + ".deleteSysDictItem", recordId);
    }

    public void updateSysDictItem(SysDictItem sysDictItem) {
        update(mapperNameSpace + ".updateSysDictItem", sysDictItem);
    }
}