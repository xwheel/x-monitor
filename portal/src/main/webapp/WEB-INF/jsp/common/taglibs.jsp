<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<c:set var="language" value="${sessionScope.locale}"/>
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="${basePath}/plugins/bootstrap/3.3.5/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${basePath}/plugins/bootstrap/3.3.5/css/bootstrap-theme.min.css" type="text/css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"
      type="text/css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" type="text/css">
<!-- Theme style -->
<%--<link rel="stylesheet" href="${basePath}/plugins/adminlte/css/AdminLTE.min.css" type="text/css">--%>
<link rel="stylesheet" href="${basePath}/plugins/adminlte/css/AdminLTE.css" type="text/css">
<link rel="stylesheet" href="${basePath}/plugins/adminlte/css/skins/_all-skins.min.css" type="text/css">
<!-- jqgrid -->
<link rel="stylesheet" type="text/css" media="screen"
      href="${basePath}/plugins/jqGrid/css/ui.jqgrid-bootstrap.css"/>
<!-- artdialo -->
<link rel="stylesheet" href="${basePath}/plugins/artdialog/ui-dialog.css">
<!-- easyui -->
<link rel="stylesheet" href="${basePath}/plugins/easyui/easyui-1.4.4/themes/bootstrap/easyui.css" type="text/css">
<link rel="stylesheet" href="${basePath}/plugins/easyui/easyui-1.4.4/themes/icon.css" type="text/css">
<%--<link rel="stylesheet" href="${basePath}/plugins/easyui/easyui-1.4.4/demo/demo.css" type="text/css">--%>
<!-- easyui扩展 -->
<link rel="stylesheet" href="${basePath}/plugins/easyui/easyui-extensions/jeasyui.extensions.css" type="text/css"/>
<!-- ztree样式 -->
<link rel="stylesheet" href="${basePath}/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>

<link rel="stylesheet" href="${basePath}/css/common.css" type="text/css"/>
<link rel="stylesheet" href="${basePath}/css/icon-extend.css" type="text/css"/>

<!-- jQuery 2.1.4 -->
<script src="${basePath}/plugins/jquery/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${basePath}/plugins/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${basePath}/plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="${basePath}/plugins/adminlte/js/app.min.js"></script>
<!-- 图标插件：Sparkline -->
<script src="${basePath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- 滚动条：SlimScroll 1.3.0 -->
<script src="${basePath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- HTML5图表绘制工具库:ChartJS 1.0.1 -->
<script src="${basePath}/plugins/chartjs/Chart.min.js"></script>

<!-- jqGrid-->
<script type="text/javascript" src="${basePath}/plugins/jqGrid/js/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="${basePath}/plugins/jqGrid/js/jquery.jqGrid.min.js"></script>

<!-- artdialog -->
<script type="text/javascript" src="${basePath}/plugins/artdialog/dialog-min.js"></script>
<!-- ajaxfileupload -->
<script type="text/javascript" src="${basePath}/plugins/jquery/ajaxfileupload.js"></script>
<!-- easy ui-->
<script src="${basePath}/plugins/easyui/easyui-1.4.4/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${basePath}/plugins/easyui/easyui-1.4.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<%--<script src="${basePath}/plugins/easyui/easyui-1.4.4/jquery.min.js" type="text/javascript" ></script>--%>

<!-- ztree -->
<script src="${basePath}/plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${basePath}/plugins/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>


<!-- this project -->
<script src="${basePath}/script/common.js"></script>
<script src="${basePath}/script/menu/menu.js"></script>
<script src="${basePath}/script/easyui/DataFormatter.js"></script>

<!--custom-->
<script>
    $.jgrid.defaults.width = 780;
    $.jgrid.defaults.styleUI = 'Bootstrap';
</script>
