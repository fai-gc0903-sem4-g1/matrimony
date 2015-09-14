<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="UserBean" class="com.matrimony.database.UserDAO"/>
<jsp:useBean id="FriendBean" class="com.matrimony.database.FriendDAO"/>

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
    </div>        <a href="/${alias }/${user.username}">${user.name}</a>

    TAT CA DANH SACH BAN BE
    <table>
        <tr>
            <th>ID</th>
            <th>Ban be</th>
            <th>Yeu cau</th>
        </tr>        
        <c:forEach var="listUser" items="${UserBean.allAccounts()}">
            <tr>
                <c:set var="nameFromId" value="${sessionScope.user.userId}"/>
                <c:set var="nameToId" value="${listUser.userId}"/>
                <c:choose>
                    <c:when test="${FriendBean.CheckExist(nameFromId,nameToId)}">
                        <c:choose>
                            <c:when test="${FriendBean.CheckStt(nameFromId,nameToId)}">
                                <td>${listUser.email}</td> 
                                <td>
                                    <input type="submit" value="Da dui loi moi ket ban"/>
                                </td>
                                <td>
                                    <form action="cancelRequest" method="POST">
                                        <input name="usTo" type="hidden" value="${nameToId}"/>
                                        <input name="usFrom" type="hidden" value="${nameFromId}"/>
                                        <input type="submit" value="Cancel Request"/>
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>${listUser.email}</td>
                                <td><input type="submit" value="Da la ban be"/></td>
                                <td>
                                    <form action="removeFriend" method="POST">
                                        <input name="usToId" type="hidden" value="${nameFromId}"/>
                                        <input name="usFromId" type="hidden" value="${nameToId}"/>
                                        <input type="submit" value="Xoa ket ban"/>
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${FriendBean.CheckExist(nameToId,nameFromId)}">
                                <c:choose>
                                    <c:when test="${FriendBean.CheckStt(nameToId,nameFromId)}">
                                        <td>${listUser.email}</td>
                                        <td>
                                            <form action="acceptRequest" method="POST">
                                                <input name="usToId" type="hidden" value="${nameFromId}"/>
                                                <input name="usFromId" type="hidden" value="${nameToId}"/>
                                                <input type="submit" value="Chap nhan ket ban"/>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="NoAccept" method="POST">
                                                <input name="usToId" type="hidden" value="${list.userId}"/>
                                                <input name="usFromId" type="hidden" value="${nameToId}"/>
                                                <input type="submit" value="Tu choi"/>
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${listUser.email}</td>
                                        <td><input type="submit" value="Da la ban be"/></td>
                                        <td>
                                            <form action="removeFriend" method="POST">
                                                <input name="usToId" type="hidden" value="${list.userId}"/>
                                                <input name="usFromId" type="hidden" value="${nameToId}"/>
                                                <input type="submit" value="Xoa ket ban"/>
                                            </form>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <td>${listUser.email}</td>    
                                <td>
                                    <form action="sendRequest" method="POST">
                                        <input name="usToId" type="hidden" value="${nameToId}"/>
                                        <input name="usFromId" type="hidden" value="${nameFromId}"/>
                                        <input type="submit" value="Send Request"/>
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>

</jsp:body>
</t:layout>
