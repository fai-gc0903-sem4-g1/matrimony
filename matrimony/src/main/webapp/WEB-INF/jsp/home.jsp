<%-- 
    Document   : home
    Created on : Sep 2, 2015, 2:53:35 PM
    Author     : SON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../bundle/bootstrap.jsp"/>
<jsp:include page="../bundle/jstl.jsp"/>
<jsp:useBean id="userDAO" class="com.matrimony.database.UserDAO"/>
<c:set var="user" value="${sessionScope.user}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Matrimony</title>
    </head>
    <body>
        <h1>Trang chủ chính thức</h1>
        <a href="/wedding_event_prj/${user.username}">${user.name}</a>
        DANH SACH GOI Y KET BAN
        <c:forEach var="list" items="${listSuggest}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Request friend</th>
                </tr>
                <tr>
                    <td>${list.accountId}</td>
                    <td>${list.email}</td>
                    <td>${list.gender}</td>
                    <td><a href="/DemoSpringHiber/controller/sendRequest/${list.accountId}/${sessionScope.id}">SendRequest</a></td>
                </tr>
            </table>
        </c:forEach>
        DANH SACH LOI MOI KET BAN
        <c:forEach var="list" items="${listRequest}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Request friend</th>
                </tr>
                <tr>
                    <td>${list.accountId}</td>
                    <td>${list.email}</td>
                    <td>${list.gender}</td>
                    <td><a href="/DemoSpringHiber/controller/acceptRequest/${list.accountId}/${sessionScope.id}">Accept Request</a></td>
                    <td><a href="/DemoSpringHiber/controller/cancelRequest/${list.accountId}/${sessionScope.id}">Cancel Request</a></td>
                </tr>
            </table>
        </c:forEach>
        DANH SACH BAN BE
        <c:forEach var="list" items="${listFriend}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Request friend</th>
                </tr>
                <tr>
                    <td>${list.accountId}</td>
                    <td>${list.email}</td>
                    <td>${list.gender}</td>
                    <td><a href="/DemoSpringHiber/controller/removeFriend/${list.accountId}/${sessionScope.id}">Remove Friend List</a></td>
                </tr>
            </table>
        </c:forEach>
        <form action="/wedding_event_prj/logout" method="POST">
            <input type="submit" value="Đăng xuất"/>
        </form>
    </body>
</html>
