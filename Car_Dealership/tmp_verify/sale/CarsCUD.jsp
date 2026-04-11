<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script type="text/javascript">
            var contextPath = "${pageContext.request.contextPath}";
            function doDelete(carID) {
                if (carID && confirm("Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xÃ³a xe vá»›i ID: " + carID + " khÃ´ng?")) {
                    window.location.href = contextPath + "/DeleteCarController?carID=" + carID;
                }
            }
        </script>
        <style>
            .btn-custom {
                padding: 0.375rem 0.75rem; /* Same padding as btn-warning */
                font-size: 1rem; /* Same font-size as btn-warning */
            }
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>
            <c:otherwise>
                <div class="container mt-4">
                    <h2 class="mb-4">CAR PAGE</h2>
                    <h3><a href="sale/AddCar.jsp" class="btn btn-primary">Add New Car</a></h3>

                    <form action="${pageContext.request.contextPath}/CarsController" accept-charset="UTF-8" method="GET" class="my-3">
                        <div class="form-group">
                            <label for="serialNumber">Serial Number</label>
                            <input type="text" class="form-control" id="serialNumber" name="serialNumber" value="${param.serialNumber}">
                        </div>
                        <div class="form-group">
                            <label for="model">Model</label>
                            <input type="text" class="form-control" id="model" name="model" value="${param.model}">
                        </div>
                        <div class="form-group">
                            <label for="year">Year</label>
                            <input type="number" class="form-control" id="year" name="year" value="${param.year}">
                        </div>
                        <button type="submit" class="btn btn-primary mt-3">Search</button>
                    </form>

                    <h3 style="color: red">${requestScope.carCheck}</h3>

                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Car ID</th>
                                <th>Serial Number</th>
                                <th>Model</th>
                                <th>Color</th>
                                <th>Year</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${requestScope.carList}">
                                <tr>
                                    <td>${c.carID}</td>
                                    <td>${c.serialNumber}</td>
                                    <td>${c.model}</td>
                                    <td>${c.colour}</td>
                                    <td>${c.year}</td>
                                    <td><a href="${pageContext.request.contextPath}/UpdateCarController?carId=${c.carID}" class="btn btn-warning">Update</a>
                                        <button class="btn btn-danger btn-custom" onclick="doDelete('${c.carID}')">
                                            <i class="bi bi-trash"></i> XÃ³a
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
        <br>
        <br>
    </body>
</html>