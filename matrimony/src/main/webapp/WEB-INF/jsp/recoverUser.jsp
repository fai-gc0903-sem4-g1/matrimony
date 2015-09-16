<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
	<jsp:attribute name="head">
<title>Lay lai mat khau</title>
</jsp:attribute>
	<jsp:body>
	<script>
	$(document).ready(function(){
		if(${requestScope.recoverSuccess}){
			$('#show-loggin-model-trigger').click();
		}
	});
	</script>
<div id='hidden-div'>
	<a href="#" id="show-loggin-model-trigger" data-toggle="modal"
				data-target="#login-modal">Login</a>
	</div>
<h1>${code }</h1>
	<c:choose>
		<c:when test="${not empty sessionScope.codeRecover}">
			<label class="alert alert-success" style="width: 450px;">Chúng tôi đã gửi mật khẩu đến Email của bạn, hãy kiểm tra. Chú ý kiểm tra cả mục spam!</label>
			<br />
			<label class="label-control">Nhap ma trong mail de tiep tuc thiet lap lai mat khau</label>
			<br />
			<c:if test="${requestScope.respCode == 2}">
				<label class="alert alert-danger" style="width: 450px;">Sai code!</label>
			</c:if>
			<br />
			
			<a href="index.jsp">Quay về trang chủ</a>
			<form action="/matrimony/recover" method="post"
					class="form-horizontal">
				<br /> <label>Nhập code</label>
				<div class="form-group">
					<div class="col-sm-5">
						<input class="form-control" type="text" name="textField" />
					</div>
					<input type="hidden" name="process" value="level2" /> <input
							class="btn btn-info" type="submit" value="Xác nhận" />
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<label class="label-control">Để lấy lại mật khẩu, xin vui lòng nhập địa chỉ Email lúc bạn đăng ký</label>
			<br />
			<c:if test="${requestScope.respCode == 1}">
				<label class="alert alert-danger" style="width: 450px;">Email Không tồn tại!</label>
			</c:if>
			<br />
			<a href="index.jsp">Quay về trang chủ</a>
			<form action="/matrimony/recover" method="post"
					class="form-horizontal">
				<br /> <label>Nhập địa chỉ Email</label>
				<div class="form-group">
					<div class="col-sm-5">
						<input class="form-control" type="email" name="textField" />
					</div>
					<input type="hidden" name="process" value="level1" /> <input
							class="btn btn-info" type="submit" value="Xác nhận" />
				</div>
			</form>
		</c:otherwise>

	</c:choose>
	
	
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
			aria-hidden="true" style="display: none;">
				<div class="modal-dialog">
					<div class="loginmodal-container">
						<h1>New password</h1>
								<br>
					  <form:form modelAttribute="user" action="loginWithFacebook"
						method="POST">
						<input id="txtPassword" type="password" name="password"
							placeholder="Enter new password">
						<input id="txtRePassword" type="password" name="rePassword"
							placeholder="Re-enter new password">
							<form:errors path="password" />
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