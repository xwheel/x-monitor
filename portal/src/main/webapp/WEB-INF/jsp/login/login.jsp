<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<%@include file="../common/encrypt.jsp" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>登录</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.4 -->
  <link href="${basePath}/plugins/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <!-- Font Awesome Icons -->
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <!-- Theme style -->
  <link href="${basePath}/plugins/adminlte/css/AdminLTE.css" rel="stylesheet" type="text/css" />
  <!-- iCheck -->
  <link href="${basePath}/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />
</head>
<body class="login-page">
<div class="login-box">
  <div class="login-logo">
     <b>自助门户系统</b>
  </div>
  <div class="login-box-body">
    <p class="login-box-msg">账号登录</p>
    <form action="${basePath}/security/doLogin" method="post" onsubmit="return loginSubmit()">
      <div class="form-group has-feedback">
        <input type="text" id="operId" name="operId" class="form-control" placeholder="账号或者邮箱名称" />
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" id="password" name="password" class="form-control" placeholder="密码" />
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"> 记住我
            </label>
          </div>
        </div><!-- /.col -->
        <div class="col-xs-4">
          <button  id="loginButtonId" type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
        </div><!-- /.col -->
      </div>

    </form>
    <a href="#">忘记密码</a><br>
    <div id="errorInfo" class="tips" style="display: none">
         <span class="fw14">错误消息</span>
    </div>
    <%--<a href="register.html" class="text-center">新用户注册</a>--%>
  </div><!-- /.login-box-body -->
</div><!-- /.login-box -->

<!-- jQuery 2.1.4 -->
<script src="${basePath}/plugins/jquery/jQuery-2.1.4.min.js" type="text/javascript"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="${basePath}/plugins/bootstrap/3.3.5/js/bootstrap.min.js" type="text/javascript"></script>
<!-- iCheck -->
<script src="${basePath}/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
  //登录
  function loginSubmit() {
    var operId = $.trim($('#operId').val());
    var password = $('#password').val();
    $('#operId').val(operId);
    if (operId == '' || operId == null) {
      $("#errorInfo").attr("style", "display:block");
      $("#errorInfo .fw14").html("用户名不能为空");
      $("#operId").focus();
      return false;
    }
    if (password == null || password == '') {
      $("#errorInfo").attr("style", "display:block");
      $("#errorInfo .fw14").html("密码不能为空");
      $('#password').focus();
      return false;
    }
    $('#password').val(encrypt(password));
    $('#loginButtonId').attr("disabled", true);
    return true;
  }
</script>
</body>
</html>
