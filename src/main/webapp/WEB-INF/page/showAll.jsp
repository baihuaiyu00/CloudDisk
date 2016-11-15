<%@ page import="java.util.Date" %><%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/4/28
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title></title>
    <script type="application/javascript">
        function share(fileName) {
            <%
                String tokenValue = new Date().getTime()+"";
                request.setAttribute("token",tokenValue);
                session.setAttribute("token",tokenValue);
            %>
            window.location.href = "${pageContext.request.contextPath}/file/share?fileName="+fileName;

<%--//            self.location = 'http://www.baidu.com';--%>
            <%--alert("done");--%>
        }
    </script>
</head>

<body>
<a href="${pageContext.request.contextPath}/Home.htm">返回主页</a>
<h1 align="center">网盘</h1>
<a href="${pageContext.request.contextPath}/file/showAll?type=all">所有</a>
<a href="${pageContext.request.contextPath}/file/showAll?type=text">文本</a>
<a href="${pageContext.request.contextPath}/file/showAll?type=video">视频</a>
<a href="${pageContext.request.contextPath}/file/showAll?type=audio">音乐</a>
<a href="${pageContext.request.contextPath}/file/showAll?type=application">文档</a>
<a href="showAll?type=image">图片</a>
<a href="${pageContext.request.contextPath}/file/showAll?type=others">其他</a>
<hr/>
<c:if test="${list!=null}">

        <table width="88%" border="0">
            <table width="88%" border="1" align="left">

                <tr>
                    <th>选择</th>
                    <th>文件名</th>
                    <th>大小</th>
                    <th>上传时间</th>
                    <th>文件类型</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${list}" var="li" varStatus="vs">

                    <td bgcolor="${vs.index%2==0?'#C9C9C9':'#6192B0'}">
                        <td align="center" nowrap="nowrap">
                                <input type="checkbox"/>
                        </td>
                        <td align="center" id="fileName">${li.fileName}</td>
                        <td align="center">${li.fileSize}kb</td>
                        <td align="center">${li.updateTime}</td>
                        <td align="center">${li.fileType}</td>

                            <%--<td><a href="share?fileName=${li.fileName}" id="shareButton">分享</a> </td>--%>
                        <%--<td><button id="shareButton" onclick="share()">分享</button> </tdd--%>
                        <td><button onclick="share('${li.fileName}')">share</button></td>
                        <td><a href="download?fileName=${li.fileName}" methods="post">下载</a> </td>
                        <td><a href="del?fileName=${li.fileName} " methods="post">删除</a> </td>
                    </tr>
                </c:forEach>
            </table>
        </table>
</c:if>
<c:if test="${list==null}">
    <h1>对不起，您的云空空如也!</h1>
</c:if>
</body>
</html>
