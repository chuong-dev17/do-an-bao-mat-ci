<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>
            <c:otherwise>
               
                <div class="main-content">
                    <h3><a href="${pageContext.request.contextPath}/CarsController">Back</a></h3>
                    <h2>ADD CAR</h2>
                    <form action="${pageContext.request.contextPath}/AddNewCar" accept-charset="UTF-8">
                        Car ID  <input type="number" name="carID" required> <br>
                        Serial Number  <input type="text" name="serialNumber" required><br>
                        Model <input type="text" name="model" required><br>
                        Colour <input type="text" name="colour" required><br>
                        Year <input type="number" name="year" required><br>
                        <input type="submit" value="Add">
                    </form>
                    <h3 style="color: red">${requestScope.carsExist}</h3>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
