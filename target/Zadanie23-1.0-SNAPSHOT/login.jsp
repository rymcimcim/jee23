<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${(not empty cookie.username) && (not empty cookie.password)}">
        <c:if test="${empty userBean}">
            <jsp:forward page="LoginServlet">
                <jsp:param name="username" value="${cookie.username.value}" />
                <jsp:param name="password" value="${cookie.password.value}" />
            </jsp:forward>
        </c:if>
        <jsp:useBean id="userBean" scope="request" class="pl.jeeweb.zadanie23.entity.UserBean" />
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Logged in</title>
            </head>
            <body>
                <h1>Welcome <jsp:getProperty name="userBean" property="username" />! You are logged in!</h1>
                <jsp:getProperty name="userBean" property="description" />
                
                <c:if test="${not empty addressList}">
                    <c:import url="address.jsp" />
                </c:if>
                <br />
                <form action="addressForm.jsp">
                    <input type="submit" value="Add New" />
                </form><br /><br /><br />
                <form action="usersList.jsp">
                    <input type="submit" value="Users List" />
                </form>
                <form action="premium.jsp">
                    <input type="submit" value="Premium" />
                </form>
                <form action="grantPremium.jsp">
                    <input type="submit" value="Grant Premium" />
                </form>
                <form action="LogoutServlet">
                    <c:remove scope="request" var="userBean" />
                    <input type="submit" value="Logout" />
                </form>
            </body>
        </html>
    </c:when>
    <c:otherwise>
        <c:redirect url="index.jsp" />
    </c:otherwise>
</c:choose>

