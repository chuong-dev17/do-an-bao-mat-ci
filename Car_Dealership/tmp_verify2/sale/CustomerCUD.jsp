<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Management</title>
        <script type="text/javascript">
            var contextPath = "${pageContext.request.contextPath}";

            function doDelete(custID) {
                if (custID && confirm("Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xÃ³a khÃ¡ch hÃ ng vá»›i ID: " + custID + " khÃ´ng?")) {
                    // XÃ³a khÃ¡ch hÃ ng vÃ  chuyá»ƒn hÆ°á»›ng
                    window.location.href = contextPath + "/DeleteCustomerController?custID=" + custID;
                }
            }
        </script>
        <style>
            html, body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .content {
                max-width: 1000px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                color: #333;
            }
            h3 {
                color: #2c3e50;
            }
            a {
                text-decoration: none;
                color: #3498db;
            }
            a:hover {
                text-decoration: underline;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #2c3e50;
                color: white;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .search-box {
                margin-bottom: 20px;
            }
            .search-box input[type="text"] {
                padding: 8px;
                width: 250px;
                margin-right: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .search-box input[type="submit"] {
                padding: 8px 15px;
                background-color: #2c3e50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .search-box input[type="submit"]:hover {
                background-color: #34495e;
            }
            .error-message {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="sale/loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>
            <c:otherwise>
                <div class="content">
                    <h2>Customer Page</h2>
                    <h3><a href="sale/AddCustomer.jsp">Add Customer</a></h3>
                    <form action="${pageContext.request.contextPath}/CustomerServlet" class="search-box">
                        <input type="text" name="custName" placeholder="TÃªn khÃ¡ch hÃ ng" value="${param.custName}">
                        <input type="submit" value="TÃ¬m kiáº¿m">
                    </form>
                    <table>
                        <tr>
                            <th>Customer ID</th>
                            <th>Customer Name</th>
                            <th>Customer Phone</th>
                            <th>Customer Sex</th>
                            <th>Customer Address</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                        <c:forEach items="${requestScope.custList}" var="c">
                            <tr>
                                <td>${c.custID}</td>
                                <td>${c.custName}</td>
                                <td>${c.phone}</td>
                                <td>${c.sex}</td>
                                <td>${c.cusAddress}</td>
                                <td><a href="${pageContext.request.contextPath}/UpdateCustomerController?custID=${c.custID}">Update</a></td>
                                <td><a href="#" onclick="doDelete('${c.custID}')">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <h3 class="error-message">${requestScope.Idexist}</h3>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
