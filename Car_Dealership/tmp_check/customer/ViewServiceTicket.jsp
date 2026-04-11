<%-- 
    Document   : ViewServiceTicket
    Created on : 16/03/2025, 5:35:43 PM
    Author     : Admin_Coder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <%
            if (session.getAttribute("USER") == null) {
                response.sendRedirect("../index.html");
                return; // Important: Stop further processing if redirected
            }
        %>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Danh Sách Phiếu Dịch Vụ</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

        <style>
            body {
                background-color: #f4f4f4;
                font-family: 'Arial', sans-serif;
                padding: 20px;
            }
            .container {
                max-width: 1100px;
                margin: auto;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                color: #d84315;
                font-weight: bold;
                text-align: center;
                margin-bottom: 20px;
            }
            .table {
                border-radius: 8px;
                overflow: hidden;
            }
            .table th {
                background-color: #d84315;
                color: white;
                text-align: center;
            }
            .table tbody tr:hover {
                background-color: #ffebee;
            }
            .btn-details {
                background: #d84315;
                color: white;
                border-radius: 5px;
                transition: 0.3s;
            }
            .btn-details:hover {
                background: #ff7043;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2><i class="bi bi-card-list"></i> Danh Sách Phiếu Dịch Vụ</h2>

            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>Mã Phiếu</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${data}" var="l">
                            <tr>
                                <td class="text-center">${l.serviceTicketID}</td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/DetailServiceTicketController?serviceTicketID=${l.serviceTicketID}" class="btn btn-sm btn-details">
                                        <i class="bi bi-eye"></i> Xem Chi Tiết
                                    </a>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
