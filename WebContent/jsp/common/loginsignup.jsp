<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionData.locale}" scope="session" />
<fmt:bundle basename="resource.i18n.interface" prefix="loginsignup.">
	<c:choose>
		<c:when test="${sessionData.role=='GUEST' }">
			<table>
				<tr>
					<td>
					<form action="${pageContext.request.contextPath}/controller" method="post">
							<input type="hidden" name="command" value="to_login" /> <input
								type="submit" value="<fmt:message key="login" />" size="20" />
						</form></td>
				</tr>
				<tr>
					<td>	
						<form action="${pageContext.request.contextPath}/controller" method="post">
						 <input type="hidden" name="command" value="to_signup" />
						 <input type="submit" value="<fmt:message key="signup" />"
							size="20" />
						</form>
					</td>
				</tr>
			</table>

		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<td>${sessionData.login}</td>
				</tr>
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/controller" method="post" >
							<input type="hidden" name="command" value="logout" /> 
							<input type="submit" value="<fmt:message key="logout" />" />
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<c:choose>
							<c:when test="${sessionData.role=='ADMIN' }">
								<form action="${pageContext.request.contextPath}/controller"
									method="post">
									<input type="hidden" name="command" value="back_to_admin_main" />
									<input type="submit" value="<fmt:message key="toadminmain" />" size="20" />
								</form>
							</c:when>
							<c:otherwise>
								<form action="${pageContext.request.contextPath}/controller"
									method="post">
									<input type="hidden" name="command" value="back_to_client_main" />
									<input type="submit" value="<fmt:message key="toclientmain" />" />
								</form>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
</fmt:bundle>