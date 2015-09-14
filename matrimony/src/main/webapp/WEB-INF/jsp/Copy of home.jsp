<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
	<jsp:body>
	<c:set var="alias" value="matrimony" />
	
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
					src='avatar.jpg' />
		<br />
		<br />
		<span id='name' style='font-weight: bold;'>Đàm Sơn</span>
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
	Friend tables
		<div id='tblPeople'>
			<div id='person-panel' class='row col-sm-12'>
				<div class='col-sm-2'>
				<img id='person-avatar' alt='person-avatar' src='person-avatar.png'
								style='height: 70px; width: 70px;' />
				</div>
			
				<div id='person-left' class='col-sm-5'>
					<span id='person-name'>Azad Siddha</span><br />
					<span id='person-age'>24</span><br />
					<span id='person-gender'>Female</span><br />
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
			
			<div class='row col-sm-12' style='text-align: center;'>
				<h4>Trống</h4>
			</div>
			</div>
		</div>
		<div id='right' class='col-lg-3'>
		<div class="panel panel-default">
	  <div class="panel-heading">
		<h3 class="panel-title">Panel title</h3>
	  </div>
	  <div class="panel-body">
		Panel content
	  </div>
		</div>
	</div>
	</div>
</div>
	</jsp:body>
</t:layout>