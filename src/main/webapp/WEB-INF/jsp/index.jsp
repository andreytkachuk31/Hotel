<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<!DOCTYPE HTML>
<html>
<head>
<title>MU Hotel/index</title>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="main_bg">
		<div class="wrap">
			<div class="main">

				<form method="post"
					action="${pageContext.request.contextPath}/index"
					name="user-room-form">
					<p align="left">
					<div>
						<p
							style="font-size: 25px; padding: 30px; text-indent: 10px; color: blue; font-family: arial, helvetica, sans-serif">
							Index page</p>
						<label for="greeting">Some information about hotel and images will be placed here</label>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>