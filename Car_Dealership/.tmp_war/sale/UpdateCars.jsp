<%-- 
    Document   : UpdateCars
    Created on : Mar 6, 2025, 6:55:36 PM
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
                <c:redirect url="sale/loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>


            <c:otherwise>

                <jsp:include page="salePage.jsp" />
               
                <h3><a href="${pageContext.request.contextPath}/CarsController">Back</a></h3>
                <H2>UPDATE CAR</H2>
                <c:set var="c" value="${requestScope.carForUpdate}" />

                <form action="${pageContext.request.contextPath}/UpdateCarController" accept-charset="UTF-8" method="post">
                    Car ID  <input type="number" name="carID" value="${requestScope.carForUpdate.carID}" readonly=""> <br>
                    Serial Number  <input type="text" name="serialNumber" value="${requestScope.carForUpdate.serialNumber}"><br>
                    Model <input type="text" name="model" value="${requestScope.carForUpdate.model}"><br>
                    Color <input type="text" name="colour" value="${requestScope.carForUpdate.colour}" ><br>
                    Year <input type="number" name="year" value="${requestScope.carForUpdate.year}"><br>
                    <input type="submit" value="Add">

                </form>


            </c:otherwise>

        </c:choose>







    </body>
</html>
