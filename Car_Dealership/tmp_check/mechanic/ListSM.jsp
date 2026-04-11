<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Service Mechanic List</title>

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
        <div class="container">
            <h2 class="text-center mb-4">Service Mechanic List</h2>

            <table class="table table-hover table-bordered">
                <thead>
                    <tr class="text-center">
                        <th>Ticket ID</th>
                        <th>Service ID</th>
                        <th>Mechanic ID</th>
                        <th>Hours</th>
                        <th>Comment</th>
                        <th>Rate</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dataSM}" var="l">
                        <tr>
                            <td class="text-center">${l.serviceTicketID}</td>
                            <td class="text-center">${l.serviceID}</td>
                            <td class="text-center">${l.mechanicID}</td>
                            <td class="text-center">${l.hours}</td>
                            <td>${l.comment}</td>
                            <td class="text-end">${l.rate} vnđ</td>
                            <td class="text-center">
                                <a href="UpdateServiceMechanicController?serviceTicketID=${l.serviceTicketID}&serviceID=${l.serviceID}" 
                                   class="btn btn-primary btn-sm btn-action">
                                    <i class="bi bi-pencil-square"></i> Update
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
