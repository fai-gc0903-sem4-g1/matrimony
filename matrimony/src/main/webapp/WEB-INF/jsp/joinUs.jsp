<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:useBean id="fbConn" class="facebook.api.FBConnection" />
<c:set var="current" value="${param.ddlLanguage}" scope="session" />

<c:if test="${not empty current}">
	<fmt:setLocale value="${current}" scope="session" />
</c:if>
<fmt:setBundle basename="com.matrimony.i18n.StringResource"
	scope="session" />
<t:layout>
	<jsp:attribute name="head">
		<title>Welcome to matrimony</title>
		<link rel="stylesheet"
			href="/matrimony/resources/css/login-form-dialog.css" />
			<style>
#hidden-div {
	display: none;
}

#login-with {
	margin-top: 17px;
}
</style>
	</jsp:attribute>


	<jsp:body>
	<script>
	$(document).ready(function(){
		if(${fbResp==1}){
			$('#show-loggin-model-trigger').click();
		};
	});
	</script>
	
	<div id='hidden-div'>
	<a href="#" id="show-loggin-model-trigger" data-toggle="modal"
				data-target="#login-modal">Login</a>
	</div>
	<div id='container'>
	<div id="left" class="col-lg-8">
		<div id='login-with' class="col-lg-6 pull-right row">
				<div class="col-lg-8">
			 <a href="${fbConn.FBAuthUrl}"
							class="btn btn-block btn-social btn-facebook">
			    <i class="fa fa-facebook"></i> Log-in with Facebook
			  </a>
			  <c:if test="${fbResp == 0 }">
					  <div class="alert alert-danger" role="alert">
					 		Bạn đã đăng ký bằng facebook, hãy nhập email và mật khẩu
					  </div>
			  </c:if>
			  </div>
			  
			  </div>
		</div>
<div id="right" class="col-lg-4">
<form:form modelAttribute="userReg" id="registerForm"
					action="/matrimony/register" method="POST"
					class="form-horizontal col-lg-12">
				<h2>Đăng ký</h2>
				<br />
				<c:if test="${registerFormError }">
				<div class="alert alert-danger" role="alert">
				<form:errors path="firstName" id="validError" />
							<br />
				<form:errors path="lastName" id="validError" />
							<br />
				<form:errors path="email" id="validError" />
							<br />
				<form:errors path="password" id="validError" />
							<br />
				<form:errors path="gender" id="validError" />
							<br />
				${birthdayNotEnough }<br />
				${birthdayInvalid }<br />
				${reEmailInvalid }
				</div>
				</c:if>
				<div style="display: none;" id="myAlert" class="alert alert-danger"
						role="alert"></div>

				<div class="form-group">
					<label style="color: red;" class="control-label col-sm-1">(*)</label>
					<div class="col-lg-5">
						<input id="firstName" class="form-control" type="text"
								name="firstName" placeholder="Tên"
								value="${requestScope.userReg.firstName}"></input>
					</div>
					<div class="col-lg-5">
						<input id="lastName" class="form-control" type="text"
								name="lastName" placeholder="Họ"
								value="${requestScope.userReg.lastName}"></input>
					</div>
					<form:errors path="firstName" id="validError"
							cssClass="control-label col-lg-offset-1" />
					<form:errors path="lastName" id="validError"
							cssClass="control-label col-lg-offset-1" />
				</div>

				
				<div class="form-group">
					<label style="color: red;" class="control-label col-sm-1">(*)</label>
					<div class="col-sm-10">
						<input id="email" onkeyup="" class="form-control" type="email"
								name="email" value="${userReg.email}"
								placeholder="Địa chỉ email">
						</input>
						<div>
							<form:errors path="email" id="validError" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label style="color: red;" class="control-label col-sm-1">(*)</label>
					<div class="col-sm-10">
						<input id="reEmail" name="reEmail" class="form-control"
								type="email" placeholder="Nhập lại địa chỉ email" />
						<div id="validError">
							${reEmailInvalid }
						</div>
					</div>
				</div>
				<div class="form-group">
					<label style="color: red;" class="control-label col-sm-1">(*)</label>
					<div class="col-sm-10">
						<input id="password" class="form-control" type="password"
								name="password" placeholder="Mật khẩu"></input>
						<form:errors path="password" id="validError" />
					</div>
				</div>
				<div class="form-group">
					<label style="color: red;" class="control-label col-sm-1"></label>
					<div class="col-sm-10">
						<input id="phone" class="form-control" type="text"
								name="contactNumber" placeholder="Số điện thoại nếu có"></input>
						<form:errors path="contactNumber" id="validError" />
					</div>
				</div>
				<div class="form-group">
					<label style="color: red;" class="control-label col-sm-1">(*)</label>
					<div class="col-lg-3" style="width: 114px;">
						<select class="form-control" name="day">
							<option>Ngày</option>
							<c:forEach var="i" begin="1" end="31" step="1">
								<option>${i}</option>
							</c:forEach>

						</select>
					</div>

					<div class="col-lg-3" style="width: 122px;">
						<select class="form-control" name="month">
							<option>Tháng</option>
							<c:forEach var="i" begin="1" end="12" step="1">
								<option>${i}</option>
							</c:forEach>

						</select>
					</div>
					<div class="col-lg-3" style="width: 114px">
						<select class="form-control" name="year">
							<option>Năm</option>
							<c:forEach var="i" begin="1905" end="2015" step="1">
								<option>${i}</option>
							</c:forEach>

						</select>
					</div>
					<div class="col-lg-5 col-lg-offset-1" id="validError">${requestScope.birthdayInvalid}</div>
				</div>
				<div class="form-group">
					<label style="color: red;" class="control-label col-sm-1"></label>
					<div class="col-lg-3">
						<label class="control-label">Giới tính</label>
					</div>
					<br />
					<div style="font-size: 17px;" class="checkbox" id="sexGroup">
						<label style="color: red;" class="control-label col-sm-1"></label>
						<label class="control-label"> <input type="radio" id="sex"
								name="gender" value="female" /> Nữ
						</label> <label class="control-label"> <input id="sex"
								type="radio" name="gender" value="male" /> Nam
						</label> <label class="control-label"><form:errors path="gender"
									id="validError" /></label>
					</div>
				</div>

				<div class="form-group">
					<label style="color: red;" class="control-label col-sm-1"></label>
					<div class="col-sm-10">
						<input class="btn btn-success col-sm-10" type="submit"
								value="Đăng ký" />
					</div>
				</div>
			</form:form>
</div>
</div>

<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
			aria-hidden="true" style="display: none;">
				<div class="modal-dialog">
					<div class="loginmodal-container">
						<h1>New password</h1>
								<br>
					  <form:form modelAttribute="logginFBUser" action="loginWithFacebook"
						method="POST">
						<input id="txtPassword" type="password" name="password"
							placeholder="Enter new password">
							<form:errors path="password" id="validError" />
						<input id="txtRePassword" type="password" name="rePassword"
							placeholder="Re-enter new password">
							<div id="validError">${rePasswordInvalid }</div>
						<input id="btnSubmitPassword" type="submit" name="login"
							class="login loginmodal-submit" value="Login">
					  </form:form>
						
					  <div class="login-help">
						<div class="checkbox">
										<label><input type="checkbox" name='keepLoggin' /> keep me logged-in</label>
									</div>
					  </div>
					</div>
				</div>
			</div>
</jsp:body>
</t:layout>