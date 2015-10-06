<%@tag pageEncoding="UTF-8" %>
    <%@attribute name="head" fragment="true" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                        <jsp:useBean id="test" class="com.matrimony.database.UserDAO" />
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
                            <link rel="stylesheet" href="/matrimony/resources/bootstrap/css/bootstrap.min.css">
                            <!-- 	===========FONT AWSOME=========== -->
                            <link rel='stylesheet' href='/matrimony/resources/css/bootstrap-social.css' />
                            <link rel="stylesheet" href="/matrimony/resources/font-awesome/css/font-awesome.min.css">
                           <!-- 	===========SILVIOMORETO-BOOTSTRAP-SELECT=========== -->
                           <link rel='stylesheet' href='/matrimony/resources/silviomoreto-bootstrap-select/css/bootstrap-select.min.css' />
                           <script src="/matrimony/resources/silviomoreto-bootstrap-select/js/bootstrap-select.min.js"></script>
                            <!-- 	===========CUSTOM CSS=========== -->
                            <link rel='stylesheet' href='/matrimony/resources/css/layout-style.css' />
                            <style>
                            .navbar{
                            border-color: #0B99BC;
                            }
                            .navbar-default{
                            border-radius: 0px;
                            }
                            
                            
                            </style>
                            <script>
                                $(document).ready(function() { 
                                	$('.st-login-input-group').popover({placement: 'bottom'});
//                                 	$('.st-reg-form-group').click(function(){
//                                 		var popoverWin;
//                                 		var formGroup=$(this);
//                                         $('.popover').each(function(i){
//                                         	if(!$(this).children('.popover-content').html()==formGroup.data('content'))
//                                         	{
//                                         		alert($(this));
//                                         		$(this).remove();
//                                         	}
//                                         });
//                                 	});
                                });
                            </script>
                            <jsp:invoke fragment="head" />
                        </head>
                        <c:set var="userAvatarFolder" value="/matrimony/resources/profile/avatar" scope="application" />

                        <body>
                            <div id="header">
                                <c:choose>
                                    <c:when test="${empty sessionScope.user}">
                                        <nav class="navbar navbar-default">
                                            <div class="container-fluid">
                                                <div class="navbar-header">
                                                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                                        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                                                    </button>
                                                    <a class="navbar-brand" style='color: blanchedalmond;font-size: 26px;' href="/matrimony">Matrimony</a>
                                                </div>

                                                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                                                    <form:form modelAttribute="userLogin" id="signin" action='login' method='POST' class="navbar-form navbar-right" role="form">
                                                        <div class="input-group st-login-input-group" data-toggle='popover' data-content='${loginNameInvalid }'>
                                                            <span class="input-group-addon"><i
																	class="glyphicon glyphicon-user"></i></span>
                                                            <input id="email" type="text" class="form-control" name="login" value="${loginValue }" placeholder="Email or phone">
                                                        <c:if test="${not empty loginNameInvalid}"><i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid" style='font-size: 22px;color: #CB2027;margin-right:0;'></i></c:if>
                                                        </div>

                                                        <div class="input-group st-login-input-group" data-toggle='popover' data-content='${loginPasswordInvalid }'>
                                                            <span class="input-group-addon"><i
																				class="glyphicon glyphicon-lock"></i></span>
                                                            <input id="password" type="password" class="form-control" name="password" value="${passwordValue}" placeholder="Password" />
															<c:if test="${not empty loginPasswordInvalid}"><i class="glyphicon glyphicon-exclamation-sign form-control-feedback st-reg-invalid" style='font-size: 22px;color: #CB2027;margin-right:0;'></i></c:if>
                                                        </div>

                                                        <button type="submit" class="btn btn-primary">Login</button>
                                                        <br />
                                                        <div class="checkbox">
                                                            <label style='color: wheat;'>
                                                                <input type="checkbox" name='keepLoggin' /> keep me logged-in</label>
                                                        </div>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <div class="checkbox">
                                                            <a href="recover" style='color: wheat;'>Forget the password ?</a>
                                                        </div>
                                                        <br/>
                                                        <form:errors path="password" id="validError" cssClass="col-lg-offset-5" />
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
                                                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                                        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                                                    </button>
                                                    <a class="navbar-brand" style='color: blanchedalmond;font-size: 26px;' href="/matrimony">Matrimony</a>
                                                </div>


                                                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                                    <div class="col-lg-6">
                                                        <form class="navbar-form" role="search">
                                                            <div class="input-group col-lg-12">
                                                                <input type="text" class="form-control" placeholder="Search" name="q">
                                                                <div class="input-group-btn">
                                                                    <button class="btn btn-default" type="submit">
                                                                        <i class="glyphicon glyphicon-search"></i>
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>

                                                    <ul class="nav navbar-nav navbar-right">
                                                    <li><a href="/matrimony"><i style='font-size:22px;' class="fa fa-newspaper-o"></i></a></li>
                                                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-users" style='font-size:19px'></i> <span
										class="label label-primary">5</span> </a>
                                                            <ul class="dropdown-menu">
                                                                <li><a href="#"><span class="label label-warning">7:00
													AM</span>Hi :)</a>
                                                                </li>
                                                                <li><a href="#"><span class="label label-warning">8:00
													AM</span>How are you?</a>
                                                                </li>
                                                                <li><a href="#"><span class="label label-warning">9:00
													AM</span>What are you doing?</a>
                                                                </li>
                                                                <li class="divider"></li>
                                                                <li><a href="#" class="text-center">View All</a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-globe" style='font-size:19px'></i> <span
										class="label label-primary">26</span> </a>
                                                            <ul class="dropdown-menu">
                                                                <li><a href="#"><span class="label label-warning">7:00
													AM</span>Hi :)</a>
                                                                </li>
                                                                <li><a href="#"><span class="label label-warning">8:00
													AM</span>How are you?</a>
                                                                </li>
                                                                <li><a href="#"><span class="label label-warning">9:00
													AM</span>What are you doing?</a>
                                                                </li>
                                                                <li class="divider"></li>
                                                                <li><a href="#" class="text-center">View All</a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-comments-o" style='font-size:19px'></i> <span
										class="label label-primary">0</span> </a>
                                                            <ul class="dropdown-menu">
                                                                <li><a href="#"><span class="label label-warning">7:00
													AM</span>Hi :)</a>
                                                                </li>
                                                                <li><a href="#"><span class="label label-warning">8:00
													AM</span>How are you?</a>
                                                                </li>
                                                                <li><a href="#"><span class="label label-warning">9:00
													AM</span>What are you doing?</a>
                                                                </li>
                                                                <li class="divider"></li>
                                                                <li><a href="#" class="text-center">View All</a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                        <li>
                                                            <a href="/matrimony"><img alt='mini-avartar' src='${userAvatarFolder}/${sessionScope.user.avatarPhoto}' style='width: 20px; height: 20px; border-radius: 2px;' />&nbsp;&nbsp;${sessionScope.user.firstName }</a>
                                                        </li>

                                                        <li>
                                                            <form id="signin" action='logout' method='POST' class="nav navbar-form navbar-right" role="form">
                                                                <button type="submit" class="btn btn-danger">Logout</button>
                                                            </form>
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