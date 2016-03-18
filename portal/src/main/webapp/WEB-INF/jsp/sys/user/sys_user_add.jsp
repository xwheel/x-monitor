<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglibs.jsp" %>
<%@include file="../../common/encrypt.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>新增用户</title>
</head>
<body>
<div>

    <div class="panel-form">
        <form id="addForm" action="${basePath}/sysUser/save" method="post">
            <div class="row">
                <div class="cell-label">登陆账号:</div>
                <div class="cell-text"><input name="operId" class="easyui-textbox" required="required"
                                              style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">登陆密码:</div>
                <div class="cell-text"><input id="operPassword" name="operPassword" class="easyui-textbox" required="required"
                                              style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">操作员名称:</div>
                <div class="cell-text"><input name="operName" class="easyui-textbox" required="required"
                                              style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">手机号码:</div>
                <div class="cell-text"><input name="mobile" class="easyui-textbox" required="required"
                                              style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">邮箱:</div>
                <div class="cell-text"><input name="mail" class="easyui-textbox" required="required"
                                              style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">&nbsp;</div>
                <div class="cell-text">&nbsp;</div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#addForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if(isValid){
                    var pwdElement = $("#operPassword");
                    pwdElement.val(encrypt(pwdElement.val()));
                }
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    parent.$.messager.show({title: "提示", msg: jsonData.resultMessage, position: "bottomRight"});
                    addPanel.panel('close');
                    //刷新
                    userGrid.datagrid('reload');
                    return true;
                } else {
                    parent.$.messager.show({title: "提示", msg: jsonData.resultMessage, position: "bottomRight"});
                    return false;
                }
            }
        });
    });

</script>
</body>