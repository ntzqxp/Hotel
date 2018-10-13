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
<body class="text-center">

	<jsp:include page="/locale" />

	<main role="main" class="container-fluid justify-content-md-center">
	<div class="row">
		<fmt:message key="message" />
	</div>
	<br />
	<div class="container-fluid">
		<jsp:include page="loginsignup.jsp" />
	</div>
	</main>
</body>
	</html>
</fmt:bundle>