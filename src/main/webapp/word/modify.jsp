<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/4/18
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modify word page</title>
</head>
<body>
<h1>modify word page</h1>
<form action="/word">
    <input type="hidden" name="action" value="modify">
    <input type="hidden" name="id" value="${requestScope.word.id}">
    ENGLISH: <input type="text" name="english" value="${requestScope.word.english}"><br/>
    PROPERTY: <input type="text" name="property" value="${requestScope.word.property}"><br/>
    CHINESE: <input type="text" name="chinese" value="${requestScope.word.chinese}"><br/>
    LEVEL: <input type="text" name="level" value="${requestScope.word.level}"><br/>
    <input type="submit" value="MODIFY">
</form>
</body>
</html>
