<%-- 
    Document   : home
    Created on : Oct 17, 2022, 10:28:01 AM
    Author     : sonnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.account ne null}">
            Hello ${sessionScope.account.displayname},  click 
            <a href="logout">here</a> 
            to logout. <br/>
            <a class="header">${requestScope.account.username}</a>
            <a href="lecturer/timetable">TimeTable</a> <br/>
            <a href="lecturer/takeattandance">TakeAttandance</a> <br/>
            <a href="lecturer/attandacne">Attandance Report</a> 
        </c:if>
        <c:if test="${sessionScope.account eq null}">
            you are not logged in yet!
        </c:if>
    </body>
</html>
