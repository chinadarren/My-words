<%--
  Created by IntelliJ IDEA.
  User: lei.chen
  Date: 4/20/2015
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--<%@ include file="commons/inc.jsp"%>-->
<html>
<head>
    <title>home page</title>
</head>
<body>
<h1>home page</h1>
<p>welcome <!--<%=(String)request.getSession().getAttribute("username")%>-->
${sessionScope.username}</p>
<a href="/user?action=logout">LOGOUT</a>
<hr/>
<form action="/word" method="post">
    <input type="hidden" name="action" value="add">
    ENGLISH: <input type="text" name="english"><br/>
    PROPERTY: <input type="text" name="property"><br/>
    CHINESE: <input type="text" name="chinese"><br/>
    LEVEL: <input type="text" name="level"><br/>
    <input type="submit" value="ADD">
</form>
<hr />
<form action="/word" method="post">
    <input type="hidden" name="action" value="query">
    <input type="text" name="keyword">
    <input type="submit" value="SEARCH">
</form>
<hr/>
<table border="1">
    <c:choose>
        <c:when test="${sessionScope.words[0] ne null}">
            <tr>
                <th>COUNT</th>
                <th>INDEX</th>
                <th>ENGLISH</th>
                <th>PROPERTY</th>
                <th>CHINESE</th>
                <th>LEVEL</th>
                <th colspan="2">OPERATIONS</th>
            </tr>
        </c:when>
        <c:otherwise>
            NO RECORDS.
        </c:otherwise>
    </c:choose>
    <c:forEach var="word" items="${sessionScope.words}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${word.id}</td>
            <td>${word.english}</td>
            <td>${word.property}</td>
            <td>${word.chinese}</td>
            <td>${word.level}</td>
            <td><a href="/word?action=search&id=${word.id}">MODIFY</a></td>
            <td><a href="/word?action=remove&id=${word.id}" onclick="return del()">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
