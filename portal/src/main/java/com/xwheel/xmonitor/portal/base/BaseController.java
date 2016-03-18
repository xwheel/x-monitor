package com.xwheel.xmonitor.portal.base;

/**
 * @author: lei hang
 * @date: 2016年03月16日
 * @description:
 */


import com.xwheel.xmonitor.commons.constants.SessionConstant;
import com.xwheel.xmonitor.commons.utils.encrpy.RSAUtil;
import com.xwheel.xmonitor.portal.entity.SysUser;
import org.apache.commons.codec.binary.Hex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

public class BaseController {

    protected SysUser getSessionUser(HttpSession session) {
        return (SysUser) session.getAttribute(SessionConstant.SESSION_USER);
    }

    /**
     * 获取公钥的系数和公用指数并放入request返回页面使用
     * 用于密码前台加密，登录，新增、修改密码
     */
    protected void getPublicKeyInfo(HttpServletRequest request) {
        KeyPair keyPair = (KeyPair) request.getSession().getAttribute(SessionConstant.SESSION_KEYPAIR);
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String modulus = new String(Hex.encodeHex(publicKey.getModulus().toByteArray()));
        String exponent = new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray()));

        request.setAttribute("modulus", modulus);
        request.setAttribute("exponent", exponent);
    }

    /**
     * 对前台传入的加密密码进行解密
     *
     * @param keyPair
     * @param password
     * @return
     */
    protected String decrypt(KeyPair keyPair, String password) {
        return new RSAUtil().decryptString(keyPair, password);
    }
}
