<%-- 
    Document   : ServiceTicket
    Created on : Mar 6, 2025, 7:59:16 PM
    Author     : tdinh
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


        <c:choose>

            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>

            <c:otherwise>
                <jsp:include page="salePage.jsp" />


                <h2>SERVICE TICKET PAGE</h2>

                <h4><a href="sale/CreateServiceTicket.jsp">CREATE NEW SERVICE TICKET</a></h4>
                <table border="1px">
                    <tr>
                        <th>Service Ticket ID</th>
                        <th>Date Received</th>
                        <th>Date Return</th>
                        <th>Customer ID</th>
                        <th>Car ID</th>
                    </tr>
                    <c:forEach var="s" items="${requestScope.getServiceTicket}">
                        <tr>
                            <td>${s.serviceTicketID}</td>
                            <td>${s.dateReceived}</td>
                            <td>${s.dateReturned}</td>
                            <td>${s.custID}</td>
                            <td>${s.carID}</td> 

                        </tr>

                    </c:forEach>    
                </table>
                <h3 style="color: red">${requestScope.ERROR}</h3>

                <h3 style="color: red">${requestScope.serviceTicketIDExist}</h3>
                <h3 style="color: red"> ${requestScope.CarId}</h3>
                <h3 style="color: red">${requestScope.CustId}</h3>


            </c:otherwise>
        </c:choose>






    </body>
</html>
