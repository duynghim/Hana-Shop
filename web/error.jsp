<%--
  Created by IntelliJ IDEA.
  User: trndu
  Date: 1/15/2021
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${sessionScope.ERROR}</h1>
    <form action="login.html">
        <input type="submit" value="Try Again">
    </form>
</body>
</html>
