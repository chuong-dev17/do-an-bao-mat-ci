<%-- 
    Document   : CarsCUD
    Created on : Mar 6, 2025, 6:28:02 PM
    Author     : tdinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


        <script type="text/javascript">
            var contextPath = "${pageContext.request.contextPath}";
            function doDelete(carID) {
                if (carID && confirm("Bạn có chắc chắn muốn xóa dịch vụ với ID: " + carID + " không?")) {
                    window.location.href = contextPath + "/DeleteCarController?carID=" + (carID);
                }
            }
        </script>
    </head>
    <body>
        <c:choose>

            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>

            <c:otherwise>

                
                
            <jsp:include page="salePage.jsp" />
            <H2>CAR PAGE</H2>
            <h3><a href="sale/AddCar.jsp">Add New Car</a></h3>
            <form action="${pageContext.request.contextPath}/CarsController" accept-charset="UTF-8">
                Serial number  <input type="text" name="serialNumber" value="${param.serialNumber}"><br>
                Model  <input type="text" name="model" value="${param.model}"><br>
                Year <input type="number" name="year" value="${param.year}"><br>
                <input type="submit" value="Search">
            </form>  <br>

            <h3 style="color: red">${requestScope.carCheck}</h3>
            <table border="1px">
                <tr>
                    <th>Car ID </th> 
                    <th>Serial Number</th>
                    <th>Model</th>
                    <th>Color</th>
                    <th>Year</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="c" items="${requestScope.carList}">
                    <tr>
                        <td>${c.carID}</td>
                        <td>${c.serialNumber}</td>
                        <td>${c.model}</td>
                        <td>${c.colour}</td>
                        <td>${c.year}</td>
                        <td><a href="${pageContext.request.contextPath}/UpdateCarController?carId=${c.carID}">Update</a></td>
                        <td><a href="#" onclick="doDelete('${c.carID}')">Delete</a></td>
                    </tr>
                </c:forEach>

            </table>      
            </c:otherwise>



        </c:choose>






    </body>
</html>
