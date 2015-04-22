<%--
  Created by IntelliJ IDEA.
  User: lei.chen
  Date: 4/20/2015
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index page111</title>
</head>
<body>
<h1>index page</h1>
<form action="/user" method="post">
    <input type="hidden" name="action" value="login">
    username: <input type="text" name="username" ><br/>
    password: <input type="password" name="password" ><br/>
    <input type="submit" value="LOGIN">
</form>
<br/>
<a href="user/signup.jsp">SIGN UP</a>
<br/>
${requestScope.message}
</body>
</html>
