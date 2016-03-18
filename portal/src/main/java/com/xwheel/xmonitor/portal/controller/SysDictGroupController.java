package com.xwheel.xmonitor.portal.controller;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.JsonStore;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.base.BaseController;
import com.xwheel.xmonitor.portal.entity.SysDictGroup;
import com.xwheel.xmonitor.portal.service.SysDictGroupService;
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
import javax.servlet.http.HttpSession;

/**
 * @author: leihang@live.cn
 * @date: 2015-08-27 22:22:30
 * @description: 字典组
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */

@Controller
@RequestMapping("/sysDictGroup")
public class SysDictGroupController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysDictGroupController.class);

    //字典组
    @Autowired
    private SysDictGroupService sysDictGroupService;

    //初始化
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpSession httpSession, ModelMap modelMap) {
        return "sys/dict/sys_dict_group_list";
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
        Page<SysDictGroup> page = new Page(pageNo, pageSize);
        page.setOrderBy(sort);
        page.setOrder(order);
        SysDictGroup sysDictGroup = new SysDictGroup();
        sysDictGroup.setDictGroupCode(request.getParameter("dictGroupCode"));
        sysDictGroup.setDictGroupName(request.getParameter("dictGroupName"));
        page = sysDictGroupService.getSysDictGroupListPage(page, sysDictGroup);
        return new JsonStore(page.getCurrentPageResult(), page.getTotalCount());
    }

    //查询单个
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public SysDictGroup getSysDictGroup(@RequestParam(required = false) int recordId) {
        return sysDictGroupService.getSysDictGroup(recordId);
    }

    //新增
    @RequestMapping(value = "/preSave", method = {RequestMethod.POST, RequestMethod.GET})
    public String preSaveSysDictGroup(HttpServletRequest request) {
        return "sys/dict/sys_dict_group_add";
    }

    //新增
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult saveSysDictGroup(SysDictGroup sysDictGroup, HttpServletRequest request) {
        sysDictGroup.setLanguage("zh");
        JsonResult result = sysDictGroupService.saveSysDictGroup(sysDictGroup);
        return result;
    }

    //修改
    @RequestMapping(value = "/preUpdate", method = {RequestMethod.POST, RequestMethod.GET})
    public String preUpdateSysDictGroup(int recordId, ModelMap modelMap) {
        modelMap.addAttribute("dictGroup", sysDictGroupService.getSysDictGroup(recordId));
        return "sys/dict/sys_dict_group_update";
    }

    //修改
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult updateSysDictGroup(SysDictGroup sysDictGroup, Model model) {
        JsonResult result = sysDictGroupService.updateSysDictGroup(sysDictGroup);
        return result;
    }

    //删除
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult deleteSysDictGroup(int recordId) {
        JsonResult result = sysDictGroupService.deleteSysDictGroup(recordId);
        return result;
    }

    //导入
    @RequestMapping(value = "/import", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult importSysDictGroup(SysDictGroup sysDictGroup, Model model) {
        JsonResult result = sysDictGroupService.importSysDictGroup(sysDictGroup);
        return result;
    }

    //导出
    @RequestMapping(value = "/export", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportSysDictGroup() {
        sysDictGroupService.exportSysDictGroup();
    }


}