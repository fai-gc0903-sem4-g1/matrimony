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
		<form action="payment" method="POST">
			<span class="alert-danger">Loại ${paymentResponse }</span>
			<label>Loại</label><br/>
			<input type="radio" name="productValue" value="1"/>
			<span id="price"><span id="currencyCode">USD</span>&nbsp;49.99</span>
			<br/>
			<input type="radio" name="productValue" value="12"/>
			<span id="price"><span id="currencyCode">USD</span>&nbsp;499.99</span>
			<br/>
			<label>Thanh toán bằng</label><br/>
			<select name="payWith">
				<option>Chọn loại thanh toán</option>
				<option>Paypal</option>
				<option>Credit card</option>
			</select>
			<input type="submit" value="Pay now"/><br/>
		</form>
	</jsp:body>
</t:layout>