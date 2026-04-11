<%-- 
    Document   : ServiceTicket
    Created on : Mar 6, 2025, 7:59:16 PM
    Author     : tdinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form action="searchSTController" method="post">
            <input type="text" name="custID" value="${param.custID}" placeholder="custID"/><br>
                    <input type="text" name="carID" value="${param.carID}" placeholder="carID"/><br>
                    <input type="text" name="dateReceived" value="${param.dateReceived}" placeholder="dateReceived"/><br>
                    <input type="submit" value="Search" />
                </form>
                <table border="1px">
                    <tr>
                        <th>Service Ticket ID</th>
                        <th>Date Received</th>
                        <th>Date Return</th>
                        <th>Customer ID</th>
                        <th>Car ID</th>
                    </tr>
                    <c:forEach var="s" items="${data}">
                        <tr>
                            <td>${s.serviceTicketID}</td>
                            <td>${s.dateReceived}</td>
                            <td>${s.dateReturned}</td>
                            <td>${s.custID}</td>
                            <td>${s.carID}</td> 
                        </tr>
                    </c:forEach>    
                </table>
    </body>
</html>
