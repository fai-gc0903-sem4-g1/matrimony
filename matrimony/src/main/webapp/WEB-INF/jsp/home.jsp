<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="UserBean" class="com.matrimony.database.UserDAO" />
<jsp:useBean id="FriendBean" class="com.matrimony.database.FriendDAO" />
<jsp:useBean id="matrimony" class="com.matrimony.database.Matrimony" />

<t:layout>
	<jsp:body>
	
	<c:set var="alias" value="matrimony" />
	<c:set var="peopleSuggestList"
			value="${matrimony.getSuggestUsers(sessionScope.user) }"
			scope="request" />
	<style>
#container {
	padding: 0px 30px 0 30px;
}

#person-panel {
	border: solid 2px #f7f7f7;
	margin-left: 0px;
	margin-top: 10px;
	padding: 12px 0px;
}

#person-name {
	font-weight: bold;
	color: #116CBB;
}

#person-btna {
	color: #8DBAD9;
	cursor: pointer;
}
</style>
	<div id="container" class='row'>
	<div id='left' class='col-lg-2' style='width: 233px;'>
		<img id='avartar' alt='avatar' style='height: 200px; width: 200px;'
					src='${userAvatarFolder}/${sessionScope.user.avatarPhoto }' />
		<br />
		<br />
		<span id='name' style='font-weight: bold;'>${sessionScope.user.name }</span>
		<br /><br />
		<ul class="list-group">
		  <li class="list-group-item"><a href="#">My friend</a> <span
						class="badge">14</span></li>
		  <li class="list-group-item"><a href="#">My favorite people</a> <span
						class="badge">33</span></li>
		</ul>
	</div>
	
	<div id='center' class='col-lg-6'
				style='background-color: #ffffff; border: solid 2px #f4f4f4; border-radius: 6px; width: 726px;'>
	Những người phù hợp với bạn
	
		<div id='tblPeople'>
		<c:forEach var="i" items="${peopleSuggestList }">
			<div id='person-panel' class='row col-sm-12'>
			
				<div class='col-sm-2'>
				<img id='person-avatar' alt='person-avatar'
									src='/matrimony/resources/profile/avatar/${i.avatarPhoto }'
									style='height: 70px; width: 70px;' />
				</div>
			
				<div id='person-left' class='col-sm-5'>
					<span id='person-name'>${i.name }</span><br />
					<span id='person-age'>${i.birthday }</span><br />
					<span id='person-gender'>${i.gender }</span><br />
					<span id='person-btna'>Send message</span>
					<span id='person-btna'>1 friend</span>
				</div>
				<div id='person-right' class='col-sm-4'>
					<span id='person-name'>Viet nam</span><br />
					<span id='person-city'>Ha noi</span><br />
					<span id='person-status'>Alone</span><br />
					<span id='person-btna'>Add friend</span>
				</div>
			</div>
			</c:forEach>
			<div class='row col-sm-12' style='text-align: center;'>
				<h4>Trống</h4>
			</div>
			</div>
			
		</div>
		<div id='right' class='col-lg-3'>
		<div class="panel panel-default">
	  <div class="panel-heading">
		<h3 class="panel-title">Friends</h3>
	  </div>
	  <div class="panel-body">
		<table>
        <tr>
            <th>ID</th>
            <th>Ban be</th>
            <th>Yeu cau</th>
        </tr>        
        <c:forEach var="listUser" items="${UserBean.allAccounts()}">
            <tr>
                <c:set var="nameFromId"
						value="${sessionScope.user.userId}" />
                <c:set var="nameToId" value="${listUser.userId}" />
                <c:choose>
                    <c:when
							test="${FriendBean.CheckExist(nameFromId,nameToId)}">
                        <c:choose>
                            <c:when
									test="${FriendBean.CheckStt(nameFromId,nameToId)}">
                                <td>${listUser.email}</td> 
                                <td>
                                    <input type="submit"
										value="Da dui loi moi ket ban" />
                                </td>
                                <td>
                                    <form action="cancelRequest"
											method="POST">
                                        <input name="usTo" type="hidden"
												value="${nameToId}" />
                                        <input name="usFrom"
												type="hidden" value="${nameFromId}" />
                                        <input type="submit"
												value="Cancel Request" />
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>${listUser.email}</td>
                                <td><input type="submit"
										value="Da la ban be" /></td>
                                <td>
                                    <form action="removeFriend"
											method="POST">
                                        <input name="usToId"
												type="hidden" value="${nameFromId}" />
                                        <input name="usFromId"
												type="hidden" value="${nameToId}" />
                                        <input type="submit"
												value="Xoa ket ban" />
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when
									test="${FriendBean.CheckExist(nameToId,nameFromId)}">
                                <c:choose>
                                    <c:when
											test="${FriendBean.CheckStt(nameToId,nameFromId)}">
                                        <td>${listUser.email}</td>
                                        <td>
                                            <form action="acceptRequest"
													method="POST">
                                                <input name="usToId"
														type="hidden" value="${nameFromId}" />
                                                <input name="usFromId"
														type="hidden" value="${nameToId}" />
                                                <input type="submit"
														value="Chap nhan ket ban" />
                                            </form>
                                        </td>
                                        <td>
                                            <form action="NoAccept"
													method="POST">
                                                <input name="usToId"
														type="hidden" value="${list.userId}" />
                                                <input name="usFromId"
														type="hidden" value="${nameToId}" />
                                                <input type="submit"
														value="Tu choi" />
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${listUser.email}</td>
                                        <td><input type="submit"
												value="Da la ban be" /></td>
                                        <td>
                                            <form action="removeFriend"
													method="POST">
                                                <input name="usToId"
														type="hidden" value="${list.userId}" />
                                                <input name="usFromId"
														type="hidden" value="${nameToId}" />
                                                <input type="submit"
														value="Xoa ket ban" />
                                            </form>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <td>${listUser.email}</td>    
                                <td>
                                    <form action="sendRequest"
											method="POST">
                                        <input name="usToId"
												type="hidden" value="${nameToId}" />
                                        <input name="usFromId"
												type="hidden" value="${nameFromId}" />
                                        <input type="submit"
												value="Send Request" />
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
	  </div>
		</div>
	</div>
	</div>
	</jsp:body>
</t:layout>
