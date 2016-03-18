<html xmlns="http://www.w3.org/1999/xhtml">
<%--<%@include file="/common/taglibs.jsp" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>异常信息</title>
    <script type="text/javascript">
        function goBack() {
            //判断是否是弹出层的异常
            if (null != self.parent.document.getElementById("TB_window")) {
                self.parent.closeDialogWindow();
            } else {
                window.location = '${basePath}/${backUrl}';
            }
        }
    </script>
</head>
<body id="sl-div-tab" style="overflow-y: scroll">
<div style="width:500px;margin-left:auto; margin-right:auto; margin-top:50px;">
    <div class="fault-topBox">
        <div class="fault-topBox-left"></div>
        <div class="fault-topBox-mid">
            <span class="fault-topBox-text">出错了</span>
            <span class="fault-topBox-img"></span>
        </div>
        <div class="fault-topBox-right"></div>
    </div>
    <div class="clear"></div>
    <div class="fault-midBox">
        <div class="fault-midBox-left"></div>
        <div class="fault-midBox-mid">
            <div class="fault-midBox-div">
                <table class="fault-midBox-table">
                    <tr>
                        <td class="fault-midBox-table-td1"><img src="${basePath}/images/default/fail.gif"/></td>
                        <td class="fault-midBox-table-td2"><span
                                class="fault-midBox-text">
                            <s:if test="exception!=null&&exception.message!=null&&exception.message!=\"\"">
                                <s:property value="exception.message"/>
                            </s:if>
                            <s:else>
                                <s:text name="operation.error"/>
                            </s:else>
                        </span></td>
                    </tr>
                </table>
            </div>
            <div class="fault-midBox-line"></div>
            <div><input name="" type="button" value="返 回" class="btn-64" onclick="goBack()"/></div>
        </div>
        <div class="fault-midBox-right"></div>
    </div>
    <div class="clear"></div>
    <div>
        <div class="fault-botBox-left"></div>
        <div class="fault-botBox-mid"></div>
        <div class="fault-botBox-right"></div>
    </div>
    <div class="clear"></div>
    <script type="text/javascript">
        function toggleDebug(debugId) {
            var debugDiv = document.getElementById(debugId);
            if (debugDiv) {
                var display = debugDiv.style.display;
                if (display == 'none') {
                    debugDiv.style.display = 'block';
                } else if (display == 'block') {
                    debugDiv.style.display = 'none';
                }
            }
        }
    </script>

    <%--<a href="javascript:toggleDebug('debug');">【详细错误信息】</a>--%>

    <%--    <div style="display:none" id="debug">
            <s:property value="exceptionStack"/>
        </div>--%>
</div>
</body>
</html>
