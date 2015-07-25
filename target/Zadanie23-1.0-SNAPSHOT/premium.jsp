<%@page import="pl.jeeweb.zadanie23.entity.UserBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${(not empty cookie.username) && (not empty cookie.password)}">
        <c:if test="${empty userBean}">
            <jsp:forward page="LoginServlet">
                <jsp:param name="username" value="${cookie.username.value}" />
                <jsp:param name="password" value="${cookie.password.value}" />
                <jsp:param name="page" value="premium.jsp" />
            </jsp:forward>
        </c:if>
        <jsp:useBean id="userBean" scope="request" class="pl.jeeweb.zadanie23.entity.UserBean" />
        <c:if test="${(userBean.privilege == 'NORMAL')}">
            <jsp:forward page="LoginServlet">
                <jsp:param name="username" value="${cookie.username.value}" />
                <jsp:param name="password" value="${cookie.password.value}" />
                <jsp:param name="page" value="/errors/noAccess.jsp" />
            </jsp:forward>
        </c:if>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Premium Page</title>
            </head>
            <body>
                <h1>Premium Page!</h1>
                <p>Only those who have PREMIUM or ADMIN privilege ale alowed to visit this site!</p>
                <form action="login.jsp">
                    <input type="submit" value="Back" />
                </form>
            </body>
        </html>
    </c:when>
    <c:otherwise>
        <c:redirect url="index.jsp" />
    </c:otherwise>
</c:choose>
