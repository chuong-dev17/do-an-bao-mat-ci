# Hướng Dẫn Fix 2 Lỗi Bảo Mật

## Lỗi 1: XSS (Cross-Site Scripting)

**File:** `Car_Dealership/web/sale/loginSale.jsp`
**Dòng:** 62

### Mã lỗi (hiện tại)

```jsp
<h6 style="color: red">${ERROR_MESSAGE}</h6>
```

### Mã đã fix

```jsp
<h6 style="color: red"><c:out value="${ERROR_MESSAGE}"/></h6>
```

### Giải thích

`${ERROR_MESSAGE}` in trực tiếp giá trị vào HTML mà không escape. Kẻ tấn công có thể nhập `<script>alert(1)</script>` để thực thi JavaScript.

`<c:out>` của JSTL sẽ tự động escape các ký tự HTML như `<`, `>`, `&`, `"`, `'` thành `&lt;`, `&gt;`, `&amp;`, `&quot;`, `&#x27;`.

---

## Lỗi 2: Hardcoded Credentials

**File:** `Car_Dealership/src/java/utils/DBUtil.java`
**Dòng:** 24-27

### Mã lỗi (hiện tại)

```java
String username = "sa";
String password = "12345";
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
String url = "jdbc:sqlserver://host.docker.internal:1433;databaseName=Car_Dealership;encrypt=true;trustServerCertificate=true";
conn = DriverManager.getConnection(url, username, password);
```

### Mã đã fix

```java
String username = System.getenv("DB_USERNAME");
String password = System.getenv("DB_PASSWORD");
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
String url = System.getenv("DB_URL");
conn = DriverManager.getConnection(url, username, password);
```

### Giải thích

Credential hardcode trong source code bị lộ nếu:
- Code bị push lên public repo
- Code bị leak qua breach
- Developer không may để lộ trong commit history

**Giải pháp:** Sử dụng biến môi trường (`System.getenv()`)

### Cách thêm biến môi trường

**Windows (CMD):**
```cmd
set DB_USERNAME=sa
set DB_PASSWORD=your_password
set DB_URL=jdbc:sqlserver://host.docker.internal:1433;databaseName=Car_Dealership;encrypt=true;trustServerCertificate=true
```

**Windows (PowerShell):**
```powershell
$env:DB_USERNAME="sa"
$env:DB_PASSWORD="your_password"
$env:DB_URL="jdbc:sqlserver://host.docker.internal:1433;databaseName=Car_Dealership;encrypt=true;trustServerCertificate=true"
```

**GitHub Actions (.github/workflows/ci.yml):**
```yaml
env:
  DB_USERNAME: ${{ secrets.DB_USERNAME }}
  DB_PASSWORD: ${{ secrets.DB_PASSWORD }}
  DB_URL: ${{ secrets.DB_URL }}
```

**Docker:**
```yaml
environment:
  - DB_USERNAME=sa
  - DB_PASSWORD=your_password
  - DB_URL=jdbc:sqlserver://host.docker.internal:1433;databaseName=Car_Dealership;encrypt=true;trustServerCertificate=true
```

---

## Xác nhận đã fix

Sau khi fix, chạy lại Semgrep:

```bash
semgrep scan --config .semgrep.yml --strict Car_Dealership/src Car_Dealership/web
```

Kết quả mong đợi: **0 findings**
