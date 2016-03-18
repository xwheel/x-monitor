<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set var="basePath" value="${pageContext.request.contextPath}"/>--%>
<%@include file="../../common/taglibs.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>新增权限菜单</title>
</head>
<body>
<div>

    <div class="panel-form">
        <form id="addAuthForm" action="${basePath}/sysAuth/save" method="post">
            <div class="row">
                <div class="cell-label">上级权限编码:</div>
                <div class="cell-area"><input name="parentCode" class="easyui-textbox"
                                              value="${parentCode}" readonly style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">权限编码:</div>
                <div class="cell-text"><input name="authCode" class="easyui-textbox" required="required"
                                              style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">权限名称:</div>
                <div class="cell-text"><input name="authName" class="easyui-textbox" required="required"
                                              style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">显示序号:</div>
                <div class="cell-text"><input name="displayNo" class="easyui-textbox"
                                              required="required"
                                              style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">权限类型:</div>
                <div class="cell-text">
                    <input class="easyui-combobox" name="authType"
                           data-options="
                                                url:'${basePath}/sysDictItem/getDictItems?dictGroupCode=sys_auth_type',
                                                method:'get',
                                                valueField:'dictItemCode',
                                                textField:'dictItemName',
                                                panelHeight:'auto'">
                </div>
            </div>
            <div class="row">
                <div class="cell-label">URL:</div>
                <div class="cell-text"><input name="menuUrl" class="easyui-textbox"
                                              style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">菜单样式:</div>
                <div class="cell-text"><input name="menuClass" class="easyui-textbox"
                                              style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">描述:</div>
                <div class="cell-area"><input name="remark" class="easyui-textbox" data-options="multiline:true"
                                              style="width:100%;height:50px">
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#addAuthForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    parent.$.messager.show({title: "提示", msg: jsonData.resultMessage, position: "bottomRight"});
                    addAuthPanel.panel('close');
                    //刷新
                    $.fn.zTree.init($("#authTree"), setting, zNodes);
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