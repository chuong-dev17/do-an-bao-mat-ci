<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Update Customer</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                display: flex;
            }
            .main-content {
                margin-left: 0px; /* Adjusted to remove the left margin */
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
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>
            <c:otherwise>
                <div class="main-content">
                    <h3><a href="${pageContext.request.contextPath}/CustomerServlet">BACK</a></h3>
                    <h2>UPDATE CUSTOMER</h2>
                    <c:set var="c" value="${requestScope.custInforUpdate}"/>
                    <form action="${pageContext.request.contextPath}/UpdateCustomerController" accept-charset="UTF-8" method="post">
                        <label>ID</label>
                        <input type="text" name="custID" readonly value="${c.custID}" class="form-control"> <br>
                        <label>Name</label>
                        <input type="text" name="custName" value="${c.custName}" class="form-control"> <br>
                        <label>Phone</label>
                        <input type="text" name="custPhone" value="${c.phone}" class="form-control"> <br>
                        <label>Customer Sex</label>
                        <input type="text" name="custSex" value="${c.sex}" class="form-control"> <br>
                        <label>Address</label>
                        <input type="text" name="custAddress" value="${c.cusAddress}" class="form-control"> <br>
                        <input type="submit" value="UPDATE" class="btn btn-primary">
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
        <br>
        <br>
    </body>
</html>