<%-- 
    Document   : index
    Created on : Aug 29, 2015, 7:00:15 PM
    Author     : SON
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../bundle/bootstrap.jsp"/>
<jsp:include page="../bundle/jstl.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   
    </head>
    <body>
        
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <jsp:include page="joinUs.jsp"/>
            </c:when>
            <c:when test="${sessionScope.user.verified==false }">
            	<jsp:include page="active.jsp"/>
            </c:when>
            <c:otherwise>
               <jsp:include page="home.jsp"/>
            </c:otherwise>
        </c:choose>
    </body>
</html>