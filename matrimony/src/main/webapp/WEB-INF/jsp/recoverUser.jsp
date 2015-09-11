<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/bundle/bootstrap.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<h1>${code }</h1>
	<c:choose>
		<c:when test="${not empty sessionScope.codeRecover}">
			<c:if test="${requestScope.wrongCode == 1}">
				<label class="alert alert-danger" style="width: 450px;">Sai code!</label>
			</c:if>
			<label class="alert alert-success" style="width: 450px;">Chúng
				tôi đã gửi mật khẩu đến Email của bạn, hãy kiểm tra. Chú ý kiểm tra
				chả mục spam!</label>
			<label class="label-control">Nhap ma trong mail de tiep tuc
				thiet lap lai mat khau</label>
			<br />
			<a href="index.jsp">Quay về trang chủ</a>
			<form action="/matrimony/recover" method="post"
				class="form-horizontal">
				<br /> <label>Nhập địa chỉ Email</label>
				<div class="form-group">
					<div class="col-sm-5">
						<input class="form-control" type="email" name="email" />
					</div>
					<input type="hidden" name="process" value="level2" /> <input
						class="btn btn-info" type="submit" value="Xác nhận" />
				</div>
			</form>

		</c:when>
		<c:otherwise>
			<c:if test="${requestScope.wrongEmail == 1}">
				<label class="alert alert-danger" style="width: 450px;">Email
					Không tồn tại!</label>
			</c:if>
			<label class="label-control">Để lấy lại mật khẩu, xin vui
				lòng nhập địa chỉ Email lúc bạn đăng ký</label>
			<br />
			<a href="index.jsp">Quay về trang chủ</a>
			<form action="/matrimony/recover" method="post"
				class="form-horizontal">
				<br /> <label>Nhập địa chỉ Email</label>
				<div class="form-group">
					<div class="col-sm-5">
						<input class="form-control" type="email" name="email" />
					</div>
					<input type="hidden" name="process" value="level1" /> <input
						class="btn btn-info" type="submit" value="Xác nhận" />
				</div>
			</form>
		</c:otherwise>

	</c:choose>
</body>
</body>

</html>