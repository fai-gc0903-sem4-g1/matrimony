<%-- 
    Document   : settings
    Created on : Sep 5, 2015, 9:01:44 PM
    Author     : SON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../bundle/bootstrap.jsp"/>
<jsp:include page="../bundle/jstl.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Settings page</title>
    </head>
    <body>
        <h1>Settings page</h1>
        <p>Session account ${empty sessionScope.account}</p>
        <!--<p>${sessionScope.account.username}</p>-->
    </body>
</html>
