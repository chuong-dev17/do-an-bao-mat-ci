<%-- 
    Document   : DetailServiceTicket
    Created on : 18/03/2025, 4:05:04 PM
    Author     : Admin_Coder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                padding: 20px;
            }
            .container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .table thead {
                background: #007bff;
                color: white;
            }
            .btn-action {
                margin-right: 5px;
            }
        </style>
    </head>
    <body>
        <%
            if (session.getAttribute("USER") == null) {
                response.sendRedirect("../index.html");
                return; // Important: Stop further processing if redirected
            }
        %>
        <table class="table table-hover table-bordered">
            <thead>
                <tr class="text-center">
                    <th>MÃ£ dá»‹ch vá»¥</th>
                    <th>MÃ£ thá»£ sá»­a chá»¯a</th>
                    <th>Giá» lÃ m</th>
                    <th>ÄÃ¡nh giÃ¡</th>
                    <th>GiÃ¡ tiá»n</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${detailM}" var="l">
                    <tr>
                        <td class="text-center">${l.serviceID}</td>
                        <td class="text-center">${l.mechanicID}</td>
                        <td class="text-center">${l.hours}</td>
                        <td>${l.comment}</td>
                        <td class="text-end">${l.rate} vnÄ‘</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <table class="table table-hover table-bordered">
            <thead>
                <tr class="text-center">
                    <th>MÃ£ linh kiá»‡n</th>
                    <th>Sá»‘ lÆ°á»£ng</th>
                    <th>GiÃ¡ tiá»n</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${detailP}" var="p">
                    <tr>
                        <td class="text-center">${p.partID}</td>
                        <td class="text-center">${p.numberUsed}</td>
                        <td class="text-center">${p.price}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h5><a class="text-center" href="ViewSTforCustomerController">Back</a></h5>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
