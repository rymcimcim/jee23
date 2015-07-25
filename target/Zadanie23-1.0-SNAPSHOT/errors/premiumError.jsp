<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Premium Error</title>
    </head>
    <body>
        <h1>Premium Error!</h1>
        <p><%= request.getParameter("premiumUsername")%> user does not exists. </p>
        <form action="grantPremium.jsp">
            <input type="submit" value="Back" />
        </form>
    </body>
</html>>
