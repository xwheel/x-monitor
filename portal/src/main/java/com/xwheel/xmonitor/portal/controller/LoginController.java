package com.xwheel.xmonitor.portal.controller;


import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.constants.SessionConstant;
import com.xwheel.xmonitor.commons.utils.encrpy.RSAUtil;
import com.xwheel.xmonitor.portal.base.BaseController;
import com.xwheel.xmonitor.portal.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.util.Date;
import java.util.Locale;

/**
 * @author: lei hang
 * @date: 2015年08月25日
 * @description:
 */
@Controller
@RequestMapping(value = "/security")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService sysUserService;

    //登陆
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpSession session, ModelMap modelMap) {
        logger.info("login in xmonitor ...");
        //生成秘钥
        RSAUtil rsaUtil = new RSAUtil();
        KeyPair keyPair = rsaUtil.getKeyPair();
        session.setAttribute(SessionConstant.SESSION_KEYPAIR, keyPair);
        getPublicKeyInfo(request);
        return "login/login";
    }

    //处理登陆
    @RequestMapping(value = "doLogin", method = {RequestMethod.POST})
    public String doLogin(HttpServletRequest request) {
        long startTime = new Date().getTime();


        String returnValue = "";
        HttpSession session = request.getSession();
        String operId = request.getParameter("operId");
        String pwd = request.getParameter("password");
        KeyPair keyPair = (KeyPair) session.getAttribute(SessionConstant.SESSION_KEYPAIR);
        int validCode = sysUserService.validUserLogin(operId, pwd, keyPair, request);
        if (validCode == 0) {
            //登陆成功
//            sysLogLoginService.saveSysLogLogin(operId, "登陆", "登陆成功", new Date().getTime() - startTime, request);
            returnValue = "redirect:/menu/index";
        } else {
            session.setAttribute(SessionConstant.SESSION_ERROR_CODE, validCode);
            RSAUtil rsaUtil = new RSAUtil();
            KeyPair keyPair2 = rsaUtil.getKeyPair();
            session.setAttribute(SessionConstant.SESSION_KEYPAIR, keyPair2);
//            sysLogLoginService.saveSysLogLogin(operId, "登陆", "登陆失败", new Date().getTime() - startTime, request);
            getPublicKeyInfo(request);
            returnValue = "login/login";
        }

        //设置国际化
        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        session.setAttribute(SessionConstant.SESSION_LOCALE, language);

        return returnValue;
    }

    //登出
    @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logOut(HttpServletRequest request) {//NOSONAR
//        sysLogLoginService.saveSysLogLogin("", "注销", "注销成功", 0, request);
        request.getSession().invalidate();//NOSONAR
        request.getSession().removeAttribute(SessionConstant.SESSION_MENU_ID);
        return "redirect:/security/login";
    }

    //注册
    // 注册
    @RequestMapping("/register")
    @ResponseBody
    public JsonResult register(HttpServletRequest request, HttpServletResponse response) {
//    public void register(SysUser sysUserModel, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Map<String, Object> result = new HashMap<String, Object>();
//        SysUser emailSysUser = sysUserService.getByProerties("email", sysUserModel.getEmail());
//        if (emailSysUser != null) {
//            result.put("result", -1);
//            writeJSON(response, result);
//            return;
//        }
//        SysUser sysUser = new SysUser();
//        sysUser.setUserName(sysUserModel.getUserName());
//        sysUser.setSex(sysUserModel.getSex());
//        sysUser.setEmail(sysUserModel.getEmail());
//        sysUser.setPhone(sysUserModel.getPhone());
//        sysUser.setBirthday(sysUserModel.getBirthday());
//        sysUser.setPassword(MD5.crypt(sysUserModel.getPassword()));
//        sysUser.setRole("ROLE_USER");
//        sysUser.setStatus(false);
//        sysUser.setLastLoginTime(new Date());
//        sysUserService.persist(sysUser);
//        request.getSession().setAttribute(SESSION_SYS_USER, sysUser);
//        result.put("result", 1);
//        writeJSON(response, result);
        return null;
    }
    //忘记密码

    //修改密码

}

