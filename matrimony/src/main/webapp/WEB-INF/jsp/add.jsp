<%-- 
    Document   : add
    Created on : Sep 4, 2015, 10:48:20 AM
    Author     : SON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form modelAttribute="student" action="add" method="POST">
            <br/>
            Name
            <input type="text" name="name" />
            <form:errors path="name" cssClass="error"/>
            <br/>
            Age
            <input type="number" name="age"/>
            <form:errors path="age" cssClass="error"/>
            <br/>
            <input type="submit"/>
        </form:form>
    </body>
</html>
