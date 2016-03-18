<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglibs.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>修改字典组</title>
</head>
<body>
<div>

    <div class="panel-form">
        <form id="updateForm" action="${basePath}/sysDictGroup/update" method="post">
            <div class="row">
                <input type="hidden" name="recordId" value="${dictGroup.recordId}" />
                <div class="cell-label">字典组编码:</div>
                <div class="cell-text"><input name="dictGroupCode" class="easyui-textbox" required="required"
                                              value="${dictGroup.dictGroupCode}" style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">字典组名称:</div>
                <div class="cell-text"><input name="dictGroupName" class="easyui-textbox" required="required"
                                              value="${dictGroup.dictGroupName}" style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">启用开始时间:</div>
                <div class="cell-text"><input name="enableStartTime" class="easyui-datebox" required="required"
                                              value="${dictGroup.enableStartTime}"  style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">启用结束时间:</div>
                <div class="cell-text"><input name="enableEndTime" class="easyui-datebox" required="required"
                                              value="${dictGroup.enableEndTime}" style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">可自定义:</div>
                <div class="cell-area"><select name="allowCustomizationFlag" class="easyui-combobox" required="true"
                                               value="${dictGroup.allowCustomizationFlag}" style="width:130px">
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
                </div>
            </div>
            <div class="row">
                <div class="cell-label">字典组描述:</div>
                <div class="cell-area"><input name="dictGroupNote" class="easyui-textbox" data-options="multiline:true"
                                              value="${dictGroup.dictGroupNote}" style="width:100%;height:50px">
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#updateForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    parent.$.messager.show({title: "提示", msg: jsonData.resultMessage, position: "bottomRight"});
                    updatePanel.panel('close');
                    //刷新
                    dictGroupGrid.datagrid('reload');
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