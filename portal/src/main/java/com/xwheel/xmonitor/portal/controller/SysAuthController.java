package com.xwheel.xmonitor.portal.controller;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.JsonStore;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.base.BaseController;
import com.xwheel.xmonitor.portal.entity.SysAuth;
import com.xwheel.xmonitor.portal.service.SysAuthService;
import org.apache.commons.lang3.StringUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: leihang@live.cn
 * @date:   2016-03-16 11:19:32
 * @description:  一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Controller
@RequestMapping("/sysAuth")
public class SysAuthController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysAuthController.class);

	@Autowired
	private SysAuthService sysAuthService;

	//初始化
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(HttpSession httpSession, ModelMap modelMap) {
		return "sys/auth/sys_auth_list";
	}

	//查询列表
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public JsonStore list(Page<SysAuth> page , HttpServletRequest request) {
        SysAuth sysAuth = new SysAuth();
		page = sysAuthService.getSysAuthListPage(page, sysAuth);
        return new JsonStore(page.getCurrentPageResult(), page.getTotalCount());
	}

	//查询单个
	@RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public SysAuth getSysAuth(String authCode) {
		SysAuth model = null;
		if (StringUtils.isNotBlank(authCode)) {
			model = sysAuthService.getSysAuthByAuthCode(authCode);
		}
		if (model == null) {
			model = new SysAuth();
		}
		return model;
	}

	//新增
	@RequestMapping(value = "/preSave", method = {RequestMethod.POST,RequestMethod.GET})
	public String preSaveSysAuth(HttpServletRequest request) {
		return "sys/auth/sys_auth_add";
	}

	//新增
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult saveSysAuth(SysAuth sysAuth, HttpServletRequest request) {
		JsonResult result =	sysAuthService.saveSysAuth(sysAuth);
		return result;
	}

	//修改
	@RequestMapping(value = "/preUpdate", method = {RequestMethod.POST,RequestMethod.GET})
	public String preUpdateSysAuth(int recordId, ModelMap modelMap) {
		modelMap.addAttribute("sysAuth", sysAuthService.getSysAuth(recordId));
	return "sys/auth/sys_auth_update";
	}

	//修改
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult updateSysAuth(SysAuth sysAuth, HttpServletRequest request) {
        JsonResult result =	sysAuthService.updateSysAuth(sysAuth);
        return result;
    }
	//删除
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult deleteSysAuth(int recordId,HttpServletRequest request) {
        JsonResult result =	sysAuthService.deleteSysAuth(recordId);
        return result;
    }

	//导入
    @RequestMapping(value = "/import", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult importSysAuth(SysAuth sysAuth, Model model) {
        JsonResult result =	sysAuthService.importSysAuth(sysAuth);
        return result;
    }

	//导出
	@RequestMapping(value = "/export", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public void exportSysAuth() {
		sysAuthService.exportSysAuth();
	}

	@RequestMapping(value = "/tree", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<Map<String, Object>> tree(SysAuth SysAuth, HttpSession session) {
		//显示树菜单
		List<Map<String, Object>> items = new ArrayList<>();
		List<SysAuth> authList = sysAuthService.getSysAuthListAll(new SysAuth());
		//添加根节点
		Map<String,Object> item3 = new HashMap<>();
		item3.put("id","0");//id
		item3.put("name", "权限菜单"); //name
		item3.put("pId", "-1"); //父节点
		item3.put("open", true);
//        item3.put("iconSkin", "home");
		items.add(item3);
		for (SysAuth auth:authList) {
			Map<String,Object> item = new HashMap<>();
			item.put("id",auth.getAuthCode());//id
			item.put("name", auth.getAuthName()); //name
			item.put("pId", auth.getParentCode()); //父节点
//            item.put("iconSkin", "1".equals(auth.getAuthType())?"menu":"auth"); //菜单节点和权限节点分开显示
			items.add(item);
		}
		return items;
	}
}