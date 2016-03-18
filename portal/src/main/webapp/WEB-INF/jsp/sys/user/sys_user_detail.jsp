<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglibs.jsp" %>
<html>
<head>
  <meta charset="utf-8"/>
  <title>人员详情</title>
</head>
<body>
<div>
  <div class="panel-form">
    <form id="detailForm" action="${basePath}/sysUser/update" method="post">
      <div class="row">
        <input type="hidden" name="recordId" value="${sysUser.recordId}"/>

        <div class="cell-label">登陆账号:</div>
        <div class="cell-text"><input name="operId" class="easyui-textbox" required="required"
                                      value="${sysUser.operId}" style="width:100%"></div>
        <div class="cell-bank">&nbsp;</div>
        <div class="cell-label">操作员名称:</div>
        <div class="cell-text"><input name="operName" class="easyui-textbox" required="required"
                                      value="${sysUser.operName}" style="width:100%"></div>
      </div>
      <div class="row">
        <div class="cell-label">手机号码:</div>
        <div class="cell-text"><input name="mobile" class="easyui-textbox"
                                      value="${sysUser.mobile}" style="width:100%">
        </div>
        <div class="cell-bank">&nbsp;</div>
        <div class="cell-label">邮箱:</div>
        <div class="cell-text"><input name="mail" class="easyui-textbox"
                                      value="${sysUser.mail}" style="width:100%">
        </div>
      </div>
    </form>
  </div>
</div>
</body>