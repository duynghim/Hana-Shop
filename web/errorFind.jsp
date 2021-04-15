<%--
  Created by IntelliJ IDEA.
  User: trndu
  Date: 1/19/2021
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${sessionScope.ERROR}</h1>
<form action="LoadFoodServlet">
    <input type="submit" value="Try Again">
</form>
</body>
</html>
