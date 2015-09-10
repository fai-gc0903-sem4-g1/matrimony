<%@page pageEncoding="UTF8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
	<jsp:body>
		<title>new title</title>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<h1>User profile</h1>
		${sessionScope.user.name }
	</jsp:body>
</t:layout>