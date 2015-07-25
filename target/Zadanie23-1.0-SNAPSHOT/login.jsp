<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${(not empty cookie.username) && (not empty cookie.password)}">
        <c:if test="${empty userBean}">
            <%--<c:set var="username" value="${cookie.username.value}" />
            <%
                UserBean ub = new UserBean();
                User u = new User();
                u = CRUDRunner.retrieveFromUsername((String)pageContext.getAttribute("username"));
                ub.setUsername(u.getUsername());
                ub.setId(u.getId());
                ub.setEmail(u.getEmail());
                ub.setPrivilege(u.getPrivilege());
                ub.setDescription();
                request.setAttribute("userBean", ub);
            %>--%>

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

