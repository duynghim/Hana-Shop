<%--
  Created by IntelliJ IDEA.
  User: trndu
  Date: 1/20/2021
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>${sessionScope.USER.fullname} history</h2>
    <c:if test="${not empty requestScope.ORDER_LIST}">
        <form action="SearchHistoryServlet">
            <label>
                <input type="date" name="createDate">
                <input type="submit" value="Search">
            </label>
        </form>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Total</th>
                    <th>Date</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.ORDER_LIST}" var="orderDTO" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${orderDTO.total}</td>
                        <td>${orderDTO.createOfDate}</td>
                        <td>
                            <form action="ViewHistoryDetailServlet">
                                <input type="hidden" name="txtOrderID" value="${orderDTO.orderID}">
                                <input type="submit" value="Detail">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty requestScope.ORDER_LIST}">
        <h1>You don't have any order</h1>
    </c:if>
    <a href="searchAdmin.jsp">
        Back
    </a>
</body>
</html>
