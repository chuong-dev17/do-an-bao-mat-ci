<%-- 
    Document   : UpdateCustomer
    Created on : Mar 6, 2025, 3:37:26 PM
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

    <jsp:include page="salePage.jsp"/>

    <c:choose>


        <c:when  test="${empty sessionScope.USER}">
            <c:redirect url="sale/loginSale.jsp?errorMechanic=You Must Login First !!!"/>
        </c:when>


        <c:otherwise>

            <H3><a href="${pageContext.request.contextPath}/CustomerServlet">BACK</a></H3>
            <H2>UPDATE CUSTOMER</H2>
            <c:set var="c" value="${requestScope.custInforUpdate}"/>
            <form action="${pageContext.request.contextPath}/UpdateCustomerController" accept-charset="UTF-8" method="post"> 
                ID  <input type="text" name="custID" readonly="" value="${c.custID}"> <br>
                Name  <input type="text" name="custName" value="${c.custName}"> <br>
                Phone  <input type="text" name="custPhone" value="${c.phone}"> <br>
                Customer Sex  <input type="text" name="custSex" value="${c.sex}"> <br>
                Address <input type="text" name="custAddress" value="${c.cusAddress}"> <br>

                <input type="submit" value="UPDATE">
            </c:otherwise>

        </c:choose>



        </body>
        </html>
