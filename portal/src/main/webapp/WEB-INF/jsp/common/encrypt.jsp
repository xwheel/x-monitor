<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${basePath}/script/rsa/BigInt.min.js"></script>
<script type="text/javascript" src="${basePath}/script/rsa/Barrett.min.js"></script>
<script type="text/javascript" src="${basePath}/script/rsa/RSA.min.js"></script>
<script type="text/javascript">
  //前台加密密码
  function encrypt(password) {
    <%--var modulus = "${modulus}";--%>
    <%--var exponent = "${exponent}";--%>
    <%--setMaxDigits(131);--%>
    <%--var key = new RSAKeyPair(exponent, '', modulus);--%>
    <%--var newPassword = encryptedString(key, password);--%>
    return password;
  }
</script>

