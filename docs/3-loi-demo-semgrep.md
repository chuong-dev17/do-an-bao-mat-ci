# 3 Lỗi Dễ Demo Cho Semgrep Trong Dự Án Car_Dealership

Tài liệu này tóm tắt 3 lỗi bảo mật dễ demo nhất trong dự án, kèm vị trí dòng và cách fix.

## 1) XSS (Cross-Site Scripting)

- File: [Car_Dealership/web/xss-demo.jsp](../Car_Dealership/web/xss-demo.jsp)
- Dòng lỗi:
  - [Dòng 17](../Car_Dealership/web/xss-demo.jsp#L17): `${param.q}` được in thẳng ra HTML
  - [Dòng 18](../Car_Dealership/web/xss-demo.jsp#L18): `<%= request.getParameter("q") %>` in scriptlet trực tiếp

### Vì sao là lỗi

Giá trị từ người dùng được render thẳng vào trang mà không escape. Nếu nhập payload như `<script>alert(1)</script>`, trình duyệt sẽ thực thi JavaScript.

### Cách fix

- Dùng `<c:out value="${param.q}"/>` thay cho `${param.q}`
- Tránh dùng `<%= ... %>` để in dữ liệu người dùng
- Nếu cần hiển thị trong input/textarea, vẫn phải escape đúng context

### Ví dụ sửa

```jsp
<p>Safe output: <c:out value="${param.q}"/></p>
```

## 2) Hardcoded secret/password

- File: [Car_Dealership/src/java/utils/DBUtil.java](../Car_Dealership/src/java/utils/DBUtil.java)
- Dòng lỗi:
  - [Dòng 23](../Car_Dealership/src/java/utils/DBUtil.java#L23): `String username = "sa";`
  - [Dòng 24](../Car_Dealership/src/java/utils/DBUtil.java#L24): `String password = "12345";`
  - [Dòng 26](../Car_Dealership/src/java/utils/DBUtil.java#L26): chuỗi kết nối DB hardcode trực tiếp

### Vì sao là lỗi

Thông tin đăng nhập database được lưu trực tiếp trong source code. Nếu source bị lộ, kẻ tấn công có thể lấy luôn credential để truy cập DB.

### Cách fix

- Đưa username/password/connection string ra biến môi trường hoặc file cấu hình ngoài source
- Dùng secret manager hoặc biến môi trường trong CI/CD
- Không commit credential thật vào repo

### Ví dụ sửa

```java
String username = System.getenv("DB_USERNAME");
String password = System.getenv("DB_PASSWORD");
String url = System.getenv("DB_URL");
```

## 3) Weak authentication / login chỉ theo tên

- File:
  - [Car_Dealership/src/java/controller/LoginMechanicController.java](../Car_Dealership/src/java/controller/LoginMechanicController.java)
  - [Car_Dealership/src/java/controller/LoginSalerController.java](../Car_Dealership/src/java/controller/LoginSalerController.java)
  - [Car_Dealership/src/java/daos/UserDAO.java](../Car_Dealership/src/java/daos/UserDAO.java)
- Dòng lỗi:
  - [LoginMechanicController.java dòng 85-86](../Car_Dealership/src/java/controller/LoginMechanicController.java#L85)
  - [LoginSalerController.java dòng 85-86](../Car_Dealership/src/java/controller/LoginSalerController.java#L85)
  - [UserDAO.java dòng 44-50](../Car_Dealership/src/java/daos/UserDAO.java#L44)
  - [UserDAO.java dòng 71-85](../Car_Dealership/src/java/daos/UserDAO.java#L71)

### Vì sao là lỗi

Luồng đăng nhập chỉ lấy `name` từ request rồi truy vấn theo tên. Không có mật khẩu, không có hash, không có kiểm tra nhiều yếu tố. Điều này khiến việc đăng nhập quá yếu và có thể bị giả mạo.

### Cách fix

- Bắt buộc có mật khẩu khi đăng nhập
- Lưu mật khẩu dạng hash mạnh, ví dụ bcrypt/Argon2
- So sánh mật khẩu đã hash thay vì so sánh plain text
- Nếu cần phân quyền, thêm role và kiểm tra session sau khi xác thực đúng

### Ví dụ hướng sửa

```java
String name = request.getParameter("name");
String password = request.getParameter("password");
// query user by name, verify password hash, then create session
```

## Kết luận

Ba lỗi trên là bộ demo tốt cho Semgrep vì:

- XSS: dễ thấy ngay trên UI, dễ trình diễn payload
- Hardcoded secret: Semgrep bắt rõ bằng rule secret scanning
- Weak authentication: thể hiện lỗi logic nghiệp vụ và auth design

Nếu muốn demo nhanh, nên chạy theo thứ tự:
1. XSS trên [xss-demo.jsp](../Car_Dealership/web/xss-demo.jsp)
2. Hardcoded credential trên [DBUtil.java](../Car_Dealership/src/java/utils/DBUtil.java)
3. Weak auth trên [UserDAO.java](../Car_Dealership/src/java/daos/UserDAO.java) và controller login
