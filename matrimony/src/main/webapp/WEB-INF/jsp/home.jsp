<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
	<jsp:body>
	<c:set var="alias" value="matrimony" />
	<title>Trang chủ</title>
<h2>Dropdowns</h2>
  <p>The .dropdown class is used to indicate a dropdown menu.</p>
  <p>Use the .dropdown-menu class to actually build the dropdown menu.</p>                                          
  <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Tutorials
    <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">HTML</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">CSS</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">JavaScript</a></li>
      <li role="presentation" class="divider"></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">About Us</a></li>
    </ul>
  </div>
</div>

        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <h1>Trang chủ chính thứcassssssssssssssssssssssssssssss</h1>
        <a href="/${alias }/${user.username}">${user.name}</a>
        DANH SACH GOI Y KET BAN sfsdfasdfds
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
                    <td><a
						href="/DemoSpringHiber/controller/sendRequest/${list.accountId}/${sessionScope.id}">SendRequest</a></td>
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
                    <td><a
						href="/DemoSpringHiber/controller/acceptRequest/${list.accountId}/${sessionScope.id}">Accept Request</a></td>
                    <td><a
						href="/DemoSpringHiber/controller/cancelRequest/${list.accountId}/${sessionScope.id}">Cancel Request</a></td>
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
                    <td><a
						href="/DemoSpringHiber/controller/removeFriend/${list.accountId}/${sessionScope.id}">Remove Friend List</a></td>
                </tr>
            </table>
        </c:forEach>
        <form action="logout" method="POST">
            <input type="submit" value="Đăng xuất" />
        </form>
	</jsp:body>
</t:layout>