<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty cookie.username}">
    <c:redirect url="index.jsp" />
</c:if>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Address</title>
    </head>
    <body>
        <h1>Add your address!</h1>
        <form action="AddAddrServlet">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Type: </td>
                        <td><select name="type">
                                <option value="zameldowania">Zameldowania</option>
                                <option value="korespondencyjny">Korespondencyjny</option>
                                <option value="pracy">Pracy</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Province: </td>
                        <td><select name="province">
                                <option value="dolnoslaskie">Dolnoslaskie</option>
                                <option value="kujawsko-pomorskie">Kujawsko - Pomorskie</option>
                                <option value="lubelskie">Lubelskie</option>
                                <option value="lubuskie">Lubuskie</option>
                                <option value="lodzkie">Lodzkie</option>
                                <option value="malopolskie">Malopolskie</option>
                                <option value="mazowieckie">Mazowieckie</option>
                                <option value="opolskie">Opolskie</option>
                                <option value="podkarpackie">Podkarpackie</option>
                                <option value="podlaskie">Podlaskie</option>
                                <option value="pomorskie">Pomorskie</option>
                                <option value="slaskie">Slaskie</option>
                                <option value="swietokrzyskie">Swietokrzyskie</option>
                                <option value="warminsko-mazurskie">Warminsko - Mazurskie</option>
                                <option value="wielkopolskie">Wielkopolskie</option>
                                <option value="zachodniopomorskie">Zachodniopomorskie</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>City: </td>
                        <td><input type="text" name="city" value="" /></td>
                    </tr>
                    <tr>
                        <td>Post Code: </td>
                        <td><input type="text" name="post1" value="" size="2" maxlength="2"/> - <input type="text" name="post2" value="" size="3" maxlength="3"/></td>
                    </tr>
                    <tr>
                        <td>Street: </td>
                        <td><input type="text" name="street" value="" /></td>
                    </tr>
                    <tr>
                        <td>House Number: </td>
                        <td><input type="text" name="house_nr" value="" /></td>
                    </tr>
                    <tr>
                        <td>Flat Number: </td>
                        <td><input type="text" name="flat_nr" value="" /></td>
                    </tr>
                    <tr>
                        <td></td><td></td>
                    </tr>
                    <tr>
                        <td>
                            <c:set var="username" value="${cookie.username.value}" scope="session"/>
                            <input type="submit" value="Submit" /></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form><br />
        <form action="login.jsp">
            <input type="submit" value="Back" />
        </form>
    </body>
</html>
