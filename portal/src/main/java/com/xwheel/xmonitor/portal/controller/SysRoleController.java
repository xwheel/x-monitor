package com.xwheel.xmonitor.portal.controller;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.JsonStore;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.base.BaseController;
import com.xwheel.xmonitor.portal.entity.SysAuthTree;
import com.xwheel.xmonitor.portal.entity.SysRole;
import com.xwheel.xmonitor.portal.service.SysRoleService;
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
import java.util.List;

/**
 * @author: leihang@live.cn
 * @date: 2016-03-16 11:38:15
 * @description: 一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    //初始化
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(HttpSession httpSession, ModelMap modelMap) {
        return "sys/role/sys_role_list";
    }

    //查询列表
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonStore list(Page<SysRole> page, HttpServletRequest request) {
        SysRole sysRole = new SysRole();
        page = sysRoleService.getSysRoleListPage(page, sysRole);
        return new JsonStore(page.getCurrentPageResult(), page.getTotalCount());
    }

    //查询单个
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public SysRole getSysRole(@RequestParam(required = false) int recordId) {
        return sysRoleService.getSysRole(recordId);
    }

    //新增
    @RequestMapping(value = "/preSave", method = {RequestMethod.POST, RequestMethod.GET})
    public String preSaveSysRole(HttpServletRequest request) {
        return "sys/role/sys_role_add";
    }

    //新增
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult saveSysRole(SysRole sysRole, HttpServletRequest request) {
        String authCodes = request.getParameter("authCodes");
        JsonResult result = sysRoleService.saveSysRole(sysRole, authCodes);
        return result;
    }

    //修改
    @RequestMapping(value = "/preUpdate", method = {RequestMethod.POST, RequestMethod.GET})
    public String preUpdateSysRole(int recordId, ModelMap modelMap) {
        modelMap.addAttribute("sysRole", sysRoleService.getSysRole(recordId));
        return "sys/role/sys_role_update";
    }

    //修改
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult updateSysRole(SysRole sysRole, HttpServletRequest request) {
        String authCodes = request.getParameter("authCodes");
        JsonResult result = sysRoleService.updateSysRole(sysRole, authCodes);
        return result;
    }

    //删除
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult deleteSysRole(int recordId, HttpServletRequest request) {
        JsonResult result = sysRoleService.deleteSysRole(recordId);
        return result;
    }

    //导入
    @RequestMapping(value = "/import", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult importSysRole(SysRole sysRole, Model model) {
        JsonResult result = sysRoleService.importSysRole(sysRole);
        return result;
    }

    //导出
    @RequestMapping(value = "/export", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void exportSysRole() {
        sysRoleService.exportSysRole();
    }


    //显示详情
    @RequestMapping(value = "/detail", method = {RequestMethod.GET, RequestMethod.POST})
    public String getSysRoleDetail(@RequestParam(required = false) int recordId, ModelMap modelMap) {
        SysRole sysRole = sysRoleService.getSysRole(recordId);
        modelMap.addAttribute("sysRole", sysRole);
        return "sys/role/sys_role_detail";
    }

    @RequestMapping(value = "/roleAuthTree", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SysAuthTree> tree(String roleCode, HttpSession session) {
        List<SysAuthTree> authorityTreeList = sysRoleService.getSysRoleAuthTree(roleCode);
        //添加根节点
        SysAuthTree sysAuthTree = new SysAuthTree();
        sysAuthTree.setId("0");
        sysAuthTree.setName("权限菜单");
        sysAuthTree.setpId("-1");
        sysAuthTree.setOpen(true);
        authorityTreeList.add(sysAuthTree);
        return authorityTreeList;
    }
}