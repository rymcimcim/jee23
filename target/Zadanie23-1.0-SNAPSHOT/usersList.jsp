<%@page import="pl.jeeweb.zadanie23.entity.User"%>
<%@page import="pl.jeeweb.zadanie23.util.CRUDRunner"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${(not empty cookie.username) && (not empty cookie.password)}">
        <c:if test="${empty userBean}">
            <jsp:forward page="LoginServlet">
                <jsp:param name="username" value="${cookie.username.value}" />
                <jsp:param name="password" value="${cookie.password.value}" />
                <jsp:param name="page" value="usersList.jsp" />
            </jsp:forward>
        </c:if>
        <jsp:useBean id="userBean" scope="request" class="pl.jeeweb.zadanie23.entity.UserBean" />
        <%
            List allUsers = CRUDRunner.retrieve();
        %>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>List of users</title>
            </head>
            <body>
                <h1>List of all users</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th><th>Username</th><th>Privilege</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < allUsers.size(); i++) {
                                User u = (User) allUsers.get(i);
                        %> 
                        <tr>
                            <td><%=u.getId()%></td><td><%= u.getUsername()%></td><td><%= u.getPrivilege()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
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