<%-- 
    Document   : CreateServiceTicket
    Created on : Mar 6, 2025, 8:16:54 PM
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

        <c:choose>

            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>

            <c:otherwise>

                <jsp:include page="salePage.jsp"/>

               
                <h3><a href="${pageContext.request.contextPath}/ViewSTforSale">Back</a></h3>
                <h4>CREATE NEW SERVICE TICKET</h4>
                <form action="${pageContext.request.contextPath}/CreateServiceTicketController"  accept-charset="UTF-8">
                    Ticket ID: <input type="number" name="serviceTicketID" required="" > <br>
                    Date Received: <input type="text" name="dateReceived" placeholder="YYYY/MM/DD" required=""> <br>
                    Date Returned: <input type="text" name="dateReturned" placeholder="YYYY/MM/DD" required=""> <br>
                    Customer ID: <input type="number" name="custID" required=""> <br>
                    Car ID: <input type="number" name="carID"  required=""> <br>
                    <input type="submit" value="Create">

                </form>

            </c:otherwise>

        </c:choose>



    </body>
</html>
