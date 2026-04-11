<%-- 
    Document   : AddCustomer
    Created on : Mar 6, 2025, 2:56:48 PM
    Author     : tdinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:include page="salePage.jsp"/>


        <c:choose>

            <c:when test="${empty sessionScope.USER}">
                <c:redirect url="loginSale.jsp?errorMechanic=You Must Login First !!!"/>
            </c:when>

            <c:otherwise>

              
                <H3><a href="${pageContext.request.contextPath}/CustomerServlet">BACK</a></H3>
                  <h2>ADD CUSTOMER</h2>
                <form action="${pageContext.request.contextPath}/AddCustomerServletController" accept-charset="UTF-8">
                    ID  <input type="text" name="custID" placeholder="custID" required=""> <br>
                    Name  <input type="text" name="custName" placeholder="custName" required=""> <br>
                    Phone  <input type="text" name="custPhone" placeholder="custPhone" required=""> <br>
                    Customer Sex  <input type="text" name="custSex" placeholder="custSex" required=""> <br>
                    Address <input type="text" name="custAddress" placeholder="custAddress" required=""> <br>

                    <input type="submit" value="ADD"> 
                </form>
                <h4 style="color: red">${requestScope.custExist}</h4>
            </c:otherwise>

        </c:choose>



    </body>


</html>
