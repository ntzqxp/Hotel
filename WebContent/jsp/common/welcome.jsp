<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionData.locale}" scope="session" />
<fmt:bundle basename="resource.i18n.interface" prefix="welcome.">
	<html>
<head>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">

<title><fmt:message key="title" /></title>
</head>
<body>
	<jsp:include page="/locale" />
	<div class="container justify-content-center">
		<div class="row">
			<div class="col-4"></div>
			<div class="col">
				<div class="std-text-color text-center">
					<fmt:message key="message" />
				</div>
				<br />
				<jsp:include page="loginsignup.jsp" />
				<br />
				<form action="${pageContext.request.contextPath}/controller"
					method="post">
					<input type="hidden" name="command" value="hotel_info" /> <input
						class="btn btn-sm btn-primary btn-block btn-outline-primary"
						type="submit" value="<fmt:message key="hotelinfo" />" size="20" />
				</form>
			</div>
			<div class="col-4"></div>
		</div>
	</div>
</body>
	</html>
</fmt:bundle>
