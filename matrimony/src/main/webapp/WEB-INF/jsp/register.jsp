<%-- 
    Document   : Register
    Created on : Aug 31, 2015, 2:30:23 PM
    Author     : LaptopF5
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <form action="../account/qlogin" method="POST">
            <label>First Name</label>
            <input type="text" name="firstName"/><br/>
            <label>Middle Name</label>
            <input type="text" name="middleName"/></br>
            <label>Last Name</label>
            <input type="text" name="lastName"/></br>  
            <label>Gender</label>
            <input type="text" name="gender"/></br>
            <label>birthday</label>
            <input type="text" name="birthday"/></br> 
            <label>Marital Status</label>
            <input type="text" name="maritalStatus"/></br> 
            <label>Height</label>
            <input type="text" name="height"/></br> 
            <label>Weight</label>
            <input type="text" name="weight"/></br> 
            <label>country</label>
            <input type="text" name="country"/></br> 
            <label>city</label>
            <input type="text" name="city"/></br> 
            <label>Country Live In</label>
            <input type="text" name="countryLiveIn"/></br
            <label>City Live In</label>
            <input type="text" name="cityLiveIn"/></br> 
            <label>Contact Number</label>
            <input type="text" name="contactNumber"/></br> 
            <label>Religion</label>
            <input type="text" name="religion"/></br> 
            <label>Caste</label>
            <input type="text" name="caste"/></br> 
            <label>Introduction</label>
            <input type="text" name="introduction"/></br>
            <input type="submit" value="Login"/>
         </form>
    </body>
</html>
