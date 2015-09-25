<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<fmt:setBundle basename="com.matrimony.i18n.StringResource" scope="session"/>
<%-- <c:set var="lang" value="${not empty param.language?param.language:null}" scope="session" /> --%>

<%-- <c:if test="${not empty lang}"> --%>
    <fmt:setLocale value="vi_VN" scope="session"/>
<%-- </c:if> --%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="vi_VN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	I18nnnnnnnn
	Giới tính:
	<fmt:message key="male" />
</body>
</html>