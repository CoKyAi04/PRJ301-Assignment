<%-- 
    Document   : timetable
    Created on : Oct 20, 2022, 9:22:31 PM
    Author     : Co Ky Ai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="util.DateTimeHelper"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="http://localhost:9999/AssignmentcuaCoKyAi/home">Home</a><br/>
        <a href="http://localhost:9999/AssignmentcuaCoKyAi/logout">Logout</a><br/>
        Lecturer: <input type="text" readonly="readonly" value="${requestScope.lecturer.name}"/>
        <form action="timetable" method="GET">
            From: <input type="date" name="from" value="${requestScope.from}"/>
            To: <input type="date" name="to" value="${requestScope.to}"/>
            <input type="submit" value="View"/> 
        </form>
        <table border="1px">
            <tr>
                <td> </td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>${d}<br/>${helper.getDayNameofWeek(d)}</td>
                    </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="slot">
                <tr>
                    <td>${slot.description}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sessions}" var="ses">
                                <c:if test="${helper.compare(ses.date,d) eq 0 and (ses.timeslot.id eq slot.id)}">
                                    <a href = "takeattandance?id=${ses.id}"> ${ses.group.name}-${ses.group.subject.name}</a>
                                    <br/>
                                    ${ses.room.name}
                                    <c:if test="${ses.attanded}">
                                        <a> <br>(Attended) </a>
                                        </c:if>
                                        <c:if test="${!ses.attanded}">
                                        <a> <br>(Not yet) </a>
                                        </c:if>
                                    </c:if>

                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
