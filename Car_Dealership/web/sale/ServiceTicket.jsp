<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                display: flex;
            }
            .main-content {
                width: 100%;
                padding: 20px;
            }
            .user-info {
                margin-top: 20px;
                padding: 10px;
                background-color: #34495e;
                text-align: center;
                border-radius: 5px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid black;
                padding: 10px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>
            <c:otherwise>
                <div class="main-content">
                    <h2>SERVICE TICKET PAGE</h2>
                    <h4><a href="sale/CreateServiceTicket.jsp">CREATE NEW SERVICE TICKET</a></h4>
                    
                    <!-- Service Ticket Table -->
                    <table>
                        <thead>
                            <tr>
                                <th>Service Ticket ID</th>
                                <th>Date Received</th>
                                <th>Date Return</th>
                                <th>Customer ID</th>
                                <th>Car ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${requestScope.getServiceTicket}">
                                <tr>
                                    <td>${s.serviceTicketID}</td>
                                    <td>${s.dateReceived}</td>
                                    <td>${s.dateReturned}</td>
                                    <td>${s.custID}</td>
                                    <td>${s.carID}</td> 
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                    <!-- Error Messages -->
                    <c:if test="${not empty requestScope.ERROR}">
                        <h3 style="color: red">${requestScope.ERROR}</h3>
                    </c:if>
                    <c:if test="${not empty requestScope.serviceTicketIDExist}">
                        <h3 style="color: red">${requestScope.serviceTicketIDExist}</h3>
                    </c:if>
                    <c:if test="${not empty requestScope.CarId}">
                        <h3 style="color: red">${requestScope.CarId}</h3>
                    </c:if>
                    <c:if test="${not empty requestScope.CustId}">
                        <h3 style="color: red">${requestScope.CustId}</h3>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
        <br>
        <br>
    </body>
</html>
