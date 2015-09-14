<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/bundle/jstl.jsp" />
<jsp:include page="/WEB-INF/bundle/bootstrap.jsp" />


<html>
<head>
<meta charset="utf-8" />
<title>This's layout page</title>
<link rel="stylesheet" type="text/css"
	href="/matrimony/resources/css/layout.css">
</head>
<style>
* {margin 0;padding 0;
	
}

body {
	margin: 0 auto;
}

#msg-response-error {
	color:red;
}
</style>


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

							<form:form modelAttribute="userLogin" id="signin" action='login' method='POST'
								class="navbar-form navbar-right" role="form">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span> <input id="email"
										type="email" class="form-control" name="username"
										value="${empty requestScope.userLogin.username?cookie.loginName.value:requestScope.userLogin.username }"
										placeholder="Email Address">
								</div>

								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> <input id="password"
										type="password" class="form-control" name="password"
										value="${empty requestScope.userLogin.password?cookie.password.value:requestScope.userLogin.password}"
										placeholder="Password">
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
<!-- 								<br/> -->
<%-- 								<form:errors path="password" cssClass="error" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
<%-- 								<form:errors path="username" cssClass="error" /> --%>
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
									data-toggle="dropdown"><span
										class="glyphicon glyphicon-asterisk"></span>Notification <span
										class="label label-primary">42</span> </a>
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
										src='avatar.jpg'
										style='width: 20; height: 20; border-radius: 2px;' />&nbsp;&nbsp;Sơn</a></li>

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



