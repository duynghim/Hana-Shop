<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: trndu
  Date: 1/20/2021
  Time: 2:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Food Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.ORDER_DETAIL_LIST}" var="detailDTO" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${detailDTO.foodName}</td>
                    <td>${detailDTO.quantity}</td>
                    <td>${detailDTO.price}</td>
                    <td>${detailDTO.price * detailDTO.quantity}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action="ViewHistoryServlet">
        <input type="submit" value="Back">
    </form>
</body>
</html>
