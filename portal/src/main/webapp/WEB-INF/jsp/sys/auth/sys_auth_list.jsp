<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8"/>
    <title>权限管理</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<jsp:include page="../../common/head.jsp"/>
<div class="content-wrapper" id="content-main">
    <!-- 内容头部-->
    <section class="content-header">
        <h1>权限管理</h1>
        <ol class="breadcrumb">
            <li><a href="${basePath}/menu/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容页面 -->
    <section class="content">
        <div style="width: 100%;height:100%">
            <div class="easyui-layout" style="height:768px;">
                <div data-options="region:'west',collapsible:false" title="权限管理" style="width:200px">
                    <div style="text-align: right;padding: 2px 2px 0px;">
                        <a href="javascript:void(0)" id="expand" class="easyui-linkbutton"
                           iconCls="icon-16-expand" title="展开"></a>
                        <a href="javascript:void(0)" id="collapse" class="easyui-linkbutton"
                           iconCls="icon-16-collapse" title="收拢"></a>
                        <a href="javascript:void(0)" id="refresh" class="easyui-linkbutton"
                           iconCls="icon-16-refresh" title="刷新"></a>
                    </div>
                    <div>
                        <ul id="authTree" class="ztree"></ul>
                    </div>
                </div>
                <div data-options="region:'center'">
                    <div>
                        <div class="panel-form">
                            <form id="authForm" action="${basePath}/sysAuth/update" method="post">
                                <div class="row">
                                    <input type="hidden" id="recordId" name="recordId"/>

                                    <div class="cell-label">上级权限编码:</div>
                                    <div class="cell-area"><input name="parentCode" class="easyui-textbox"
                                                                  disabled style="width:100%">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="cell-label">权限编码:</div>
                                    <div class="cell-text"><input id="authCode" name="authCode" class="easyui-textbox"
                                                                  required="required"
                                                                  style="width:100%"></div>
                                    <div class="cell-bank">&nbsp;</div>
                                    <div class="cell-label">权限名称:</div>
                                    <div class="cell-text"><input name="authName" class="easyui-textbox"
                                                                  required="required"
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
                                    <div class="cell-area"><input name="remark" class="easyui-textbox"
                                                                  data-options="multiline:true"
                                                                  style="width:100%;height:50px">
                                    </div>
                                </div>
                            </form>
                            <div>
                                <a href="javascript:void(0)" id="addNext" class="easyui-linkbutton"
                                   iconCls="icon-search">添加下级权限</a>
                                <a href="javascript:void(0)" id="update" class="easyui-linkbutton" iconCls="icon-clear">修改</a>
                                <a href="javascript:void(0)" id="delete" class="easyui-linkbutton" iconCls="icon-clear">删除</a>
                            </div>
                            <div id="dlg"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<jsp:include page="../../common/foot.jsp"/>
<script type="text/javascript">

    var setting = {
        view: {
            showLine: false
        },
        async: {
            enable: true,//异步处理
            url: '${basePath}/sysAuth/tree'
        },
        callback: {//回调函数，在这里可做一些回调处理
            onClick: onClick
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
    $(function () {
        //初始化列表
        $.fn.zTree.init($("#authTree"), setting, zNodes);
    });

    //刷新
    $("#refresh").click(function () {
        $.fn.zTree.init($("#authTree"), setting, zNodes);
    });
    //展开
    $("#expand").click(function () {
        $.fn.zTree.getZTreeObj("authTree").expandAll(true);
    });
    //收拢
    $("#collapse").click(function () {
        $.fn.zTree.getZTreeObj("authTree").expandAll(false);
    });
    //单击加载
    function onClick(event, treeId, treeNode, clickFlag) {
        if (treeNode.id != 0) {
            $('#authForm').show();
            $('#delete').show();
            $('#update').show();
            $('#authForm').form('load', "${basePath}/sysAuth/get?authCode=" + treeNode.id);
        } else {
            $('#authForm').hide();
            $('#delete').hide();
            $('#update').hide();
        }
    }

    //修改
    $("#update").click(function () {
        var authCode = $('#authCode').val();
        $('#authForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                return isValid;	// 返回false终止表单提交
            },
            success: function (data) {
                var jsonData = $.parseJSON(data);
                if (jsonData.success) {
                    parent.$.messager.show({title: "提示", msg: jsonData.resultMessage, position: "bottomRight"});
                    //刷新
                    $.fn.zTree.init($("#authTree"), setting, zNodes);
                    return true;
                } else {
                    parent.$.messager.show({title: "提示", msg: jsonData.resultMessage, position: "bottomRight"});
                    return false;
                }
            }
        });
        $('#authForm').submit();
    });

    //删除
    $("#delete").click(function () {
        var recordId = $('#recordId').val();
        $.messager.confirm('提示', '删除后无法恢复您确定要删除？', function (data) {
            if (data) {
                $.ajax({
                    type: 'post',
                    url: "${basePath}/sysAuth/delete?recordId=" + recordId,
                    success: function (data) {
                        if (data.success) {
                            $.messager.show({title: "提示", msg: data.resultMessage, position: "bottomRight"});
                            //刷新
                            $.fn.zTree.init($("#authTree"), setting, zNodes);
                            return true;
                        } else {
                            $.messager.show({title: "提示", msg: data.resultMessage, position: "bottomRight"});
                            return false;
                        }
                    }
                });
            }
        });
    });
    //添加子权限
    var addAuthPanel;
    $("#addNext").click(function () {
        var authCode = $('#authCode').val() || 0;
        addAuthPanel = $("#dlg").dialog({
            title: '权限信息',
            width: 600,
            height: 400,
            href: '${basePath}/sysAuth/preSave?authCode=' + authCode,
            maximizable: true,
            modal: true,
            buttons: [{
                text: '确认',
                handler: function () {
                    $("#addAuthForm").submit();
                    $.fn.zTree.init($("#authTree"), setting, zNodes);
                }
            }, {
                text: '取消',
                handler: function () {
                    addAuthPanel.panel('close');
                }
            }]
        });
    });


</script>
</body>
</html>