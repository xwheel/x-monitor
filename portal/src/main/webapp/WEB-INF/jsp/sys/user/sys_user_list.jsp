<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>人员管理</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<jsp:include page="../../common/encrypt.jsp"/>
<jsp:include page="../../common/head.jsp"/>
<div class="content-wrapper" id="content-main">
    <!-- 内容头部-->
    <section class="content-header">
        <h1>
            人员管理
            <small>部门和人员信息维护</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${basePath}/menu/index"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容页面 -->
    <section class="content">
        <div style="width: 100%;height:500px">
            <div id="tb" style="padding:2px 5px 10px 5px;">
                <div class="searchform">
                    <form id="searchForm" action="">
                        <label class="searchLabel">用户名称:</label><input class="textbox" type="text" name="operName" id="operName"
                                                                        style="width:110px;height:22px"/>
                        <label class="searchLabel">用户邮箱:</label><input class="textbox" type="text" name="mail" id="mail"
                                                                        style="width:110px;height:22px"/>
                        <a href="javascript:void(0)" id="query" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
                        <a href="javascript:void(0)" id="clear" class="easyui-linkbutton" iconCls="icon-clear">清除</a>
                    </form>
                </div>
                <!--按钮需要：权限控制-->
                <div>
                    <a href="javascript:void(0)" id="add" class="easyui-linkbutton"><i class="fa fa-fw fa-plus"></i>新增</a>
                    <a href="javascript:void(0)" id="export" class="easyui-linkbutton"><i class="fa fa-fw fa-file-excel-o"></i>导出</a>
                </div>
            </div>
            <table id="userGrid" class="easyui-layout" style="width:100%;height: 600px"></table>
            <div id="dlg"></div>
        </div>
    </section>
</div>

<jsp:include page="../../common/foot.jsp"/>
</body>
</html>
<script type="text/javascript">
    var packageTypeList;
    $.ajax({
        url:'packagetype.do',
        dataType : 'json',
        type : 'POST',
        async:false,
        success: function (data){
           packageTypeList = data;
        }
    });

    //新增窗口
    var addPanel;
    //修改窗口
    var updatePanel;
    //数据表格
    var userGrid;
    //子列表
    var userDetailGrid;
    $(function () {
        //绑定回车事件
        $(document).keyup(function (e) {
            if (e.keyCode == 13) {
                $("#query").click();
            }
        });
        //初始化数据表格
        userGrid = $('#userGrid').datagrid({
            method: "get",
            url: '${basePath}/sysUser/list',
            fit: true,
            fitColumns: true,
            border: false,
            striped: true,
            idField: 'recordId',
            pagination: true,
            rowNumbers: true,
            pageNumber: 1,
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50],
            singleSelect: true,
            columns: [[
                {field: 'id', checkbox: true},
                {field: 'recordId', title: 'recordId', hidden: true},
                {field: 'operName', title: '操作员名称', sortable: true, width: 100},
                {field: 'status', title: '账号状态', sortable: true, width: 100,
                    formatter: function(value,row,index) {
                        return getDictValue(value,'oper_status');
                    }
                },
                {field: 'mail', title: '邮箱', sortable: true, width: 100},
                {field: 'mobile', title: '手机号码', sortable: true, width: 100},
                {
                    field: 'createTime',
                    title: '创建时间',
                    sortable: true,
                    width: 200,
                    formatter: DataFormmatter.DateFormatter
                },
                {
                    field: 'oper', title: '操作', width: 100,
                    formatter: function (value, row, index) {
                        var deleteUser = '<a href="javascript:del(' + row.recordId + ')"><i class="fa fa-fw fa-remove"></i>删  除</a>';
                        var modifyUser = '<a href="javascript:upd(' + row.recordId + ')"><i class="fa fa-fw fa-pencil"></i>修  改</a>';
                        return "" + modifyUser + '&nbsp;&nbsp;' + deleteUser + "";
                    }
                }
            ]],
            enableHeaderClickMenu: false,
            enableHeaderContextMenu: false,
            enableRowContextMenu: false,
            toolbar: '#tb',
            onDblClickRow: function(rowIndex, rowData){
                userDetailGrid = $("#dlg").dialog({
                    id:'userId',
                    title: '用户详情',
                    width: 800,
                    height: 600,
                    href: '${basePath}/sysUser/detail?recordId='+rowData.recordId,
                    maximizable: true,
                    modal: true
                });
            }
        });
    });

    //查询事件
    $("#query").click(function () {
        var obj = $("#searchForm").serializeObject();
        userGrid.datagrid('reload', obj);
    });
    //清除事件
    $("#clear").click(function () {
        $('#searchForm').form('clear');
    });

    //新增弹窗
    $("#add").click(function () {
        addPanel = $("#dlg").dialog({
            title: '新增用户',
            width: 600,
            height: 400,
            href: '${basePath}/sysUser/preSave',
            maximizable: true,
            modal: true,
            buttons: [{
                text: '确认',
                handler: function () {
                    $("#addForm").submit();
                }
            }, {
                text: '取消',
                handler: function () {
                    addPanel.panel('close');
                }
            }]
        });
    });

    //导出事件
    $('#export').click(function(){
        //查询条件
        var mail = $('#mail').val();
        var operName = $('#operName').val();
        var data = {operName: operName, mail: mail};
        downFile('${basePath}/sysUser/export', data);
    });

    //删除事件
    function del(recordId) {
        parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function (data) {
            if (data) {
                $.ajax({
                    type: 'post',
                    url: "${basePath}/sysUser/delete?recordId=" + recordId,
                    success: function (data) {
                        if (data.success) {
                            $.messager.show({title: "提示", msg: data.resultMessage, position: "bottomRight"});
                            //刷新
                            userGrid.datagrid('reload');
                            return true;
                        } else {
                            $.messager.show({title: "提示", msg: data.resultMessage, position: "bottomRight"});
                            return false;
                        }
                    }
                });
            }
        });
    }
    //修改弹窗
    function upd(recordId) {
        updatePanel = $("#dlg").dialog({
            title: '修改用户',
            width: 600,
            height: 400,
            href: '${basePath}/sysUser/preUpdate?recordId=' + recordId,
            maximizable: true,
            modal: true,
            buttons: [{
                text: '修改',
                handler: function () {
                    $('#updateForm').submit();
                }
            }, {
                text: '取消',
                handler: function () {
                    updatePanel.panel('close');
                }
            }]
        });
    }

</script>