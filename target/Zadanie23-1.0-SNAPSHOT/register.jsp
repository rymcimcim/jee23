<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${not empty cookie['username']}">
        <jsp:forward page="LoginServlet">
            <jsp:param name="username" value="${cookie.username.value}" />
            <jsp:param name="password" value="${cookie.password.value}" />
        </jsp:forward>
    </c:when>
    <c:otherwise>
        <!DOCTYPE html>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Register</title>
            </head>
            <body>
                <h1>Registration</h1>
                <form action="RegisterServlet">
                    <table border="0">
                        <tbody>
                            <tr>
                                <td>Username: </td><td><input type="text" name="username" value="" /></td>
                            </tr>
                            <tr>
                                <td>Password: </td><td><input type="password" name="password" value="" /></td>
                            </tr>
                            <tr>
                                <td>Confirm Password: </td><td><input type="password" name="cpassword" value="" /></td>
                            </tr>
                            <tr>
                                <td>E-mail: </td><td><input type="text" name="email" value="" /></td>
                            </tr>
                            <tr>
                                <td></td><td></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Register" /></td>
                            </tr>
                        </tbody>
                    </table><br />
                    Already have an account? <a href="index.jsp">Login</a>.
                </form>
            </body>
        </html>
    </c:otherwise>
</c:choose>
