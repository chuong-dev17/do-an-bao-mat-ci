<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đăng Nhập Nhân Viên Bán Hàng</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            width: 100%;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .login-container img {
            width: 80px;
            height: 80px;
            margin-bottom: 15px;
        }
        .form-control {
            margin-bottom: 10px;
        }
        .btn-login {
            background-color: #ff6600;
            color: white;
            font-weight: bold;
        }
        .btn-login:hover {
            background-color: #cc5200;
        }
    </style>
</head>
<body>

<div class="d-flex justify-content-center align-items-center min-vh-100">
    <div class="login-container">
        <!-- Ảnh đại diện -->
        <img src="./img/saler_icon.png" alt="Sales Icon">
        
        <h3 class="mb-3">Đăng Nhập Nhân Viên Bán Hàng</h3>

        <form action=" ${pageContext.request.contextPath}/LoginSalerController" method="post">
            <div class="mb-3">
                <input type="text" class="form-control" id="uname" name="name" placeholder="Nhập tên của bạn" required>
            </div>

            <button type="submit" name="login" value="Login" class="btn btn-login w-100">Đăng Nhập</button>
        </form>
            <br>
            <a href="index.html">back</a>
               <h6 style="color: red">${param.errorMechanic}</h6>
    </div>
</div>
     
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
