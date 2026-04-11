<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Service</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <c:set value="${service}" var="l"/>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Update Service</h1>
            <div class="card shadow p-4">
                <form action="UpdateServiceController" method="post" accept-charset="UTF-8">
                    <div class="mb-3">
                        <label class="form-label">Service ID</label>
                        <input type="text" name="serviceID" class="form-control" value="${l.serviceID}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Service Name</label>
                        <input type="text" name="serviceName" class="form-control" value="${l.serviceName}" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Hourly Rate</label>
                        <input type="number" name="hourlyRate" class="form-control" value="${l.hourlyRate}" required>
                    </div>
                    <button type="submit" class="btn btn-warning">Update</button>
                </form>
            </div>
        </div>

        <!-- Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
