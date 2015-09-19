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
	body{
	background-color:#f9f9f9;}
#container {
	padding: 0px 30px 0 30px;
}

#person-panel {
	border: solid 2px #f7f7f7;
	margin-left: 0px;
	margin-top: 10px;
	padding: 12px 0px;
}

#person-btna {
	color: #8DBAD9;
	cursor: pointer;
}

#label-basic {
	font-weight: bold;
	color: #116CBB;
}

#avatar {
	cursor: pointer;
}

#hiddenDIV {
	display: none;
}
</style>

<script>
	$(document).ready(function() {
		$("#avatar").click(function() {
			$("#uploadAvatarPhoto").click();
		});
		$("#uploadAvatarPhoto").change(function() {
			$("#uploadPhotoForm").submit();
		});
	});
</script>
<div id="hiddenDIV">
		<form id="uploadPhotoForm" action="changeAvatar" method="POST" enctype="multipart/form-data">
			<input id="uploadAvatarPhoto" type="file" name="file"
					accept="image/*" />
		</form>
	</div>
	<div id="container" class='row'>
	<div id='left' class='col-lg-2' style='width: 233px;'>
		<img id="avatar" alt='avatar' style='height: 200px; width: 200px;'
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
									style='height: 70px; width: 70px;' /><br /><br />
									<span id='person-name'>${i.name }</span>
				</div>
			
				<div id='person-left' class='col-sm-5'>
					<span id='person-name'><span id="label-basic">Name</span> ${i.name }</span><br />
					<span id='person-age'><span id="label-basic">Age </span>${i.birthday }</span><br />
					<span id='person-gender'><span id="label-basic">Gender </span>${i.gender }</span><br /><br />
					<span id='person-btna'><button class="btn-success">Inbox</button></span>
					<span id='person-btna'>1 friend</span>
				</div>
				<div id='person-right' class='col-sm-4'>
					<span id='person-name'><span id="label-basic">Country </span>Viet nam</span><br />
					<span id='person-city'><span id="label-basic">City </span>Ha noi</span><br />
					<span id='person-status'><span id="label-basic">Marital </span> No infomation</span><br /><br />
					<span id='person-btna'><button class="btn-info">Add friend</button></span>
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
	  </div>
		</div>
	</div>
	</div>
	</jsp:body>
</t:layout>
