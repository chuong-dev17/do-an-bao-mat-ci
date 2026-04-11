<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Danh Sách Hóa Đơn</title>

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
        <%
            if (session.getAttribute("USER") == null) {
                response.sendRedirect("../index.html");
                return; // Important: Stop further processing if redirected
            }
        %>
        <div class="container">
            <h2><i class="bi bi-receipt"></i> Danh Sách Hóa Đơn</h2>

            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>Mã Hóa Đơn</th>
                            <th>Ngày Lập</th>
                            <th>Mã Nhân Viên</th>
                            <th>Mã Xe</th>
                            <th>Mã Khách Hàng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center">${InvoiceDetail.invoiceID}</td>
                            <td class="text-center">${InvoiceDetail.invoiceDate}</td>
                            <td class="text-center">${InvoiceDetail.salesID}</td>
                            <td class="text-center">${InvoiceDetail.carID}</td>
                            <td class="text-center">${InvoiceDetail.custID}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <h5><a class="text-center" href="ListMyInvoiceController">Back</a></h5>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

</body>
</html>
