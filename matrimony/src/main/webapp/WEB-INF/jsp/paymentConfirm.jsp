<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
		<t:homeLayout>
		<jsp:attribute name="head">
			<title>Payment confirm</title>
		</jsp:attribute>
			<jsp:attribute name="middle">
			<br/>
				<div class="alert alert-warning" role="alert">Vui lòng xác nhận thanh toán</div>
				<form action="paymentConfirm" method="POST">
					<input type='submit' class='btn btn-success' value='Xác nhận'/>
				</form>
			</jsp:attribute>
		</t:homeLayout>