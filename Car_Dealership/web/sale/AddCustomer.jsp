<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                display: flex;
            }
            .sidebar {
                width: 250px;
                height: 100vh;
                background-color: #2c3e50;
                color: white;
                padding: 15px;
                position: fixed;
            }
            .sidebar a {
                color: white;
                text-decoration: none;
                display: block;
                padding: 10px;
            }
            .sidebar a:hover {
                background-color: #34495e;
            }
            .main-content {
                margin-left: 250px;
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
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid black;
                padding: 10px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>
            <c:otherwise>
                <div class="sidebar">
                    <h4>Logo</h4>
                    <div class="user-info">
                        🔵 Chào mừng, ${sessionScope.USER.salesName}!
                    </div>
                    <a href="${pageContext.request.contextPath}/CustomerServlet">👤 CUSTOMER</a>
                    <a href="${pageContext.request.contextPath}/CarsController">🚗 CAR</a>
                    <a href="${pageContext.request.contextPath}/ViewSTforSale">📄 SERVICE TICKET</a>
                    <a href="${pageContext.request.contextPath}/ListParts">🛠️ Parts</a>
                    <a href="${pageContext.request.contextPath}/sale/CreateInvoice.jsp">📝 Create Invoice</a>
                    <a href="${pageContext.request.contextPath}/LogoutController">🚪 Logout</a>
                </div>
                <div class="main-content">
                    <h2>ADD CUSTOMER</h2>
                    <h3><a href="${pageContext.request.contextPath}/CustomerServlet">BACK</a></h3>
                    <form action="${pageContext.request.contextPath}/AddCustomerServletController" accept-charset="UTF-8">
                        <label>ID</label>  <input type="text" name="custID" placeholder="custID" required=""> <br>
                        <label>Name</label>  <input type="text" name="custName" placeholder="custName" required=""> <br>
                        <label>Phone</label>  <input type="text" name="custPhone" placeholder="custPhone" required=""> <br>
                        <label>Customer Sex</label>  <input type="text" name="custSex" placeholder="custSex" required=""> <br>
                        <label>Address</label> <input type="text" name="custAddress" placeholder="custAddress" required=""> <br>
                        <input type="submit" value="ADD"> 
                    </form>
                    <h4 style="color: red">${requestScope.custExist}</h4>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
