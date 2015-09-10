<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
	<jsp:body>
	<title>Trang chu</title>
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
	</jsp:body>
</t:layout>