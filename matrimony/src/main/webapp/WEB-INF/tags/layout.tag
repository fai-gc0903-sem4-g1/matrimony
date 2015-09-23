<%@tag pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:useBean id="test" class="com.matrimony.database.UserDAO" />
<html>
<head>
<meta charset="utf-8" />
	<!-- Jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	
	<!-- twitter bootstrap -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 	<script src="/resources/bootstrap/js/bootstrap.min.js"></script> -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- 	<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css"> -->
	<!-- style -->
	<link rel="stylesheet" type="text/css"href="/matrimony/resources/css/layout.css">
	
	<!-- 	font awesome -->
	<link rel='stylesheet' href='/matrimony/resources/css/bootstrap-social.css'/>
	<link rel="stylesheet" href="/matrimony/resources/font-awesome/css/font-awesome.min.css">
	<jsp:invoke fragment="head" />
</head>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	margin: 0 auto;
}

#msg-response-error {
	color: red;
}

#hidden-div{
	display:none;
}
#validError{
	color:red;
}
</style>
<c:set var="userAvatarFolder" value="/matrimony/resources/profile/avatar" scope="application"/>

<body>
	<div id="header">
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">Brand</a>
						</div>

						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">

							<form:form modelAttribute="userLogin" id="signin" action='login'
								method='POST' class="navbar-form navbar-right" role="form">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span> <input id="email"
										type="text" class="form-control" name="username"
										value="${empty requestScope.userLogin.username?cookie.loginName.value:requestScope.userLogin.username }"
										placeholder="Email Address">
								</div>

								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> 
										<input id="password"
										type="password" class="form-control" name="password"
										value="${empty requestScope.userLogin.password?cookie.password.value:requestScope.userLogin.password}"
										placeholder="Password" />
										
								</div>

								<button type="submit" class="btn btn-primary">Login</button>
								<br />
								<div class="checkbox">
									<label><input type="checkbox" name='keepLoggin' />
										keep me logged-in</label>
								</div>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<div class="checkbox">
									<a href="recover">Forget the password ?</a>
								</div>
																<br/>
																<form:errors path="password" id="validError" cssClass="col-lg-offset-5" />
							</form:form>
							<p id='msg-response-error' class="navbar-text navbar-right">${requestScope.notice}</p>
						</div>
					</div>
				</nav>

			</c:when>
			
			<c:otherwise>
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">Brand</a>
						</div>


						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<div class="col-lg-6">
								<form class="navbar-form" role="search">
									<div class="input-group col-lg-12">
										<input type="text" class="form-control" placeholder="Search"
											name="q">
										<div class="input-group-btn">
											<button class="btn btn-default" type="submit">
												<i class="glyphicon glyphicon-search"></i>
											</button>
										</div>
									</div>
								</form>
							</div>

							<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"><i class="fa fa-users" style='font-size:19px'></i> <span
										class="label label-primary">5</span> </a>
									<ul class="dropdown-menu">
										<li><a href="#"><span class="label label-warning">7:00
													AM</span>Hi :)</a></li>
										<li><a href="#"><span class="label label-warning">8:00
													AM</span>How are you?</a></li>
										<li><a href="#"><span class="label label-warning">9:00
													AM</span>What are you doing?</a></li>
										<li class="divider"></li>
										<li><a href="#" class="text-center">View All</a></li>
									</ul></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"><i class="fa fa-globe" style='font-size:19px'></i> <span
										class="label label-primary">26</span> </a>
									<ul class="dropdown-menu">
										<li><a href="#"><span class="label label-warning">7:00
													AM</span>Hi :)</a></li>
										<li><a href="#"><span class="label label-warning">8:00
													AM</span>How are you?</a></li>
										<li><a href="#"><span class="label label-warning">9:00
													AM</span>What are you doing?</a></li>
										<li class="divider"></li>
										<li><a href="#" class="text-center">View All</a></li>
									</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"><i class="fa fa-comments-o" style='font-size:19px'></i> <span
										class="label label-primary">0</span> </a>
									<ul class="dropdown-menu">
										<li><a href="#"><span class="label label-warning">7:00
													AM</span>Hi :)</a></li>
										<li><a href="#"><span class="label label-warning">8:00
													AM</span>How are you?</a></li>
										<li><a href="#"><span class="label label-warning">9:00
													AM</span>What are you doing?</a></li>
										<li class="divider"></li>
										<li><a href="#" class="text-center">View All</a></li>
									</ul></li>
								<li><a href="#"><img alt='mini-avartar'
										src='${userAvatarFolder}/${sessionScope.user.avatarPhoto}'
										style='width: 20px; height: 20px; border-radius: 2px;' />&nbsp;&nbsp;${sessionScope.user.firstName }</a></li>

								<li><form id="signin" action='logout' method='POST'
										class="nav navbar-form navbar-right" role="form">
										<button type="submit" class="btn btn-danger">Logout</button>
									</form></li>
							</ul>
						</div>
					</div>
				</nav>
			</c:otherwise>
		</c:choose>

	</div>
	<div id="body">
		<jsp:doBody />
	</div>
</body>
</html>



