<%--
  Created by IntelliJ IDEA.
  User: trndu
  Date: 1/11/2021
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<c:if test="${not empty sessionScope.USER}">
         <span style="color: red; ">
             Welcome, ${sessionScope.USER.fullname}
        </span>
    <form action="ProcessServlet">
        <input type="submit" value="Logout" name="btAction">
    </form>
</c:if>
<c:if test="${not empty sessionScope.USER}">
    <c:if test="${!sessionScope.USER.admin}">
        <a href="viewCart.jsp">
            Your Cart
            <c:if test="${not empty sessionScope.USER_CART.cart.values()}">
                (${sessionScope.USER_CART.cart.size()})
            </c:if>
        </a>
        <br/>
        <form action="ViewHistoryServlet">
            <input type="submit" value="View History">
        </form>
    </c:if>
</c:if>

<c:if test="${empty sessionScope.USER}">
    <form action="login.html">
        <input type="submit" value="Login">
    </form>
</c:if>
<c:if test="${sessionScope.USER.admin}">
    <form action="ProcessServlet">
        <input type="submit" value="New" name="btAction">
    </form>
</c:if>
<form action="ProcessServlet" method="post">
    <label>
        <input type="text" name="txtSearchValue" value=""/>
    </label>
    <input type="submit" value="SearchByName" name="btAction"/>
</form>
<form action="ProcessServlet" method="post">
    Min<label>
    <input type="text" name="txtMinPrice" value=""/>
</label>
    Max<label>
    <input type="text" name="txtMaxPrice" value=""/>
</label>
    <input type="submit" value="SearchByPrice" name="btAction"/>
</form>
<form action="ProcessServlet" method="post">
    <label>
        <select name="category">
            <c:forEach items="${sessionScope.LIST_CATEGORY}" var="category">
                <option value="${category.categoryID}">${category.categoryName}</option>
            </c:forEach>
        </select>
    </label>
    <input type="submit" value="SearchByCategory" name="btAction">
</form>
<c:if test="${not empty sessionScope.LIST_FOOD}">
    <table border="1">
        <thead>
        <tr>
            <th>Name</th>
            <th>Image</th>
            <th>Price</th>
            <th>Category</th>
            <c:if test="${sessionScope.USER.admin}">
                <th>Quantity</th>
                <th>Create Of Date</th>
                <th>Update</th>
                <th>Remove</th>
            </c:if>
            <c:if test="${!sessionScope.USER.admin}">
                <th>Add</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dto" items="${sessionScope.LIST_FOOD}" varStatus="">
            <form action="ProcessServlet" method="post" enctype="multipart/form-data" onsubmit="">
                <tr>
                    <td>
                        <c:if test="${sessionScope.USER.admin}">
                            <label>
                                <input type="text" name="txtFoodName" value="${dto.foodName}"/>
                                <input type="hidden" name="txtFoodID" value="${dto.foodID}"/>
                            </label>
                        </c:if>
                        <c:if test="${!sessionScope.USER.admin}">
                            ${dto.foodName}
                            <input type="hidden" name="txtFoodID" value="${dto.foodID}"/>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${empty dto.image}">
                            Chua co hinh
                        </c:if>
                        <c:if test="${not empty dto.image}">
                            <img src="${dto.image}" alt="Do an" width="50" height="50">
                        </c:if>
                        <br/>
                        <c:if test="${sessionScope.USER.admin}">
                            <input type="file" name="FILE">
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${sessionScope.USER.admin}">
                            <label>
                                <input type="text" name="txtFoodPrice" value="${dto.price}"/>
                            </label>
                        </c:if>
                        <c:if test="${!sessionScope.USER.admin}">
                            ${dto.price}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${sessionScope.USER.admin}">
                            <label>
                                <select name="category">
                                    <c:forEach items="${sessionScope.LIST_CATEGORY}" var="category">
                                        <c:if test="${category.categoryID == dto.categoryID}">
                                            <option value="${category.categoryID}"
                                                    selected>${category.categoryName}</option>
                                        </c:if>
                                        <c:if test="${category.categoryID != dto.categoryID}">
                                            <option value="${category.categoryID}">${category.categoryName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </label>
                        </c:if>
                        <c:if test="${!sessionScope.USER.admin}">
                            <c:forEach items="${sessionScope.LIST_CATEGORY}" var="category">
                                <c:if test="${category.categoryID == dto.categoryID}">
                                    <option value="${category.categoryID}" selected>${category.categoryName}</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </td>

                    <c:if test="${sessionScope.USER.admin}">
                        <td>
                            <label>
                                <input type="text" name="txtQuantity" value="${dto.quantity}"/>
                            </label>
                        </td>
                    </c:if>

                    <c:if test="${sessionScope.USER.admin}">
                        <td>
                                ${dto.createDate}
                        </td>
                    </c:if>
                    <td>
                        <c:if test="${sessionScope.USER.admin}">
                            <input type="submit" value="Update" name="btAction"/>
                        </c:if>
                        <c:if test="${!sessionScope.USER.admin}">
                            <input type="submit" value="Add" name="btAction"/>
                        </c:if>
                        <c:if test="${not empty requestScope.ERROR}">
                            ${requestScope.ERROR}
                        </c:if>
                    </td>
                    <c:if test="${sessionScope.USER.admin}">
                        <td>
                            <c:url var="removeLink" value="RemoveServlet">
                                <c:param name="pk" value="${dto.foodID}"/>
                            </c:url>
                            <a href="${removeLink}" onclick="return confrimDelete()">Remove</a>
                        </td>
                    </c:if>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
    <c:set var="all" scope="session" value="all"/>
    <c:set var="price" scope="session" value="price"/>
    <c:set var="name" scope="session" value="name"/>
    <c:set var="category" scope="session" value="category"/>
    <c:set var="type" scope="session" value="${sessionScope.LIST_TYPE}"/>
    <c:if test="${type == all}">
        <form action="ProcessServlet" method="post">
            <label>
                <select name="page">
                    <c:forEach items="${sessionScope.PAGE}" var="page">
                        <option value="${page}">${page}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="PageAll" name="btAction">
            </label>
        </form>
    </c:if>
    <c:if test="${type == category}">
        <form action="ProcessServlet" method="post">
            <label>
                <select name="page">
                    <c:forEach items="${sessionScope.PAGE}" var="page">
                        <option value="${page}">${page}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="PageCategory" name="btAction">
            </label>
        </form>
    </c:if>
    <c:if test="${type == name}">
        <form action="ProcessServlet" method="post">
            <label>
                <select name="page">
                    <c:forEach items="${sessionScope.PAGE}" var="page">
                        <option value="${page}">${page}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="PageName" name="btAction">
            </label>
        </form>
    </c:if>
    <c:if test="${type == price}">
        <form action="ProcessServlet" method="post">
            <label>
                <select name="page">
                    <c:forEach items="${sessionScope.PAGE}" var="page">
                        <option value="${page}">${page}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="PagePrice" name="btAction">
            </label>
        </form>
    </c:if>
</c:if>
<c:if test="${empty sessionScope.LIST_FOOD}">
    <h2>No food is matched!</h2>
    <form action="LoadFoodServlet">
        <input type="submit" value="Try Again!">
    </form>
</c:if>
<form action="LoadFoodServlet">
    <input type="submit" value="View All">
</form>
</body>
</html>
<script type="text/javascript">
    function confrimDelete(){
        let a = confirm("Are you sure?");
        return a;
    }
</script>
