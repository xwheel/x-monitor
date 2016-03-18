package com.xwheel.xmonitor.portal.service;

import com.xwheel.xmonitor.commons.beans.JsonResult;
import com.xwheel.xmonitor.commons.beans.Page;
import com.xwheel.xmonitor.commons.constants.SessionConstant;
import com.xwheel.xmonitor.commons.utils.ExcelCreateUtils;
import com.xwheel.xmonitor.portal.dao.SysUserDao;
import com.xwheel.xmonitor.portal.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.util.List;


/**
 * @author: leihang@live.cn
 * @date: 2016-03-15 05:29:22
 * @description: 一句话描述功能
 * 基本操作方法：新增、删除、修改、查询、导入、导出
 */
@Service
public class SysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SysUserDao sysUserDao;


    public SysUser getSysUserByUserIdOrEmail(String userId, String mail) {
        return sysUserDao.getSysUserByUserIdOrEmail(userId, mail);
    }

    /**
     * 用户登录系统信息验证
     *
     * @param userId   登录用户ID
     * @param password 密码
     * @return 0：成功，
     * 1:用户名、密码不能为空
     * 2:用户不存在
     * 3:密码错误
     * 4:用户已被锁定,无法登陆 (0:正常;1:锁定;)
     */
    public int validUserLogin(String userId, String password, KeyPair keyPair, HttpServletRequest request) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(password)) {
            return 1;
        }
        SysUser user = getSysUserByUserIdOrEmail(userId, null);
        if (user == null) {
            return 2;
        }
//        RSAUtil rsaUtil = new RSAUtil();
//        String pass = rsaUtil.decryptString(keyPair, password);
//        if (!user.getOperPassword().equals(DigestUtils.sha256Hex(userId + pass))) {
//            return 3;
//        }
        if ("1".equals(user.getStatus())) {
            return 4;
        }
        //登录成功
        request.getSession().setAttribute(SessionConstant.SESSION_USER, user);
        return 0;
    }

    public void exportSysUser(HttpServletResponse response, List<SysUser> dataList, String templatePath, String fileName) {
        try {
            InputStream is = new FileInputStream(templatePath);
            XSSFWorkbook wb = new XSSFWorkbook(is);
            //取得第一个表格
            XSSFSheet sheet = wb.getSheetAt(0);
            int startCellNum;
            int startRowNum = 1;
            for (int i = 0; i < dataList.size(); i++) {
                startCellNum = 0;
                SysUser sysUser = dataList.get(i);
                XSSFRow row = ExcelCreateUtils.getCellRow(sheet, startRowNum, null);
                ExcelCreateUtils.setCellValue(row, startCellNum++, sysUser.getOperName() + "", null);//用户ID
                ExcelCreateUtils.setCellValue(row, startCellNum++, sysUser.getOperName() + "", null);//用户名称
                ExcelCreateUtils.setCellValue(row, startCellNum++, sysUser.getMobile() + "", null);//手机号码
                ExcelCreateUtils.setCellValue(row, startCellNum++, sysUser.getMail() + "", null);//邮箱
                ExcelCreateUtils.setCellValue(row, startCellNum++, sysUser.getStatus() + "", null);//账号状态
                startRowNum++;
            }
            //输出表格
            createTable(response, wb, fileName);
        } catch (Exception e) {
            LOGGER.error("exportSysUser:", e);
        }
    }

    public JsonResult importSysUser(Workbook workbook) {
        JsonResult result = new JsonResult(true, JsonResult.OPERATE_SUCCESS);
        return result;
    }

    /**
     * 输出表格
     *
     * @param response
     * @param wb
     * @param fileName
     */
    public void createTable(HttpServletResponse response, XSSFWorkbook wb, String fileName) {
        try {
            response.reset();
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-Type", "application/force-download");
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Type", "application/download");
            response.setHeader("Cache-Control", "private, max-age=0, must-revalidate");
            response.setHeader("Pragma", "public");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");

            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            LOGGER.error("exportSysUser:", e);
        }
    }


    public SysUser getSysUser(int recordId) {
        return sysUserDao.getSysUser(recordId);
    }

    public Page<SysUser> getSysUserListPage(Page<SysUser> page, SysUser sysUser) {
        page.setCurrentPageResult(sysUserDao.getSysUserListPage(page, sysUser));
        page.setTotalCount(sysUserDao.getSysUserListPageCount(page, sysUser));
        return page;
    }

    public List<SysUser> getSysUserListAll(SysUser sysUser) {
        return sysUserDao.getSysUserListAll(sysUser);
    }

    public JsonResult saveSysUser(SysUser sysUser) {
        sysUserDao.saveSysUser(sysUser);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult deleteSysUser(int recordId) {
        sysUserDao.deleteSysUser(recordId);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

    public JsonResult updateSysUser(SysUser sysUser) {
        sysUserDao.updateSysUser(sysUser);
        return new JsonResult(true, JsonResult.OPERATE_SUCCESS);
    }

}