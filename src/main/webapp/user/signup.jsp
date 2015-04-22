<%--
  Created by IntelliJ IDEA.
  User: lei.chen
  Date: 4/20/2015
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sign up page</title>
    <script src="../js/jquery-1.11.2.min.js"></script>
    <script src="../js/signup.js"></script>
</head>
<body>
<h1>sign up page</h1>
<form action="/user" method="post">
    <input type="hidden" name="action" value="signup">
    username: <input id="username" type="text" name="username"><span id="hint"></span><br/>
    password: <input type="password" name="password"><br/>
    <input type="submit" value="SIGN UP">
</form>
</body>
</html>