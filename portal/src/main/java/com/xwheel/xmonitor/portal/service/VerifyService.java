package com.xwheel.xmonitor.portal.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
* @author: leihang@live.cn
* @date:   2015-08-27 22:22:30
* @description:  一句话描述功能
* 基本操作方法：验证服务
*/
@Service
public class VerifyService {

	public String permissionAccessPage(HttpServletRequest request,String pagePath) {
		String page="";
		if(true){
			page=pagePath;
		}else{
			page="error";
		}
		return page;
	}
	


}