<%@tag pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:useBean id="test" class="com.matrimony.database.UserDAO" />
<jsp:useBean id="friendDAO" class="com.matrimony.database.FriendDAO" />
<c:choose>
	<c:when test="${not empty login }">
		<c:set var="loginValue" value="${login }" />
		<c:set var="passwordValue" value="${password }" />
	</c:when>
	<c:when test="${sessionScope.type=='Native' }">
		<c:set var="loginValue" value="${sessionScope.login.value }" />
		<c:set var="passwordValue" value="${sessionScope.password.value }" />
	</c:when>
	<c:otherwise>
		<c:set var="loginValue" value="" />
		<c:set var="passwordValue" value="" />
	</c:otherwise>
</c:choose>
<html>

<head>
<meta charset="utf-8" />
<!-- ===========JQUERY=========== -->
<script src="/matrimony/resources/js/jquery.min.js"></script>
<!-- ===========TWITTER BOOTSTRAP=========== -->
<script src="/matrimony/resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="/matrimony/resources/bootstrap/css/bootstrap.min.css">
<!-- 	===========FONT AWSOME=========== -->
<link rel='stylesheet'
	href='/matrimony/resources/css/bootstrap-social.css' />
<link rel="stylesheet"
	href="/matrimony/resources/font-awesome/css/font-awesome.min.css">
<!-- 	===========SILVIOMORETO-BOOTSTRAP-SELECT=========== -->
<link rel='stylesheet'
	href='/matrimony/resources/silviomoreto-bootstrap-select/css/bootstrap-select.min.css' />
<script
	src="/matrimony/resources/silviomoreto-bootstrap-select/js/bootstrap-select.min.js"></script>
<!-- 	===========CUSTOM CSS=========== -->
<link rel='stylesheet' href='/matrimony/resources/css/layout-style.css' />
<style>
.navbar {
	border-color: #6B5094;
}

.navbar-default {
	border-radius: 0px;
}

.st-icon-nav-bar {
	color: navy;
}

.st-label-number-notify {
	color: #FDF200;
}
</style>
<script>
//                             	$.noConflict();
                                $(document).ready(function() { 
//                                 	$.noConflict();
                                	$('.st-login-input-group').popover({placement: 'bottom'});
                                	
                                	
                                	$('.drop-friend a').click(function(e){
                                		console.log('ngan ngua event ok');
                                		e.preventDefault();
                                	});
                                	
                                	$(document).on('click', '.btn-deny-friend').click(function(){
                                		var btn=$(this);
                                		$.ajax({
                            				url: 'denyFriend',
                                            method: 'POST',
                                            data: {userInviteId:btn.data('id')},
                                            async: false,
                                            success: function(obj){
                            					if(obj=='SUCCESS'){
                            						console.log('da tu choi');
                            						btn.parents('li').remove();
                            					}else{
                            						alert('Không để Từ chối');
                            					}
                            				},
                            				error: function(){
                            					alert('Kiểm tra lại đường truyền');
                            				}
                            			});
                                	});
                                	});
                                	
                                	$(document).on('click', '.btn-accept-friend', function(e){
                                		var btn=$(this);
                                		$.ajax({
                            				url: 'acceptFriend',
                                            method: 'POST',
                                            data: {userInviteId:btn.data('id')},
                                            async: false,
                                            success: function(obj){
                            					if(obj=='SUCCESS'){
                            						console.log('da dong y');
                            						btn.parents('li').remove();
                            					}else{
                            						alert('Không để đồng ý');
                            					}
                            				},
                            				error: function(){
                            					alert('Kiểm tra lại đường truyền');
                            				}
                            			});
                                	});
                                
                            </script>
<jsp:invoke fragment="head" />
</head>
<c:set var="userAvatarFolder"
	value="/matrimony/resources/profile/avatar" scope="application" />

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
							<a class="navbar-brand"
								style='color: blanchedalmond; font-size: 26px;'
								href="/matrimony">Matrimony</a>
						</div>

						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<form:form modelAttribute="userLogin" id="signin" action='login'
								method='POST' class="navbar-form navbar-right" role="form">
								<div class="input-group st-login-input-group"
									data-toggle='popover' data-content='${loginNameInvalid }'>
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span> <input id="email"
										type="text" class="form-control" name="login"
										value="${loginValue }" placeholder="Email or phone">
									<c:if test="${not empty loginNameInvalid}">
										<i
											class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid"
											style='font-size: 22px; color: #CB2027; margin-right: 0;'></i>
									</c:if>
								</div>

								<div class="input-group st-login-input-group"
									data-toggle='popover' data-content='${loginPasswordInvalid }'>
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> <input id="password"
										type="password" class="form-control" name="password"
										value="${passwordValue}" placeholder="Password" />
									<c:if test="${not empty loginPasswordInvalid}">
										<i
											class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid"
											style='font-size: 22px; color: #CB2027; margin-right: 0;'></i>
									</c:if>
								</div>

								<button type="submit" class="btn btn-primary">Login</button>
								<br />
								<div class="checkbox">
									<label style='color: wheat;'> <input type="checkbox"
										name='keepLoggin' /> keep me logged-in
									</label>
								</div>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <div
									class="checkbox">
									<a href="recover" style='color: wheat;'>Forget the password
										?</a>
								</div>
								<br />
								<form:errors path="password" id="validError"
									cssClass="col-lg-offset-5" />
							</form:form>
							<p id='msg-response-error' class="navbar-text navbar-right">
								<c:if test="${loginWrongPassword }">
                                                     	Sai mật khẩu vui lòng nhập lại
                                                     </c:if>
								<c:if test="${loginUserNotExists }">
                                                     	Tài khoản không tồn tại
                                                     </c:if>
								<c:if test="${loginNotNativeAccount }">
                                                     	Tài khoản này được đăng nhập với Facebook
                                                     </c:if>
							</p>
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
							<a class="navbar-brand"
								style='color: blanchedalmond; font-size: 26px;'
								href="/matrimony">Matrimony</a>
						</div>


						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<div class="col-lg-6"></div>
							<c:set var="askRequest"
								value="${friendDAO.getRequestAskInvited(sessionScope.user)  }" />
							<ul class="nav navbar-nav navbar-right">
								<li><a href="/matrimony"><i style='font-size: 22px;'
										class="fa fa-newspaper-o st-icon-nav-bar"></i></a></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"><i
										class="fa fa-users st-icon-nav-bar" style='font-size: 19px'></i>
										<span class="label label-primary st-label-number-notify">${fn:length(askRequest) }</span>
								</a>
									<ul class="dropdown-menu drop-friend" style="width: 400px;">

										<!--                                                                 <li><a href="#"><div id="request"><span class="label label-warning"></span></div></a></li> -->
										<li class='disabled'><a>Lời mời làm quen</a></li>
										<li class="divider"></li>
										<c:forEach var="i" items="${askRequest}">
											<li><a class='col-lg-12'> <span> <img alt=""
														class='col-lg-3'
														src="/matrimony/resources/profile/avatar/${i.avatarPhoto }">
												</span> <span class='col-lg-9'> <span
														style='cursor: pointer'>${i.name }</span><br /> <span>${i.gender }
															đến từ ${i.hometown }</span> <br /> <span><input
															class='btn-success btn-accept-friend' data-id="${i.id }"
															type='submit' value='Chấp nhận' /></span> <span
														class='pull-right'><input type='submit'
															class='btn-warning btn-deny-friend' data-id="${i.id }"
															value='Từ chối' /></span>
												</span>
											</a></li>
											<li class='disabled'><a><hr style='margin: 0' /></a></li>

										</c:forEach>
										<li><a href="#" class="text-center">View All</a>
									</ul></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"><i
										class="fa fa-bell-o st-icon-nav-bar" style='font-size: 19px'></i>
										<span class="label label-primary st-label-number-notify">26</span>
								</a>
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
									data-toggle="dropdown"><i
										class="fa fa-comments-o  st-icon-nav-bar"
										style='font-size: 19px'></i> <span
										class="label label-primary st-label-number-notify">0</span> </a>
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
								<li><a href="/matrimony"
									style='color: white; font-weight: 600; font-size: 13px;'><img
										data-current-user-id='${sessionScope.user.id }'
										alt='mini-avartar' id='mini-avatar'
										src='${userAvatarFolder}/${sessionScope.user.avatarPhoto}'
										style='width: 20px; height: 20px; border-radius: 2px;' />&nbsp;&nbsp;${sessionScope.user.firstName }</a>
								</li>
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
