<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Update Car</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="${pageContext.request.contextPath}/sale/loginSale.jsp?errorMechanic=You%20Must%20Login%20First%20!!!" />
            </c:when>
            <c:otherwise>
                <div class="container mt-4">
                    <h3><a href="${pageContext.request.contextPath}/CarsController" class="btn btn-secondary">Back</a></h3>
                    <h2>Update Car Information</h2>
                    <c:set var="car" value="${requestScope.carForUpdate}" />
                    <form action="${pageContext.request.contextPath}/UpdateCarController" method="post">
                        <div class="mb-3">
                            <label class="form-label">Car ID</label>
                            <input type="number" class="form-control" name="carID" value="${car.carID}" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Serial Number</label>
                            <input type="text" class="form-control" name="serialNumber" value="${car.serialNumber}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Model</label>
                            <input type="text" class="form-control" name="model" value="${car.model}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Color</label>
                            <input type="text" class="form-control" name="colour" value="${car.colour}" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Year</label>
                            <input type="number" class="form-control" name="year" value="${car.year}" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
        <br>
        <br>
    </body>
</html>
