<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                height: 100vh;
                overflow: hidden;
            }
            .navbar {
                background-color: #2c3e50;
                padding: 10px 20px;
            }
            .navbar-brand {
                color: white;
                font-size: 22px;
                font-weight: bold;
            }
            .navbar-brand img {
                height: 40px;
                margin-right: 10px;
            }
            .navbar .welcome-text {
                color: white;
                font-size: 18px;
            }
            .sidebar {
                width: 250px;
                background: #34495e;
                color: white;
                padding: 20px;
                display: flex;
                flex-direction: column;
                height: calc(100vh - 56px);
            }
            .sidebar .list-group-item {
                background: #34495e;
                color: white;
                border: none;
                text-align: center;
                transition: 0.3s;
            }
            .sidebar .list-group-item:hover {
                background: #1abc9c;
                color: white;
            }
            .content {
                flex-grow: 1;
                padding: 20px;
            }
            iframe {
                width: 100%;
                height: calc(100vh - 56px);
                border: none;
            }
        </style>
    </head>
    <body>
        <%
            if (session.getAttribute("USER") == null) {
                response.sendRedirect("index.html");
                return; // Important: Stop further processing if redirected
            }
        %>
        <!-- Navbar -->
        <nav class="navbar navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand d-flex align-items-center" href="#">
                    <img src="./img/machanic_icon.png" alt="Logo">
                </a>
                <span class="welcome-text"><i class="bi bi-person-circle"></i> ChÃ o má»«ng, ${USER.mechanicName}!</span>
            </div>
        </nav>

        <div class="d-flex">
            <!-- Sidebar -->
            <div class="sidebar d-flex flex-column">
                <div class="list-group flex-grow-1">
                    <a href="#" class="list-group-item" onclick="loadPage('${pageContext.request.contextPath}/ListServiceController')">
                        <i class="bi bi-wrench"></i> List Service
                    </a>
                    <a href="#" class="list-group-item" onclick="loadPage('${pageContext.request.contextPath}/ListServiceMechanicController')">
                        <i class="bi bi-tools"></i> List Service Mechanic
                    </a>
                    <a href="#" class="list-group-item" onclick="loadPage('${pageContext.request.contextPath}/ViewSTforMechanic')">
                        <i class="bi bi-tools"></i>Service Ticket
                    </a>
                </div>
                <div class="mt-auto">
                    <a href="${pageContext.request.contextPath}/LogoutController" class="list-group-item list-group-item-danger">
                        <i class="bi bi-box-arrow-right"></i> Logout
                    </a>
                </div>
            </div>

            <!-- Content -->
            <div class="content">
                <iframe id="contentFrame" src="" title="Noi dung chinh"></iframe>
            </div>
        </div>

        <!-- Script -->
        <script>
            function loadPage(url) {
                document.getElementById("contentFrame").src = url;
            }
        </script>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
