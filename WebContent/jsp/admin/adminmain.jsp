<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionData.locale}" scope="session" />
<fmt:bundle basename="resource.i18n.interface" prefix="adminmain.">
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<jsp:include page="/locale" />
	<br />
	<hr />
	<jsp:include page="/loginlogout" />
	<br />
	<hr />
	<fmt:message key="adminmessage" />
	<hr />
	${errorRelogMessage} ${errorReSignupMessage}
	
	<hr />
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="command" value="to_all_orders" /> 
		<input type="hidden" name="currentPage" value="1">
	    <input type="hidden" name="recordsPerPage" value="10">
		<input type="submit" value="<fmt:message key="orders" />" />
	</form>
	<hr />
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="command" value="to_all_clients" /> 
		<input type="hidden" name="currentPage" value="1">
	    <input type="hidden" name="recordsPerPage" value="10">
		<input type="submit" value="<fmt:message key="clients" />" />
	</form>
	<hr />
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="command" value="to_admin_rooms" /> 
		<input type="submit" value="<fmt:message key="rooms" />" />
	</form>
	<hr />
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="command" value="to_admin_nationalities" /> 
		<input type="submit" value="<fmt:message key="nationalities" />" />
	</form>
	<hr />
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="command" value="to_admin_classes" /> 
		<input type="submit" value="<fmt:message key="roomclasses" />" />
	</form>
	<hr />
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="command" value="to_all_accounts" /> 
	    <input type="hidden" name="currentPage" value="1">
	    <input type="hidden" name="recordsPerPage" value="10">
		<input type="submit" value="<fmt:message key="accounts" />" />
	</form>
	<hr />
	<c:if test="${sessionData.login ne 'superadmin' }">
		<form action="${pageContext.request.contextPath}/controller" method="post">
			<input type="hidden" name="command" value="to_change_personal_data" /> 
			<input type="submit" value="<fmt:message key="changepersonaldata" />" />
		</form>
	<hr />
	</c:if>
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="command" value="to_all_emails" /> 
		<input type="hidden" name="currentPage" value="1">
	    <input type="hidden" name="recordsPerPage" value="10">
		<input type="submit" value="<fmt:message key="sendmessage" />" />
	</form>
</body>
</html>
</fmt:bundle>