package com.xwheel.xmonitor.portal.controller;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.JsonStore;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.base.BaseController;
import com.xwheel.xmonitor.portal.entity.SysDictItem;
import com.xwheel.xmonitor.portal.service.SysDictItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: leihang@live.cn
 * @date: 2015-11-23 11:12:32
 * @description: 字典
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Controller
@RequestMapping("/sysDictItem")
public class SysDictItemController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysDictGroupController.class);

    @Autowired
    private SysDictItemService sysDictItemService;

    //初始化
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpServletRequest request, ModelMap modelMap) {
        modelMap.addAttribute("dictGroupCode", request.getParameter("dictGroupCode"));
        return "sys/dict/sys_dict_item_list";
    }

    //查询列表
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonStore list(HttpServletRequest request) {
        //page ,rows
        int pageNo = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        String order = request.getParameter("order");
        String sort = request.getParameter("sort");
        Page<SysDictItem> page = new Page(pageNo, pageSize);
        page.setOrderBy(sort);
        page.setOrder(order);
        SysDictItem sysDictItem = new SysDictItem();
        sysDictItem.setDictItemCode(request.getParameter("dictItemCode"));
        sysDictItem.setDictGroupCode(request.getParameter("dictGroupCode"));
        sysDictItem.setDictItemName(request.getParameter("dictItemName"));
        page = sysDictItemService.getSysDictItemListPage(page, sysDictItem);
        return new JsonStore(page.getCurrentPageResult(), page.getTotalCount());
    }

    //查询单个
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public SysDictItem getSysDictItem(@RequestParam(required = false) int recordId) {
        return sysDictItemService.getSysDictItem(recordId);
    }

    //查询指定字典信息
    @RequestMapping(value = "/getDictItems", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SysDictItem> getDictItems(String dictGroupCode) {
        SysDictItem sysDictItem = new SysDictItem();
        sysDictItem.setDictGroupCode(dictGroupCode);
        List<SysDictItem> dataList = sysDictItemService.getSysDictItemListAll(sysDictItem);
        return dataList;
    }

    //查询所有字典信息
    @RequestMapping(value = "/getAllDictItems", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SysDictItem> getAllDictItems() {
        return sysDictItemService.getSysDictItemListAll(null);
    }

    //新增
    @RequestMapping(value = "/preSave", method = {RequestMethod.POST, RequestMethod.GET})
    public String preSaveSysDictGroup(HttpServletRequest request, Model model) {
        model.addAttribute("dictGroupCode", request.getParameter("dictGroupCode"));
        return "sys/dict/sys_dict_item_add";
    }

    //新增
    @RequestMapping(value = "/save", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public JsonResult saveSysDictItem(SysDictItem sysDictItem) {
        JsonResult result = sysDictItemService.saveSysDictItem(sysDictItem);
        return result;
    }

    //修改
    @RequestMapping(value = "/preUpdate", method = {RequestMethod.POST, RequestMethod.GET})
    public String preUpdateSysDictGroup(int recordId, ModelMap modelMap) {
        modelMap.addAttribute("dictItem", sysDictItemService.getSysDictItem(recordId));
        return "sys/dict/sys_dict_item_update";
    }

    //修改
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult updateSysDictItem(SysDictItem sysDictItem) {
        JsonResult result = sysDictItemService.updateSysDictItem(sysDictItem);
        return result;
    }

    //删除
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult deleteSysDictItem(int recordId) {
        JsonResult result = sysDictItemService.deleteSysDictItem(recordId);
        return result;
    }

    //导入
    @RequestMapping(value = "/import", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult importSysDictItem(SysDictItem sysDictItem, Model model) {
        JsonResult result = sysDictItemService.importSysDictItem(sysDictItem);
        return result;
    }

    //导出
    @RequestMapping(value = "/export", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportSysDictItem() {
        sysDictItemService.exportSysDictItem();
    }


}