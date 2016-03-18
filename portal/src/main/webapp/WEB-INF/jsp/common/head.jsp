<%@ page import="com.xwheel.xmonitor.commons.constants.SessionConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglibs.jsp" %>
<script>
    var basePath = '${basePath}';
    //请求菜单
    $(function () {
        $.ajax({
            url: "${basePath}/menu/getMenus",
            async: false,
            type: "POST",
            success: function (data) {
                var menuDiv = createMenu(data, '<%=session.getAttribute(SessionConstant.SESSION_MENU_ID)%>');
                $("#sidebar").html(menuDiv);
            }
        });

    });

    function createMenu(data, menuCode) {
        var tempMenu = '<li class="header">Monitor</li>';
        for (var i = 0; i < data.length; i++) {
            var menu = data[i];
            //获取值信息start
            var click = "";
            if (menu.menuUrl) {
                if ('#' != menu.menuUrl) {
                    var targetUrl = '${basePath}/' + menu.menuUrl;
                    click = 'onclick=goto("' + targetUrl + '","' + menu.authCode + '")';
                }
            }
            var menuIClass = "";
            if (menu.menuClass) {
                menuIClass = '<i class="' + menu.menuClass + '"></i>';
            }
            //获取值信息end

            //第一级菜单
            var isActive = menuCode.indexOf(menu.authCode) == 0 ? 'active' : ''; //active:激活，
            tempMenu += '<li class="treeview ' + isActive + '">';
            //url
            tempMenu += '<a href="#" ' + click + '>';
            //图标
            tempMenu += menuIClass;
            //菜单文字
            tempMenu += '<span>' + menu.authName + '</span>';
            //是否有子菜单，有，添加箭头图标
            if (menu.subSysAuth.length != 0) {
                tempMenu += '<i class="fa fa-angle-left pull-right"></i>';
                tempMenu += '<ul class="treeview-menu">';
            }
            tempMenu += '</a>';
            //子菜单
            if (menu.subSysAuth.length != 0) {
                var subMenuLList = menu.subSysAuth;
                for (var j = 0; j < subMenuLList.length; j++) {
                    var menuSub = subMenuLList[j];
                    tempMenu += createSubMenu(menuSub, menuCode);
                }
                tempMenu += '</ul>';
            }
            tempMenu += '</li>';
        }
        return tempMenu;
    }

    function createSubMenu(menu, menuCode) {
        var tempSubMenu = '';
        //多级菜单
        var isActive = menuCode.indexOf(menu.authCode) == 0 ? 'active' : ''; //active:激活，
        var click = '';
        if (menu.menuUrl) {
            if ('#' != menu.menuUrl) {
                var targetUrl = '${basePath}/' + menu.menuUrl;
                click = 'onclick=goto("' + targetUrl + '","' + menu.authCode + '")';
            }
        }
        var menuIClass = "";
        if (menu.menuClass) {
            menuIClass = '<i class="' + menu.menuClass + '"></i>';
        }

        tempSubMenu += '<li class="' + isActive + '">';
        //url
        tempSubMenu += '<a href="#" ' + click + '>';
        //图标
        tempSubMenu += menuIClass;
        //菜单文字
        tempSubMenu += '<span>' + menu.authName + '</span>';
        //是否有子菜单，有，添加箭头图标
        if (menu.subSysAuth.length != 0) {
            tempSubMenu += '<i class="fa fa-angle-left pull-right"></i>';
            tempSubMenu += '<ul class="treeview-menu">';
        }
        //加载子菜单
        if (menu.subSysAuth.length != 0) {
            var subMenuLList = menu.subSysAuth;
            for (var j = 0; j < subMenuLList.length; j++) {
                var menuSub = subMenuLList[j];
                tempSubMenu += createSubMenu(menuSub, menuCode);
            }
            tempSubMenu += '</ul>';
        }

        tempSubMenu += '</li>';
        return tempSubMenu;
    }

    //加载字典表信息
    var dictMap = [];
    if (dictMap.length == 0) {
        $.ajax('${basePath}/sysDictItem/getAllDictItems', {
            async: false,
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                if (data) {
                    $(data).each(function (index, dict) {
                        dictMap[dict.dictItemCode + dict.dictGroupCode + dict.language] = dict.dictItemName;
                    });
                }
            }
        });
    }

    function getDictValue(dictItemCode, dictGroupCode, defaultValue) {
        var locale = '${language}';
        var value = dictMap[dictItemCode + dictGroupCode + locale] || defaultValue;
        if (typeof(value) != "undefined" && value != "undefined") {
            return value;
        }
        return "";
    }

</script>
<div class="wrapper">
    <div class="main-header">

        <!-- Logo -->
        <a href="${basePath}/menu/index" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>门户</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>自助门户</b></span>
        </a>

        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <li class="dropdown messages-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-envelope-o"></i>
                            <span class="label label-success">4</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">你有4封未读消息</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <li><!-- start message -->
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${basePath}/plugins/adminlte/img/user2-160x160.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Support Team
                                                <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <!-- end message -->
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${basePath}/plugins/adminlte/img/user3-128x128.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                AdminLTE Design Team
                                                <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${basePath}/plugins/adminlte/img/user4-128x128.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Developers
                                                <small><i class="fa fa-clock-o"></i> Today</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${basePath}/plugins/adminlte/img/user3-128x128.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Sales Department
                                                <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="pull-left">
                                                <img src="${basePath}/plugins/adminlte/img/user4-128x128.jpg"
                                                     class="img-circle" alt="User Image">
                                            </div>
                                            <h4>
                                                Reviewers
                                                <small><i class="fa fa-clock-o"></i> 2 days</small>
                                            </h4>
                                            <p>Why not buy a new awesome theme?</p>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="footer"><a href="#">See All Messages</a></li>
                        </ul>
                    </li>
                    <!-- Notifications: style can be found in dropdown.less -->
                    <li class="dropdown notifications-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-bell-o"></i>
                            <span class="label label-warning">10</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">你有10个提醒</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-warning text-yellow"></i> Very long description here that
                                            may not fit into the page and may cause design problems
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-users text-red"></i> 5 new members joined
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-user text-red"></i> You changed your username
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="footer"><a href="#">View all</a></li>
                        </ul>
                    </li>
                    <!-- Tasks: style can be found in dropdown.less -->
                    <li class="dropdown tasks-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-flag-o"></i>
                            <span class="label label-danger">9</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">当前9个任务进行中</li>
                            <li>
                                <!-- inner menu: contains the actual data -->
                                <ul class="menu">
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <h3>
                                                Design some buttons
                                                <small class="pull-right">20%</small>
                                            </h3>
                                            <div class="progress xs">
                                                <div class="progress-bar progress-bar-aqua" style="width: 20%"
                                                     role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                     aria-valuemax="100">
                                                    <span class="sr-only">20% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <h3>
                                                Create a nice theme
                                                <small class="pull-right">40%</small>
                                            </h3>
                                            <div class="progress xs">
                                                <div class="progress-bar progress-bar-green" style="width: 40%"
                                                     role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                     aria-valuemax="100">
                                                    <span class="sr-only">40% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <h3>
                                                Some task I need to do
                                                <small class="pull-right">60%</small>
                                            </h3>
                                            <div class="progress xs">
                                                <div class="progress-bar progress-bar-red" style="width: 60%"
                                                     role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                     aria-valuemax="100">
                                                    <span class="sr-only">60% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                    <li><!-- Task item -->
                                        <a href="#">
                                            <h3>
                                                Make beautiful transitions
                                                <small class="pull-right">80%</small>
                                            </h3>
                                            <div class="progress xs">
                                                <div class="progress-bar progress-bar-yellow" style="width: 80%"
                                                     role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                     aria-valuemax="100">
                                                    <span class="sr-only">80% Complete</span>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <!-- end task item -->
                                </ul>
                            </li>
                            <li class="footer">
                                <a href="#">View all tasks</a>
                            </li>
                        </ul>
                    </li>
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${basePath}/plugins/adminlte/img/user2-160x160.jpg" class="user-image"
                                 alt="User Image">
                            <span class="hidden-xs">${userInfo.operName}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="${basePath}/plugins/adminlte/img/user2-160x160.jpg" class="img-circle"
                                     alt="User Image">

                                <p>
                                    ${userInfo.operName} - 系统管理员
                                    <small>Member since Nov. 2012</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="col-xs-4 text-center">
                                    <a href="#">Followers</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Sales</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Friends</a>
                                </div>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="${basePath}/security/logout" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>

        </nav>
    </div>
    <!-- 左侧菜单栏. contains the logo and sidebar -->
    <div class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <div class="sidebar">
            <!-- 左侧栏：用户信息 -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${basePath}/plugins/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${userInfo.operName}</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                </div>
            </div>
            <!-- 左侧栏：搜索栏 -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
                </div>
            </form>
            <!-- 左侧栏：菜单列表 -->
            <ul id='sidebar' class="sidebar-menu">

            </ul>
        </div>
        <!-- /.sidebar -->
    </div>
