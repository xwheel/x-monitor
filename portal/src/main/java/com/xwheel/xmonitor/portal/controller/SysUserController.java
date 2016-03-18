package com.xwheel.xmonitor.portal.controller;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.JsonStore;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.portal.base.BaseController;
import com.xwheel.xmonitor.portal.entity.SysUser;
import com.xwheel.xmonitor.portal.service.SysUserService;
import com.xwheel.xmonitor.portal.service.VerifyService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author: leihang@live.cn
 * @date:   2016-03-15 05:29:22
 * @description:  一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController  extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private VerifyService verifyService;

	@Autowired
	private SysUserService sysUserService;


	//初始化
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(HttpSession httpSession, ModelMap modelMap) {
		return "sys/user/sys_user_list";
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
		Page<SysUser> page = new Page(pageNo, pageSize);
		page.setOrderBy(sort);
		page.setOrder(order);
		SysUser sysUser = new SysUser();
		sysUser.setOperName(request.getParameter("operName"));
		sysUser.setMail(request.getParameter("mail"));
		page = sysUserService.getSysUserListPage(page, sysUser);
		return new JsonStore(page.getCurrentPageResult(), page.getTotalCount());
	}

	//查询单个
	@RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public SysUser getSysUser(@RequestParam(required = false) int recordId) {
		return sysUserService.getSysUser(recordId);
	}


	//显示详情
	@RequestMapping(value = "/detail", method = {RequestMethod.GET, RequestMethod.POST})
	public String getSysRoleDetail(@RequestParam(required = false) int recordId, ModelMap modelMap) {
		SysUser sysUser = sysUserService.getSysUser(recordId);
		modelMap.addAttribute("sysUser", sysUser);
		return "sys/user/sys_user_detail";
	}

	//导出
	@RequestMapping(value = "/export", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void exportSysUser(HttpServletRequest request, HttpServletResponse response) {
		String templateFilePath = request.getSession().getServletContext().getRealPath("/template/用户信息模版.xlsx");
		List<SysUser> sysUserList = sysUserService.getSysUserListAll(new SysUser());
		sysUserService.exportSysUser(response, sysUserList, templateFilePath, "用户信息表" + new Date().getTime() + ".xlsx");
	}

	//进入新增页面
	@RequestMapping(value = "/preSave", method = {RequestMethod.GET, RequestMethod.POST})
	public String preSaveSysUser(HttpServletRequest request) {
		//后台权限判断
		return verifyService.permissionAccessPage(request, "sys/user/sys_user_add");
	}

	//新增
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult saveSysUser(SysUser newUser, HttpServletRequest request) {
		SysUser currentUser = getSessionUser(request.getSession());
//        KeyPair keyPair = (KeyPair) request.getSession().getAttribute(SessionConstant.SESSION_KEYPAIR);
//        newUser.setOperPassword(decrypt(keyPair, newUser.getOperPassword()));
		newUser.setStatus("0");
		newUser.setCreateTime(new Date());
		newUser.setCreatorId(currentUser.getOperId());
		newUser.setCreatorName(currentUser.getOperName());
		JsonResult result = sysUserService.saveSysUser(newUser);
		return result;
	}

	//修改
	@RequestMapping(value = "/preUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	public String preUpdateSysRole(int recordId, ModelMap modelMap) {
		modelMap.addAttribute("sysUser", sysUserService.getSysUser(recordId));
		return "sys/user/sys_user_update";
	}

	//修改
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult updateSysUser(SysUser sysUser, HttpServletRequest request) {
//        KeyPair keyPair = (KeyPair) request.getSession().getAttribute(SessionConstant.SESSION_KEYPAIR);
//        sysUser.setOperPassword(decrypt(keyPair, sysUser.getOperPassword()));
		JsonResult result = sysUserService.updateSysUser(sysUser);
		return result;
	}

	//删除
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult deleteSysUser(int recordId) {
		JsonResult result = sysUserService.deleteSysUser(recordId);
		return result;
	}

	private String getExcelVersion(MultipartFile editFile) {
		String filename = editFile.getOriginalFilename();
		return filename.substring(filename.lastIndexOf('.') + 1);
	}

	//导入
	@RequestMapping(value = "/import", method = {RequestMethod.POST})
	@ResponseBody
	public JsonResult importSysUser(MultipartFile fileName) {
		JsonResult result;
		try {
			String excelVersion = getExcelVersion(fileName);
			InputStream is = fileName.getInputStream();
			Workbook workbook;
			switch (excelVersion) {
				case "xls":
					workbook = new HSSFWorkbook(is);
					break;
				case "xlsx":
					workbook = new XSSFWorkbook(is);
					break;
				default:
					throw new IOException("Excel file format error");
			}
			result = sysUserService.importSysUser(workbook);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = new JsonResult(false, JsonResult.OPERATE_FAIL);
		}
		return result;
	}

}