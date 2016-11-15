<%@ page language="Java" contentType="text/html; charset=utf-8" isErrorPage="true" pageEncoding="utf-8"%>
<%
	/**
	 * 本页面是在客户查找的页面无法找到的情况下调用的
	 */
	response.setStatus(HttpServletResponse.SC_OK);
%>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Page couldn't be found.</title>
		<link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/user/css/style.css">
		
		<script language="JavaScript">
			function dosearch() {
			var sf=document.searchform;
			var submitto = sf.sengine.value + escape(sf.searchterms.value);
			window.location.href = submitto;
			return false;
			}
		</script>
	</head>
	<body>
		
		<div class="header">
			<img src="${pageContext.request.contextPath}/user/images/Logo_sample.png" />
		</div>
		
		<p class="error">404</p>
		
		<div class="content">
			<h2>凡人不可查看此网页</h2>
			
			<p class="text">
				不要乱搞哦!小伙子!这不是你这种凡人可以查看的网页!
			
				<form name="searchform" onSubmit="return dosearch();">
					<input type="hidden" name="sengine" value="http://www.google.com/search?q=site:www.yoursite.com+" />
					<input type="text" name="searchterms" class="inputform">
					<input type="submit" name="SearchSubmit" value="Search" class="button"> 
				</form>
				<!-- Change www.yoursite.com to your website domain -->
			</p>
				
			<p class="links">
				<a id="button" href="${pageContext.request.contextPath}/index.jsp">&larr; Back</a> <a href="${pageContext.request.contextPath}/index.jsp">Homepage</a> <a href="#">Portfolio</a> <a href="#">About Us</a> <a href="#">Blog</a>
				<!--These are links. You can change it to a page you want to by replacing the '#' with your url.-->
			</p>
		</div>
		
	</body>
</html>