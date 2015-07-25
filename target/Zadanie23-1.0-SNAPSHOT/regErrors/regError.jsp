<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Error!</title>
    </head>
    <body>
        <h1>Registration Error!</h1>
        <p>User with <%=request.getParameter("username")%> username already exists.</p>
        <form action="register.jsp">
            <input type="submit" value="Back" />
        </form>
    </body>
</html>