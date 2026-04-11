<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Ticket Search</title>

    <!-- Bootstrap CSS (optional for styling) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .form-container {
            margin-bottom: 30px;
        }
        .table-container {
            margin-top: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Search Service Tickets</h2>
        <form action="searchSTController" method="post">
            <div class="mb-3">
                <label for="custID" class="form-label">Customer ID</label>
                <input type="text" class="form-control" id="custID" name="custID" value="${param.custID}" placeholder="Enter Customer ID">
            </div>
            <div class="mb-3">
                <label for="carID" class="form-label">Car ID</label>
                <input type="text" class="form-control" id="carID" name="carID" value="${param.carID}" placeholder="Enter Car ID">
            </div>
            <div class="mb-3">
                <label for="dateReceived" class="form-label">Date Received</label>
                <input type="date" class="form-control" id="dateReceived" name="dateReceived" value="${param.dateReceived}" placeholder="Enter Date Received">
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>

    <!-- Table to display results -->
    <div class="table-container">
        <h3>Search Results</h3>
        <table>
            <thead>
                <tr>
                    <th>Service Ticket ID</th>
                    <th>Date Received</th>
                    <th>Date Return</th>
                    <th>Customer ID</th>
                    <th>Car ID</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${data}">
                    <tr>
                        <td>${s.serviceTicketID}</td>
                        <td>${s.dateReceived}</td>
                        <td>${s.dateReturned}</td>
                        <td>${s.custID}</td>
                        <td>${s.carID}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <!-- Bootstrap JS (optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
