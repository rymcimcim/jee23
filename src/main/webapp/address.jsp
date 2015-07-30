<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table border="1">
    <thead>
        <tr>

            <th>ID</th>
            <th>TYPE</th>
            <th>PROVINCE</th>
            <th>CITY</th>
            <th>POST CODE</th>
            <th>STREET</th>
            <th>HOUSE_NR</th>
            <th>FLAT_NR</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${addressList}" var="address">
            <c:url var="editURL" value="addressEdit.jsp">
                <c:param name="id" value="${address.addr_id}" />
                <c:param name="type" value="${address.type}" />
                <c:param name="province" value="${address.province}" />
                <c:param name="city" value="${address.city}" />
                <c:param name="post1" value="${address.post1}" />
                <c:param name="post2" value="${address.post2}" />
                <c:param name="street" value="${address.street}" />
                <c:param name="house_nr" value="${address.house_nr}" />
                <c:param name="flat_nr" value="${address.flat_nr}" />
            </c:url>
            <tr>
                <td>${address.addr_id}</td>
                <td>${address.type}</td>
                <td>${address.province}</td>
                <td>${address.city}</td>
                <td>${address.post1}-${address.post2}</td>
                <td>${address.street}</td>
                <td>${address.house_nr}</td>
                <td>${address.flat_nr}</td>
                <td> 
                    <a href="DelAddrServlet?id=<c:out value="${address.addr_id}" />">DELETE</a> 
                </td>
                <td> 
                    <a href="<c:out value='${editURL}'/>">EDIT</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

