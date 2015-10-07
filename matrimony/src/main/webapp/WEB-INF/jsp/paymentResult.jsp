<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<t:homeLayout>
		<jsp:attribute name="head">
			<title>Payment confirm</title>
		</jsp:attribute>
			<jsp:attribute name="middle">
			<br/>
				<c:choose>
					<c:when test="${not empty paymentResultSuccess }">
					<div class="alert alert-success" role="alert">
					  <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
					  <span class="sr-only">Success:</span>
					  Tài khoản của bạn đã được gia tăng thời hạn sử dụng đến ngày ${sessionScope.user.expiries }
					</div>
					</c:when>
					<c:when test="${not empty paymentResultFailed }">
					<div class="alert alert-danger" role="alert">
					  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					  <span class="sr-only">Error:</span>
					Có lỗi xảy ra, thanh toán không thành công
					</div>
					</c:when>
					<c:otherwise>
						Không xác định
					</c:otherwise>
				</c:choose>
			</jsp:attribute>
		</t:homeLayout>