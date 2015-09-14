<%-- 
    Document   : viewlistFriend
    Created on : Sep 13, 2015, 12:59:39 AM
    Author     : anhdh_a05370
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="myBean" class="com.matrimony.database.FriendDAO"/>
        <h1>Hello World!</h1>
        <c:forEach var="list" items="${myBean.allFriend}">
            ${list.userToId.userId}
        </c:forEach>
    </body>
</html>
