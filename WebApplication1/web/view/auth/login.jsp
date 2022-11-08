<%-- 
    Document   : login
    Created on : Nov 3, 2022, 9:26:11 PM
    Author     : minh0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST">
            Username: <input type="text" name="username"/> <br/>
            password: <input type="password" name="password"  /> <br/>
            Remember Me <input type="checkbox" name="remember"/> <br/>
            <input type="submit" value="Login" /> 
        </form> 
    </body>
</html>
