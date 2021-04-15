<%--
  Created by IntelliJ IDEA.
  User: trndu
  Date: 1/16/2021
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Create New</title>
</head>
<body>
    <h1>Create New Food</h1>
    <form action="ProcessServlet" method="post" enctype="multipart/form-data">
        <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
        FoodName
        <label>
            <input type="text" name="txtFoodName" value="">
        </label>(3-20 chars)<br/>
        <c:if test="${not empty errors.foodNameLengthErr}">
            <span style="color: red; ">
                    ${errors.foodNameLengthErr}
            </span>
        </c:if>
        <br/>
        Category
        <label>
            <select name="category">
                <c:forEach items="${sessionScope.LIST_CATEGORY}" var="category">
                    <option value="${category.categoryID}" >${category.categoryName}</option>
                </c:forEach>
            </select>
        </label>
        <br/>
        <br/>
        Quantity
        <label>
        <input type="text" name="txtQuantity" value="">
        </label>
        <br/>
        <c:if test="${not empty errors.foodQuantityErr}">
            <span style="color: red; ">
                    ${errors.foodQuantityErr}
            </span>
        </c:if>
        <br/>
        Price
        <label>
            <input type="text" name="txtPrice" value="">
        </label>
        <c:if test="${not empty errors.foodPriceErr}">
            <span style="color: red; ">
                    ${errors.foodPriceErr}
            </span>
        </c:if>
        <br/>
        Image <input type="file" name="FILE">
        <br/>
        <input type="submit" value="Create" name="btAction">
    </form>
    <form action="LoadFoodServlet">
        <input type="submit" value="Back">
    </form>
</body>
</html>
