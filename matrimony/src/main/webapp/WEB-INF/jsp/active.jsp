<%-- 
    Document   : activeAccount
    Created on : Sep 1, 2015, 9:42:23 PM
    Author     : SON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Kich hoat tai khoan</h1>
        <form action="/wedding_event_prj/active" method="POST">
            <label>Chúng tôi vừa gửi key active vào mail của bạn, hãy kiếm tra mail kể trong mục spam!!!Nhập key vào đây</label>
            <input type="text" name="activeKey" placeholder="Nhập key vào đây"/>
            <br/>
            <input type="submit" value="Kích hoạt"/>
        </form>
        <form action="/wedding_event_prj/resend" method="POST">
            <input type="submit" value="Gửi lại kích hoạt"/>
        </form>
    </body>
</html>
