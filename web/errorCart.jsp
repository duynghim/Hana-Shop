<%--
  Created by IntelliJ IDEA.
  User: trndu
  Date: 1/20/2021
  Time: 12:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${requestScope.ERROR}
<form action="viewCart.jsp" method="post">
    <input type="submit" value="Try Again!">
</form>
</body>
</html>
