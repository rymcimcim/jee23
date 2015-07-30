<%@page import="pl.jeeweb.zadanie23.entity.Address"%>
<%@page import="pl.jeeweb.zadanie23.util.CRUDRunner"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty cookie.username}">
    <c:redirect url="index.jsp" />
</c:if>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Address</title>
    </head>
    <body>
        <h1>Edit Address</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>TYPE</th>
                    <th>PROVINCE</th>
                    <th>CITY</th>
                    <th>POST CODE</th>
                    <th>STREET</th>
                    <th>HOUSE_NR</th>
                    <th>FLAT_NR</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${param.type}</td>
                    <td>${param.province}</td>
                    <td>${param.city}</td>
                    <td>${param.post1}-${param.post2}</td>
                    <td>${param.street}</td>
                    <td>${param.house_nr}</td>
                    <td>${param.flat_nr}</td>
                </tr>
            </tbody>
        </table><br /><br />
        <form action="EditAddrServlet">
            <table border="0">
                <tbody>
                    <tr>
                        <td> <input type="hidden" name= "id" value="${param.id}" />
                            Type: </td>
                        <td><select name="type">
                                <c:choose>
                                    <c:when test="${param.type eq 'zameldowania'}">
                                        <option value="zameldowania" selected="selected">Zameldowania</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="zameldowania">Zameldowania</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${param.type eq 'korespondencyjny'}">
                                        <option value="korespondencyjny" selected="selected">Korespondencyjny</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="korespondencyjny">Korespondencyjny</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${param.type eq 'pracy'}">
                                        <option value="pracy" selected="selected">Pracy</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="pracy">Pracy</option>
                                    </c:otherwise>
                                </c:choose>
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
                        <td><input type="text" name="city" value="${param.city}" /></td>
                    </tr>
                    <tr>
                        <td>Post Code: </td>
                        <td><input type="text" name="post1" value="${param.post1}" size="2" maxlength="2"/> - <input type="text" name="post2" value="${param.post2}" size="3" maxlength="3"/></td>
                    </tr>
                    <tr>
                        <td>Street: </td>
                        <td><input type="text" name="street" value="${param.street}" /></td>
                    </tr>
                    <tr>
                        <td>House Number: </td>
                        <td><input type="text" name="house_nr" value="${param.house_nr}" /></td>
                    </tr>
                    <tr>
                        <td>Flat Number: </td>
                        <td><input type="text" name="flat_nr" value="${param.flat_nr}" /></td>
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
