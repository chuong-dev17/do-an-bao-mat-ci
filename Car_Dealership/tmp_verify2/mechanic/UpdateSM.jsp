<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Service Mechanic</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                min-height: 100vh;
                display: flex;
                flex-direction: column;
            }
            .container {
                flex: 1;
            }
        </style>
    </head>
    <body>
        <c:set value="${serviceSM}" var="l"/>
        <div class="container mt-5">
            <h1 class="text-center mb-4">Update Service Mechanic</h1>
            <div class="card shadow p-4">
                <form action="UpdateServiceMechanicController" method="post" accept-charset="UTF-8">
                    <div class="mb-3">
                        <label class="form-label">Service Ticket ID</label>
                        <input type="text" name="serviceTicketID" class="form-control" value="${l.serviceTicketID}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Service ID</label>
                        <input type="text" name="serviceID" class="form-control" value="${l.serviceID}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mechanic ID</label>
                        <input type="text" name="mechanicID" class="form-control" value="${l.mechanicID}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Hours</label>
                        <input type="number" name="hours" class="form-control" value="${l.hours}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Comment</label>
                        <input type="text" name="comment" class="form-control" value="${l.comment}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Rate</label>
                        <input type="number" name="rate" class="form-control" value="${l.rate}">
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button>
                    <a href="${pageContext.request.contextPath}/ListServiceController" style="margin-left: 20px">Back</a>
                </form>
            </div>
        </div>
                    <br>
                    <br>
                    <br>
                    <br>
        <!-- Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
