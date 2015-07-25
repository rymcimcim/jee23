<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${(empty cookie.username) && (empty cookie.password)}">
        <c:if test="${userBean == null}">--%>
            <%@page contentType="text/html" pageEncoding="UTF-8"%>
            <!DOCTYPE html>
            <html>
                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>E-mail error</title>
                </head>
                <body>
                    <h1>E-mail error!</h1>
                    <p>Bad format of e-mail address.</p>
                    <form action="register.jsp">
                        <input type="submit" value="Back" />
                    </form>
                </body>
            </html>
        <%--</c:if>
    </c:when>
    <c:otherwise>
        <c:redirect url="../login.jsp" />
    </c:otherwise>
</c:choose>--%>