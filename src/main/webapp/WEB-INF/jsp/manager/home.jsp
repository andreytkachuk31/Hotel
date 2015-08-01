<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ page pageEncoding="utf-8" %>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE HTML>
<html>
<head>
<title>MU <fmt:message key="hotel"/>/<fmt:message key="home"/></title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
		
				<form method="post" action="${pageContext.request.contextPath}/manager/home" name="home">
				<p style="font-size: 25px; text-align:center; padding: 10px; text-indent: 10px; color: blue; font-family: arial, helvetica, sans-serif">
							<fmt:message key="manager.home.page"/></p>
				<img  width="200" height="200" style="-webkit-filter: blur(1px); margin-bottom: 150px; vertical-align: top" align="middle" src="${pageContext.request.contextPath}/images/home.png"/>
					<div>
						<label style="margin-left:20px; margin-bottom: 100px;"  for="greeting">Welcome, ${user.firstName}</label>
						
					</div>
				</form>
			</div>
		</div>
	</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>	
</body>
</html>