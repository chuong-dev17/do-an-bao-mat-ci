<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Service Ticket</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <c:choose>
                <c:when test="${empty sessionScope.USER}">
                    <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
                </c:when>
                <c:otherwise>
                    <h2 class="mb-4">Create New Service Ticket</h2>
                    <a href="${pageContext.request.contextPath}/ViewSTforSale" class="btn btn-secondary mb-3">Back</a>
                    <form action="${pageContext.request.contextPath}/CreateServiceTicketController" accept-charset="UTF-8" class="border p-4">
                        <div class="form-group">
                            <label>Ticket ID:</label>
                            <input type="number" name="serviceTicketID" class="form-control" required min="-2147483648" max="2147483647">
                        </div>
                        <div class="form-group">
                            <label>Date Received:</label>
                            <input type="text" name="dateReceived" class="form-control" placeholder="YYYY/MM/DD" required>
                        </div>
                        <div class="form-group">
                            <label>Date Returned:</label>
                            <input type="text" name="dateReturned" class="form-control" placeholder="YYYY/MM/DD" required>
                        </div>
                        <div class="form-group">
                            <label>Customer ID:</label>
                            <input type="number" name="custID" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Car ID:</label>
                            <input type="number" name="carID" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
