<%-- 
    Document   : attandancereport
    Created on : Nov 4, 2022, 12:24:19 AM
    Author     : minh0
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="report" method="post">
            <input type="hidden" name="lid" value="${requestScope.lecturer.id}"/>
            <select name="gid">
                <c:forEach items="${requestScope.groups}" var="gid">
                    <option value="${gid.id}">${gid.subject.name}-${gid.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Save"/>
        </form>

        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                    <c:forEach items="${requestScope.sessions}" var="ses">
                    <th>${ses.index}</th>
                    </c:forEach>
            </tr>
            <tbody>
                <c:forEach items="${requestScope.students}" var="s">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.name}</td>
                        <c:forEach items="${requestScope.sessions}" var="ses">
                            <td>
                                <c:forEach items="${requestScope.attendances}" var="a">
                                    <c:if test="${(a.student.id eq s.id) and (a.session.index eq ses.index)}">
                                        <c:if test="${a.present}"> 
                                            x
                                        </c:if>
                                        <c:if test="${!a.present}"> 
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <table>
            <tr>
                <th>% Absent</th>
            </tr>
            <tbody>
                <c:forEach items="${requestScope.totals}" var="total">
                    <tr>
                        <c:if test="${total > 20}">
                            <td style="background-color: red">${total}%</td>
                        </c:if>
                        <c:if test="${total<=20}">
                            <td style="background-color: green">${total}%</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
