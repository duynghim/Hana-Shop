<%--
  Created by IntelliJ IDEA.
  User: trndu
  Date: 1/18/2021
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
        <h1>${sessionScope.USER.fullname}'s cart</h1>
        <c:if test="${not empty sessionScope.USER_CART}">
            <form action="UpdateCartServlet" method="post" onsubmit="">
                <table border="1">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.USER_CART.cart.values()}" var="foodDTO" varStatus="counter">
                        <tr>
                            <td>
                                    ${counter.count}
                            </td>
                            <td>
                                    ${foodDTO.foodName}
                            </td>
                            <td>
                                <img src="${foodDTO.image}" alt="Hinh"  width="50" height="50">
                            </td>
                            <td>
                                    ${foodDTO.price}
                            </td>
                            <td>
                                <label>
                                    <input type="text" name="txtQuantity" value="${foodDTO.quantity}">
                                    <input type="hidden" name="txtFoodID" value="${foodDTO.foodID}">
                                </label>
                            </td>
                            <td>
                                <input type="checkbox" name="chkRemove" value="${foodDTO.foodID}">
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>
                            <a href="searchAdmin.jsp">Continue Shopping</a>
                        </td>
                        <td>
                            TOTAL
                        </td>
                        <td>
                                ${sessionScope.USER_CART.toTal}
                        </td>
                        <td>

                        </td>
                        <td>
                            <input type="submit" value="UpdateCart" name="btAction">
                        </td>
                        <td>
                            <input type="submit" value="RemoveCart" name="btAction" onclick="return confrimDelete()">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </c:if>
        <c:if test="${empty sessionScope.USER_CART}">
            Your cart is empty
            <br/>
            <a href="searchAdmin.jsp">Continue Shopping</a>
        </c:if>
        <c:if test="${not empty requestScope.ERROR}">
            <table border="1">
                <thead>
                <tr>
                    <th>Warning</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.ERROR}" var="error">
                    <tr>
                        <td>${error.foodName} don't have enough quantity</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <br/>
        <c:if test="${not empty sessionScope.USER_CART}">
            Default Payment is COD
            <form action="ProcessServlet">
                <input type="submit" value="Order" name="btAction">
            </form>
        </c:if>
  </body>
</html>
<script type="text/javascript">
    function confrimDelete(){
        let a = confirm("Are you sure?");
        return a;
    }
</script>
