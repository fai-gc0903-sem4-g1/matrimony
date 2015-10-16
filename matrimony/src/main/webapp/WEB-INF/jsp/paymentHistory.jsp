<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="transactionDAO" class="com.matrimony.database.TransactionDAO" />

<t:homeLayout>
	<jsp:attribute name="head">
			<title>Lịch sử giao dịch</title>
		</jsp:attribute>
	<jsp:attribute name="middle">
			<br />
			Các lần giao dịch của bạn
			<c:forEach var="i" items="${transactionDAO.viewAllBillTransaction(sessionScope.user.id) }">
			<h4>${i.key}</h4>
			<div>
				${i.value }
			</div>
			<br/>
			</c:forEach>
					
	</jsp:attribute>
</t:homeLayout>