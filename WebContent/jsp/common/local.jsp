<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="locale" value="English" />
		<input type="hidden" name="command" value="change_locale" />
		<input type="hidden" name="jsppath" value="${pageContext.request.requestURI }" />
		<input type="submit" value="English" size="20" />
	</form>
	<form action="${pageContext.request.contextPath}/controller" method="post">
		<input type="hidden" name="locale" value="Russian" />
		<input type="hidden" name="command" value="change_locale" />
		<input type="hidden" name="jsppath" value="${pageContext.request.requestURI }" />
		<input type="submit" value="Русский" size="20" />
	</form>
