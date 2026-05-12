<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Profile</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }
            .container {
                max-width: 600px;
                margin-top: 50px;
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            h2 {
                text-align: center;
                margin-bottom: 30px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                font-weight: bold;
            }
            .form-group input, .form-group select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border-radius: 4px;
                border: 1px solid #ced4da;
            }
            .form-group select {
                padding: 8px;
            }
            .btn-primary {
                width: 100%;
                padding: 10px;
                background-color: #007bff;
                border: none;
                border-radius: 4px;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }
            .alert {
                margin-top: 20px;
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
            <h2>Update Your Profile</h2>
            <form action="${pageContext.request.contextPath}/UpdateProfileController" method="post" accept-charset="UTF-8">
                <div class="form-group">
                    <label for="custName">Full Name</label>
                    <input type="text" name="custName" id="custName" value="<c:out value='${USER.custName}'/>" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="text" name="phone" id="phone" value="<c:out value='${USER.phone}'/>" class="form-control" required />
                </div>
                <div class="form-group">
                    <label for="sex">Gender</label>
                    <select name="sex" id="sex" class="form-select" required>
                        <option value="M" <c:if test="${USER.sex == 'M'}">selected</c:if>>M</option>
                        <option value="F" <c:if test="${USER.sex == 'F'}">selected</c:if>>F</option>
                        <option value="other" <c:if test="${USER.sex == 'other'}">selected</c:if>>Other</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="cusAddress">Address</label>
                    <input type="text" name="cusAddress" id="cusAddress" value="<c:out value='${USER.cusAddress}'/>" class="form-control" required />
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
            </form>
            <br>
            <h6 style="color: #1abc9c"><c:out value="${success}"/></h6>
        </div>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
