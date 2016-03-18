<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglibs.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>修改字典信息</title>
</head>
<body>
<div>

    <div class="panel-form">
        <form id="updateItemForm" action="${basePath}/sysDictItem/update" method="post">
            <div class="row">
                <input type="hidden" name="recordId" value="${dictItem.recordId}" />
                <div class="cell-label">所属字典组编码:</div>
                <div class="cell-text"><input name="dictGroupCode" class="easyui-textbox"
                                              value="${dictItem.dictGroupCode}" readonly style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">字典序号:</div>
                <div class="cell-text"><input name="dictItemSeq" class="easyui-textbox" required="required"
                                              value="${dictItem.dictItemSeq}"  style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">字典编码:</div>
                <div class="cell-text"><input name="dictItemCode" class="easyui-textbox" required="required"
                                              value="${dictItem.dictItemCode}"  style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">字典名称:</div>
                <div class="cell-text"><input name="dictItemName" class="easyui-textbox" required="required"
                                              value="${dictItem.dictItemName}"   style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">描述:</div>
                <div class="cell-area"><input name="dictItemNote" class="easyui-textbox" data-options="multiline:true"
                                              value="${dictItem.dictItemNote}"   style="width:100%;height:50px">
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#updateItemForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    parent.$.messager.show({title: "提示", msg: jsonData.resultMessage, position: "bottomRight"});
                    updateItemPanel.panel('close');
                    //刷新
                    dictItemGrid.datagrid('reload');
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