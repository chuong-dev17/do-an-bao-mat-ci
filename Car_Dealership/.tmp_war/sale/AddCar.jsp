<%-- 
    Document   : AddCar
    Created on : Mar 6, 2025, 6:57:35 PM
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
                <jsp:include page="salePage.jsp" />
                <h3><a href="${pageContext.request.contextPath}/CarsController">Back</a></h3>
                <H2>ADD CAR</H2>
                <form action="${pageContext.request.contextPath}/AddNewCar" accept-charset="UTF-8">
                    Car ID  <input type="number" name="carID" required=""> <br>
                    Serial Number  <input type="text" name="serialNumber" required=""><br>
                    Model <input type="text" name="model" required=""><br>
                    Colour <input type="text" name="colour" required=""><br>
                    Year <input type="number" name="year" required=""><br>
                    <input type="submit" value="Add">

                </form>
                <h3 style="color: red">${requestScope.carsExist}</h3>

            </c:otherwise>


        </c:choose>

        


    </body>
</html>
