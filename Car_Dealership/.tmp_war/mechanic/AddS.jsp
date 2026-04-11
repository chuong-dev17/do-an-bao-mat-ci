<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Service</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Add Service</h1>
            <div class="card shadow p-4">
                <form action="${pageContext.request.contextPath}/AddServiceController" method="post" accept-charset="UTF-8">
                    <div class="mb-3">
                        <label class="form-label">Service ID</label>
                        <input type="text" name="serviceID" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Service Name</label>
                        <input type="text" name="serviceName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Hourly Rate</label>
                        <input type="number" name="hourlyRate" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Service</button>
                </form>
                <!-- Hiển thị lỗi nếu có -->
                <h3 class="text-danger mt-3">${error}</h3>
            </div>
        </div>

        <!-- Bootstrap JavaScript (nếu cần dùng các tính năng JS của Bootstrap như modal, dropdown, v.v.) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
