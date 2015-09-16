<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:layout>
	<jsp:attribute name="head">
	<link rel="stylesheet" href="/matrimony/resources/css/login-form-dialog.css"/>
<title>Lay lai mat khau</title>
</jsp:attribute>
	<jsp:body>
	<script>
	$(document).ready(function(){
		if(${recoverRespCode==4}){
			$('#show-loggin-model-trigger').click();
		}
	});
	</script>
<div id='hidden-div'>
	<a href="#" id="show-loggin-model-trigger" data-toggle="modal"
				data-target="#login-modal">Login</a>
	</div>
	
	<c:set var="fieldType" value="email" scope="page" />
	<c:set var="process" value="level1" scope="page" />
	<c:choose>
	<c:when test="${recoverRespCode==0 }">
		<label class="alert alert-danger">Email Không tồn tại!</label>
				<br />
		<label class="label-control">Nhập lại địa chỉ Email</label>
				<br />
	</c:when>
	<c:when test="${recoverRespCode==1 || null !=sessionScope.codeRecover}">
		<c:set var="fieldType" value="text" scope="page" />
		<c:set var="process" value="level2" scope="page" />
		<label class="alert alert-success">Chúng tôi đã gửi mật khẩu đến Email của bạn, hãy chờ và kiểm tra. Chú ý kiểm tra cả mục spam!</label>
			<br />
		<label class="label-control">Nhập code</label>
			<br />
	</c:when>
	
	<c:when test="${recoverRespCode==3 }">
		<c:set var="fieldType" value="text" scope="page" />
		<c:set var="process" value="level2" scope="page" />
		
		<label class="alert alert-danger">Code sai, hay nhập lại</label>
			<br />
		<label class="label-control">Nhập code</label>
			<br />
	</c:when>
	<c:when test="${recoverRespCode==4 }">
		<c:set var="fieldType" value="text" scope="page" />
		<c:set var="process" value="level2" scope="page" />
		
		<label class="alert alert-success">Xác nhận thành công, bạn có thể đăng nhập bằng mật khẩu mới</label>
			<br />
	</c:when>
	
	<c:otherwise>
	<label>Để lấy lại mật khẩu hãy nhập địa chỉ email</label>
				<br />
		<label>Nhập địa chỉ Email</label>
				<br />
	</c:otherwise>
	</c:choose>
	<a href="/matrimony">Quay về trang chủ</a>
	<form action="/matrimony/recover" method="post" class="form-horizontal">
		<div class="form-group">
			<div class="col-lg-4">
				<input class="form-control" type="${fieldType }" name="textField" />
			</div>
			<input type="hidden" name="process" value="${process }" /> 
			<input class="btn btn-info" type="submit" value="Xác nhận" />
		</div>
	</form>
	
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
			aria-hidden="true" style="display: none;">
				<div class="modal-dialog">
					<div class="loginmodal-container">
						<h1>New password for you</h1>
								<br>
					  <form:form modelAttribute="ruser" action="recover" method="POST">
						<input id="txtPassword" type="password" name="password"
							placeholder="Enter new password">
						<input id="txtRePassword" type="password" name="rePassword"
							placeholder="Re-enter new password">
							<form:errors path="password" />
							<input type="hidden" name="process" value="level3" /> 
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