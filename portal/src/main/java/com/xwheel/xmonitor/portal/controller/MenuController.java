package com.xwheel.xmonitor.portal.controller;

import com.xwheel.xmonitor.commons.constants.SessionConstant;
import com.xwheel.xmonitor.portal.entity.SysAuth;
import com.xwheel.xmonitor.portal.service.SysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * @author: lei hang
 * @date: 2015年09月21日
 * @description:
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private SysAuthService sysAuthService;

    /**
     * 登陆完成后跳转页面
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String init(HttpSession session, HttpServletRequest request, ModelMap modelMap) {

        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        session.setAttribute(SessionConstant.SESSION_LOCALE, language);
        //设置菜单和子菜单
        SysAuth sysAuth = new SysAuth();
        sysAuth.setParentCode("0");
        List<SysAuth> menus = sysAuthService.getSysMenuListAll(sysAuth);
        if (menus != null) {
            modelMap.put("menus", menus);
            //菜单保存到session中
            session.setAttribute(language + "menus", menus);
        } else {
            session.setAttribute("canLogin", "0");
            return "redirect:/security/login";
        }
        //默认打开有权限的第一个页面，一般的用户为首页
//        request.getSession().setAttribute(SessionConstant.SESSION_MENU_ID, menus.get(0).getSubSysAuth().get(0).getAuthCode());
//        return "redirect:/" + menus.get(0).getSubSysAuth().get(0).getMenuUrl();
        //默认打开首页：
        return "menu/dashboard";
    }

    /**
     * 获取所有的菜单权限节点
     *
     * @return
     */
    @RequestMapping(value = "getMenus", method = RequestMethod.POST)
    @ResponseBody
    public List<SysAuth> getMenus(HttpSession session) {
        String language = session.getAttribute(SessionConstant.SESSION_LOCALE).toString();
        List<SysAuth> menus = (List<SysAuth>) session.getAttribute(language + "menus");
        return menus;
    }

    @RequestMapping(value = "setActiveMenuId", method = RequestMethod.POST)
    @ResponseBody
    public void setActiveMenuId(HttpServletRequest request) {
        String menuId = request.getParameter("menuId");
        request.getSession().setAttribute(SessionConstant.SESSION_MENU_ID, menuId);
    }

}


