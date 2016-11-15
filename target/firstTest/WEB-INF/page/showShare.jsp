<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/5/29
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>下载界面</h3>
    ${sessionScope.fileShareBean.userName}分享了文件${sessionScope.fileShareBean.fileName}
    <form action="sDownload" method="post">
        <input type="submit" value="下载">
    </form>
    <c:if test="${sessionScope.userLoginBean!=null}">
        <a href="file/shareKeep">转存到我的云盘</a>
        ${sessionScope.userLoginBean.username}
    </c:if>
</body>
</html>
