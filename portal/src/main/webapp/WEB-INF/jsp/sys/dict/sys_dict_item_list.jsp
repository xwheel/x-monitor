<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<%@include file="../../common/taglibs.jsp" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>字典信息</title>
</head>
<body>

<div style="width: 100%;height:100%">
    <div id="tb1" style="padding:2px 5px 10px 5px;">
        <div class="searchform">
            <form id="searchItemForm" action="">
                <label class="searchLabel">字典名称:</label><input class="textbox" type="text" name="dictItemName"
                                                                style="width:110px;height:22px"/>
                <label class="searchLabel">字典编码:</label><input class="textbox" type="text" name="dictItemCode"
                                                                style="width:110px;height:22px"/>
                <a href="javascript:void(0)" id="queryItem" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
                <a href="javascript:void(0)" id="clearItem" class="easyui-linkbutton" iconCls="icon-clear">清除</a>
            </form>
        </div>
        <!--按钮需要：权限控制-->
        <div>
            <a href="javascript:void(0)" id="addItem" class="easyui-linkbutton"><i
                    class="fa fa-fw fa-plus"></i>新增</a>
        </div>
    </div>
    <table id="dictItemGrid" class="easyui-layout" style="width:100%;height:100%"></table>
    <div id="dlgItem"></div>
</div>
<script type="text/javascript">
    //新增窗口
    var addItemPanel;
    //修改窗口
    var updateItemPanel;
    //数据表格
    var dictItemGrid;

    $(function () {
        //绑定回车事件
        $(document).keyup(function (e) {
            if (e.keyCode == 13) {
                $("#queryItem").click();
            }
        });
        //初始化数据表格
        dictItemGrid = $('#dictItemGrid').datagrid({
            method: "get",
            url: '${basePath}/sysDictItem/list?dictGroupCode=${dictGroupCode}',
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
                {field: 'dictGroupCode', title: '所属字典组编码', sortable: true, width: 100},
                {field: 'dictItemSeq', title: '序号', sortable: true, width: 100},
                {field: 'dictItemCode', title: '字典编码', sortable: true, width: 100},
                {field: 'dictItemName', title: '字典名称', sortable: true, width: 100},
                {field: 'dictItemNote', title: '描述', sortable: true, width: 100},
                {
                    field: 'oper', title: '操作', width: 100,
                    formatter: function (value, row, index) {
                        var deleteGroup = '<a href="javascript:del(' + row.recordId + ')"><i class="fa fa-fw fa-remove"></i>删  除</a>';
                        var modifyGroup = '<a href="javascript:upd(' + row.recordId + ')"><i class="fa fa-fw fa-pencil"></i>修  改</a>';
                        return "" + modifyGroup + '&nbsp;&nbsp;' + deleteGroup + "";
                    }
                }
            ]],
            enableHeaderClickMenu: false,
            enableHeaderContextMenu: false,
            enableRowContextMenu: false,
            toolbar: '#tb1'
        });
    });

    //查询事件
    $("#queryItem").click(function () {
        var obj = $("#searchItemForm").serializeObject();
        dictItemGrid.datagrid('reload', obj);
    });
    //清除事件
    $("#clearItem").click(function () {
        $('#searchItemForm').form('clear');
    });
    //新增弹窗
    $("#addItem").click(function () {
        addItemPanel = $("#dlgItem").dialog({
            title: '新增字典信息',
            width: 600,
            height: 400,
            href: '${basePath}/sysDictItem/preSave?dictGroupCode=${dictGroupCode}',
            maximizable: true,
            modal: true,
            buttons: [{
                text: '确认',
                handler: function () {
                    $("#addItemForm").submit();
                }
            }, {
                text: '取消',
                handler: function () {
                    addItemPanel.panel('close');
                }
            }]
        });
    });
    //删除事件
    function del(recordId) {
        parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function (data) {
            if (data) {
                $.ajax({
                    type: 'post',
                    url: "${basePath}/sysDictItem/delete?recordId=" + recordId,
                    success: function (data) {
                        if (data.success) {
                            $.messager.show({title: "提示", msg: data.resultMessage, position: "bottomRight"});
                            //刷新
                            dictItemGrid.datagrid('reload');
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
        updateItemPanel = $("#dlgItem").dialog({
            title: '修改字典',
            width: 600,
            height: 400,
            href: '${basePath}/sysDictItem/preUpdate?recordId=' + recordId,
            maximizable: true,
            modal: true,
            buttons: [{
                text: '修改',
                handler: function () {
                    $('#updateItemForm').submit();
                }
            }, {
                text: '取消',
                handler: function () {
                    updateItemPanel.panel('close');
                }
            }]
        });
    }

</script>
</body>
</html>
