<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Danh Sách Dịch Vụ</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

        <script type="text/javascript">
            function doDelete(serviceID) {
                if (serviceID && confirm("Bạn có chắc chắn muốn xóa dịch vụ ID = '" + serviceID + "' không?")) {
                    window.location.href = "DeleteServiceController?serviceID=" + serviceID;
                }
            }
        </script>

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
    </head>
    <body>
        <div class="container">
            <h2><i class="bi bi-tools"></i> Danh Sách Dịch Vụ</h2>

            <div class="d-flex justify-content-between mb-3">
                <a href="mechanic/AddS.jsp" class="btn btn-success btn-custom">
                    <i class="bi bi-plus-circle"></i> Thêm Dịch Vụ Mới
                </a>
            </div>

            <div class="table-responsive">
                <table class="table table-hover table-bordered text-center">
                    <thead>
                        <tr>
                            <th>Mã Dịch Vụ</th>
                            <th>Tên Dịch Vụ</th>
                            <th>Giá (VNĐ/giờ)</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${data}" var="c">
                        <tr>
                            <td>${c.serviceID}</td>
                            <td>${c.serviceName}</td>
                            <td class="text-end">${c.hourlyRate} vnđ</td>
                            <td>
                                <a href="UpdateServiceController?serviceID=${c.serviceID}" class="btn btn-primary btn-sm btn-custom">
                                    <i class="bi bi-pencil-square"></i> Cập Nhật
                                </a>
                                <button class="btn btn-danger btn-sm btn-custom" onclick="doDelete('${c.serviceID}')">
                                    <i class="bi bi-trash"></i> Xóa
                                </button>
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
