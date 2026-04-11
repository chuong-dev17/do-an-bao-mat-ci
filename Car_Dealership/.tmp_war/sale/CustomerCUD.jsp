<%-- 
    Document   : CustomerCUD
    Created on : Mar 6, 2025, 2:27:12 PM
    Author     : tdinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script type="text/javascript">
            var contextPath = "${pageContext.request.contextPath}";
            function doDelete(custID) {
                if (custID && confirm("Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xÃ³a dá»‹ch vá»¥ vá»›i ID: " + custID + " khÃ´ng?")) {
                    window.location.href = contextPath + "/DeleteCustomerController?custID=" + (custID);
                }
            }
        </script>

    </head>
    <body>

        <c:choose>
            
            <c:when test="${empty sessionScope.USER}">
                 <c:redirect url="sale/loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>
  
            <c:otherwise>

                <jsp:include page="salePage.jsp" />

                <H2>CUSTOMER PAGE</H2>
                <h3><a href="sale/AddCustomer.jsp">Add Customer</a></h3>

                <form action="${pageContext.request.contextPath}/CustomerServlet" accept-charset="UTF-8">
                    <input type="text" name="custName" placeholder="TÃªn khÃ¡ch hÃ ng" value="${param.custName}">
                    <input type="submit" value="TÃ¬m kiáº¿m">
                </form>

                <table border="1px">
                    <tr>

                        <th>Customer ID</th>
                        <th>Customer Name</th>
                        <th>Customer Phone</th>
                        <th>Customer Sex</th>
                        <th>Customer Address</th>
                        <th>Update</th>
                        <th>Delete</th>
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
                    <h3 style="color: red">${requestScope.Idexist}</h3>


                </table>

            </c:otherwise>

        </c:choose>








    </body>
</html>
