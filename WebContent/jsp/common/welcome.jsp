<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionData.locale}" scope="session" />
<fmt:bundle basename="resource.i18n.interface" prefix="welcome.">
	<html>
<head>
<link rel="stylesheet" type="text/css"
	href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<title><fmt:message key="title" /></title>
</head>
<body class="text-center">

	<jsp:include page="/locale" />

<main role="main"  class="container-fluid justify-content-md-center">
	<jsp:include page="loginsignup.jsp" />


	<div class="text-muted"><fmt:message key="message" /></div>


	<div><fmt:message key="hotelinfo" /></div>

	<div><fmt:message key="contacts" /></div>
</main>
</body>
	</html>
</fmt:bundle>