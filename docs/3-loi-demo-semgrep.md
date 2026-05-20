# 3 Lỗi Dễ Demo Cho Semgrep Trong Dự Án Car_Dealership

Tài liệu này đã được cập nhật theo code hiện tại của dự án. 3 ví dụ dưới đây đều còn có thể demo được ngay, kèm vị trí dòng và cách xử lý.

## 1) XSS qua giá trị nhập vào ô login

- File: [Car_Dealership/web/sale/loginSale.jsp](../Car_Dealership/web/sale/loginSale.jsp)
- Dòng lỗi:
  - [Dòng 55](../Car_Dealership/web/sale/loginSale.jsp#L55): `value="${name}"` in dữ liệu người dùng thẳng vào thuộc tính HTML

### Demo nhanh

Nhập payload như `"><img src=x onerror=alert(1)>` vào ô tên đăng nhập. Khi trang render lại giá trị này, payload có thể thoát khỏi thuộc tính `value` và thực thi JavaScript.

### Vì sao là lỗi

Giá trị từ request được phản chiếu trực tiếp ra HTML mà không escape theo đúng context. Đây là kiểu XSS rất dễ bị Semgrep bắt và rất dễ trình diễn trên UI.

### Cách fix

- Dùng `<c:out value="${name}"/>` thay cho `${name}` trong thuộc tính HTML
- Không render trực tiếp dữ liệu người dùng nếu chưa escape theo context
- Nếu hiển thị trong `input`, luôn escape cả dấu nháy và ký tự đặc biệt

### Ví dụ sửa

```jsp
<input type="text" name="name" value="<c:out value='${name}'/>">
```

## 2) Hardcoded credential / secret trong tài liệu dự án

- File: [Car_Dealership/README.md](../Car_Dealership/README.md)
- Dòng lỗi:
  - [Dòng 35](../Car_Dealership/README.md#L35): `Cập nhật tài khoản kết nối SQL Server (mặc định: sa / 12345)`

### Demo nhanh

Chỉ cần mở README hoặc quét secret trong repo là thấy credential mặc định được ghi rõ. Đây là ví dụ dễ demo vì nội dung lộ ngay trong source-controlled file.

### Vì sao là lỗi

Credential mặc định được public trong repo sẽ tạo thói quen dùng mật khẩu yếu và có thể bị tái sử dụng ngoài môi trường demo. Với Semgrep/secret scanning, đây là loại lỗi rất dễ chứng minh.

### Cách fix

- Không ghi credential thật hoặc mặc định vào README public
- Đưa thông tin cấu hình sang biến môi trường hoặc file `.env` không commit
- Nếu cần hướng dẫn cài đặt, chỉ ghi placeholder như `DB_USERNAME`, `DB_PASSWORD`

### Ví dụ sửa

```md
Cập nhật tài khoản kết nối SQL Server bằng biến môi trường:
DB_USERNAME, DB_PASSWORD, DB_URL
```

## 3) Xóa dữ liệu qua GET, không có CSRF token

- File:
  - [Car_Dealership/web/sale/CarsCUD.jsp](../Car_Dealership/web/sale/CarsCUD.jsp)
  - [Car_Dealership/src/java/controller/DeleteCarController.java](../Car_Dealership/src/java/controller/DeleteCarController.java)
- Dòng lỗi:
  - [CarsCUD.jsp dòng 14](../Car_Dealership/web/sale/CarsCUD.jsp#L14): điều hướng xóa bằng `window.location.href = contextPath + "/DeleteCarController?carID=" + carID;`
  - [DeleteCarController.java dòng 40](../Car_Dealership/src/java/controller/DeleteCarController.java#L40): controller nhận `carID` từ query string
  - [DeleteCarController.java dòng 67](../Car_Dealership/src/java/controller/DeleteCarController.java#L67): xóa trực tiếp qua GET

### Demo nhanh

Sau khi đăng nhập, mở console hoặc tạo URL như `/DeleteCarController?carID=...`. Vì thao tác xóa dùng GET và không có CSRF token, một request giả mạo cũng có thể kích hoạt xóa.

### Vì sao là lỗi

Thao tác thay đổi trạng thái hệ thống không nên đi qua GET. Không có CSRF token thì một trang ngoài có thể lừa trình duyệt gửi request xóa thay cho người dùng đang đăng nhập.

### Cách fix

- Chuyển xóa sang POST hoặc DELETE
- Bổ sung CSRF token cho form/request
- Kiểm tra quyền của session trước khi xóa

### Ví dụ sửa

```java
// dùng POST + CSRF token thay vì điều hướng bằng GET
```

## Kết luận

Ba lỗi này phù hợp để demo Semgrep vì:

- XSS: thấy ngay trên UI và dễ chạy payload
- Hardcoded secret: lộ ngay trong file được quản lý bởi git
- CSRF/unsafe GET: dễ chứng minh bằng một request giả mạo

Nếu cần demo theo thứ tự, nên chạy:
1. XSS trên [loginSale.jsp](../Car_Dealership/web/sale/loginSale.jsp)
2. Hardcoded credential trên [README.md](../Car_Dealership/README.md)
3. Unsafe GET delete trên [CarsCUD.jsp](../Car_Dealership/web/sale/CarsCUD.jsp) và [DeleteCarController.java](../Car_Dealership/src/java/controller/DeleteCarController.java)
