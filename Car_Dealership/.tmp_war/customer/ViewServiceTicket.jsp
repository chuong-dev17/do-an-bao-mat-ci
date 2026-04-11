<%-- 
    Document   : ViewServiceTicket
    Created on : 16/03/2025, 5:35:43 PM
    Author     : Admin_Coder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>Service Ticket ID</th>
                    <th>Date Received</th>
                    <th>Date Return</th>
                    <th>Customer ID</th>
                    <th>Car ID</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${data}" var="l">
                    <tr>
                        <td>${l.serviceTicketID}</td>
                        <td>${l.dateReceived}</td>
                        <td>${l.dateReturned}</td>
                        <td>${l.custID}</td>
                        <td>${l.carID}</td>
                        <td><a href="#">Detail</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
