<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Parts List</title>
        <!-- Bootstrap CSS Link -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            body {
                background-color: #ecf0f1;
                font-family: 'Arial', sans-serif;
                padding: 20px;
            }
            .container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                color: #34495e;
                font-weight: bold;
                text-align: center;
                margin-bottom: 20px;
            }
            .table thead {
                background: #34495e;
                color: white;
            }
            .table tbody tr:hover {
                background-color: #dfe6e9;
            }
            .btn-custom {
                border-radius: 5px;
                transition: 0.3s;
            }
            .btn-custom:hover {
                opacity: 0.8;
            }
        </style>
        <script type="text/javascript">
            function doDelete(partID) {
                var confirmation = confirm("Are you sure to delete Part id= '" + partID + "'?");
                if (confirmation) {
                    window.location.href = "DeleteParts?partID=" + partID;
                }
            }
        </script>
    </head>
    <body>

        <div class="container mt-5">
            <h1 class="text-center mb-4">Parts List</h1>

            <!-- Search Form -->
            <form method="get" action="SearchParts" accept-charset="UTF-8" class="mb-4">
                <div class="input-group">
                    <input type="text" class="form-control" name="search" value="${param.search}" placeholder="Search parts..." />
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </form>

            <!-- Parts Table -->
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Part ID</th>
                        <th>Part Name</th>
                        <th>Purchase Price</th>
                        <th>Retail Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${data}" var="l">
                        <tr>
                            <td>${l.partID}</td>
                            <td>${l.partName}</td>
                            <td>${l.purchasePrice}</td>
                            <td>${l.retailPrice}</td>
                            <td>
                                <button class="btn btn-danger btn-sm btn-custom" onclick="doDelete('${l.partID}')">
                                    <i class="bi bi-trash"></i> Xóa
                                </button>
                                <a href="UpdateParts?partID=${l.partID}" class="btn btn-warning btn-sm">Update</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Add Part Link -->
            <div class="text-center mt-3">
                <a href="sale/AddP.jsp" class="btn btn-success">
                    <i class="bi bi-plus-circle"></i> Add New Part
                </a>
            </div>
        </div>
        <br>
        <br>
        <br>
        <br>
        <!-- Bootstrap JS and dependencies (Optional for interactive elements) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
