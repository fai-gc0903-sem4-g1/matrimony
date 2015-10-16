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
<title>Payment</title>
</jsp:attribute>
	<jsp:body>
		<form action="paymentVerify" method="POST">
			<label>Để xác nhận giao dịch, vui lòng nhập mã giao dịch Paypal</label><br/>
			<input type="text" name="transactionId"/>
			<input type="submit"/>
		</form>
	</jsp:body>
</t:layout>
