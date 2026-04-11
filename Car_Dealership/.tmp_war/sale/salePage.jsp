<%-- 
    Document   : salePage
    Created on : 2/03/2025, 1:09:43 PM
    Author     : Admin_Coder
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

            <center>
                <a href="${pageContext.request.contextPath}/CustomerServlet">CUSTOMER</a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/CarsController">CAR </a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/ViewSTforSale">SERVICE TICKET</a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/ListParts">Parts</a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sale/CreateInvoice.jsp">Create Invoice</a> &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/LogoutController">Log Out</a> &nbsp;&nbsp;&nbsp;
                <a style="color: red">[${sessionScope.USER.salesName}]</a> 

                <p></p>
            </center>

        </c:otherwise>

    </c:choose>










</body>
</html>
