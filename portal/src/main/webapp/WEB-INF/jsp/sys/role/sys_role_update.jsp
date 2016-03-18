<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglibs.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>修改角色</title>
</head>
<body>
<div>
    <div class="panel-form">
        <form id="updateForm" action="${basePath}/sysRole/update" method="post">
            <div class="row">
                <input type="hidden" name="recordId" value="${sysRole.recordId}"/>

                <div class="cell-label">角色编码:</div>
                <div class="cell-text"><input name="roleCode" class="easyui-textbox" required="required"
                                              value="${sysRole.roleCode}" style="width:100%"></div>
                <div class="cell-bank">&nbsp;</div>
                <div class="cell-label">角色名称:</div>
                <div class="cell-text"><input name="roleName" class="easyui-textbox" required="required"
                                              value="${sysRole.roleName}" style="width:100%"></div>
            </div>
            <div class="row">
                <div class="cell-label">角色描述:</div>
                <div class="cell-area"><input name="remark" class="easyui-textbox" data-options="multiline:true"
                                              value="${sysRole.remark}" style="width:100%;height:50px">
                </div>
                <input type="hidden" id="authCodes" name="authCodes"  />
            </div>
        </form>
    </div>
    <div style=" padding: 0px 30px;float:left;height: 100%">
        <ul id="authTree" class="ztree"></ul>
    </div>
</div>
<script type="text/javascript">
    //权限列表
    var setting = {
        view: {
            showLine: false
        },
        check: {
            enable: true
        },
        async: {
            enable: true,//异步处理
            url: '${basePath}/sysRole/roleAuthTree?roleCode=${sysRole.roleCode}'
        },
        callback: {//回调函数，在这里可做一些回调处理
            onCheck: onCheck
        },
        data: {
            simpleData: {
                /**
                 如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey / rootPId
                 并且让数据满足父子关系。*/
                enable: true,//true / false 分别表示 使用 / 不使用 简单数据模式
                idKey: "id",
                pIdKey: "pId",
                rootPId: 0
            }
        }
    };

    var zNodes = [];
    //单击加载
    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("authTree");
        var auths = zTree.getCheckedNodes(true);
        var authCodes = '';
        for (var i = 0; i < auths.length; i++) {
            authCodes += auths[i].id + ",";
        }
        if (authCodes.length > 1) {
            authCodes = authCodes.substr(0, authCodes.length - 1);
        }
        $('#authCodes').val(authCodes);

    }

    $(function () {
        //初始化列表
        $.fn.zTree.init($("#authTree"), setting, zNodes);
        $('#updateForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    parent.$.messager.show({title: "提示", msg: jsonData.resultMessage, position: "bottomRight"});
                    addPanel.panel('close');
                    //刷新
                    roleGrid.datagrid('reload');
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