# Hướng dẫn chạy test đồ án Car_Dealership (CI/CD bảo mật)

Tài liệu này hướng dẫn cách **chạy và test ứng dụng** mà không cần khởi tạo hay cấu hình gì thêm. Ứng dụng đã sẵn sàng để chạy.

---

## 1. Chạy Car_Dealership trên máy (Docker)

### Yêu cầu

- **Docker Desktop**: Cần cài đặt [Docker Desktop](https://www.docker.com/products/docker-desktop/) trên máy để chạy container.
- **Trình duyệt web**: Để kiểm tra ứng dụng sau khi chạy.

### Các bước

1. Mở terminal/command prompt, chuyển tới thư mục Car_Dealership:

``ash
cd Car_Dealership
``

2. Chạy Docker Compose để build và chạy container:

``ash
docker compose up --build
``

   - Lần đầu sẽ mất thời gian để download image và build.
   - Khi thấy log dừng ở các dòng như Server running on port 8080 hoặc tương tự, ứng dụng đã sẵn sàng.

3. Mở trình duyệt và truy cập:

``
http://localhost:8080
``

4. Kiểm tra các chức năng chính:
   - **Trang đăng nhập**: Có form login cho Customer, Mechanic, Sale.
   - **Trang chính**: Giao diện quản lý nhiều chức năng (xe, khách hàng, phiếu bảo dưỡng, v.v.).
   - **Chức năng cơ bản**: Thêm/xoá/sửa dữ liệu nếu có quyền truy cập.

5. Để dừng ứng dụng, bấm `Ctrl + C` trong terminal.

---

## 2. Test tự động trên GitHub Actions (CI/CD)

Nếu đã push code lên GitHub, có thể kiểm tra kết quả test tự động qua Actions.

### Xem kết quả pipeline

1. Vào repo trên GitHub → tab **Actions**.
2. Bạn sẽ thấy danh sách các workflow có sẵn:
   - **Security CI (Semgrep)**: Quét lỗ hổng bảo mật bằng Semgrep.
   - **SonarCloud**: Phân tích chất lượng code (nếu đã cấu hình).
   - **Car_Dealership smoke (docker)**: Chạy container và test smoke.

3. Chọn workflow bất kỳ → xem run gần nhất → kiểm tra trạng thái (✓ thành công hay ✗ thất bại).

4. Mở job để xem **log chi tiết** (thời gian chạy, số lỗi tìm được, v.v.).

### Chạy lại workflow

Nếu cần chạy lại (không cần push):

1. Chọn workflow → chọn run cũ nhất → bấm **Re-run all jobs** (góc phải).
2. GitHub sẽ chạy lại tất cả các job.

---

## 3. Xem báo cáo chi tiết

### Semgrep (SAST - tìm lỗ hổng)

- Vào workflow **Security CI (Semgrep)** → mở job **Semgrep scan**.
- Log sẽ hiển thị:
  - **Số cảnh báo** tìm được (nếu có).
  - **Thời gian chạy** (duration).
  - **Danh sách lỗi** chi tiết (nếu muốn).

### SonarCloud (SAST + phân tích chất lượng)

- Vào [SonarCloud](https://sonarcloud.io) → đăng nhập → chọn project.
- Xem:
  - **Issues**: Danh sách các lỗi/khuyến nghị.
  - **Security**: Các vấn đề bảo mật.
  - **Coverage**: Độ che phủ code (nếu có).
  - **Quality Gate**: Trạng thái tổng quát (pass/fail).

### Car_Dealership smoke test

- Vào workflow **Car_Dealership smoke (docker)** → job chi tiết.
- Log sẽ hiển thị:
  - **Build time**: Thời gian build image.
  - **Run time**: Thời gian khởi chạy container.
  - **Smoke test result**: Kết quả kiểm tra cơ bản (HTTP status code, response).

---

## 4. Checklist kiểm tra trước nộp bài

- [ ] Ứng dụng Car_Dealership chạy thành công trên Docker local (http://localhost:8080).
- [ ] Ít nhất một workflow chạy thành công trên GitHub Actions.
- [ ] Có ảnh/log của kết quả từ từng công cụ (Semgrep, SonarCloud, smoke test).
- [ ] Biết được:
  - Số cảnh báo/lỗi tìm được từ mỗi công cụ.
  - Thời gian chạy mỗi job.
  - Trạng thái của pipeline (pass/fail).

---

## 5. Xử lý sự cố thường gặp

| Hiện tượng | Hướng xử lý |
|------------|-------------|
| Docker không chạy | Đảm bảo Docker Desktop đã cài và khởi động. Kiểm tra terminal không có lỗi khi chạy docker compose up. |
| Lỗi port 8080 đã được dùng | Tắt những ứng dụng/container khác dùng cổng 8080, hoặc sửa docker-compose.yml để dùng cổng khác (ví dụ 8081). |
| Workflow không chạy trên GitHub | Kiểm tra tab Actions có bị tắt trong **Settings**. Kiểm tra .github/workflows/*.yml có nhánh main/master/develop trong on.push.branches. |
| SonarCloud không quét | Kiểm tra sonar-project.properties, SONAR_TOKEN secret, và organization/project key có đúng không. |
| Smoke test timeout | Lần đầu build image có thể lâu. Cho phép 10-15 phút và chạy lại nếu cần. |

---

## 6. Tài liệu tham chiếu

| File | Mô tả |
|------|-------|
| [Car_Dealership/docker-compose.yml](../Car_Dealership/docker-compose.yml) | Cấu hình Docker Compose |
| [Car_Dealership/Dockerfile](../Car_Dealership/Dockerfile) | Cấu hình Docker image |
| [.github/workflows/security-ci.yml](../.github/workflows/security-ci.yml) | Workflow Semgrep |
| [.github/workflows/sonarcloud.yml](../.github/workflows/sonarcloud.yml) | Workflow SonarCloud |
| [.github/workflows/juice-shop-smoke.yml](../.github/workflows/juice-shop-smoke.yml) | Workflow smoke test |
| [sonar-project.properties](../sonar-project.properties) | Cấu hình Sonar |
| [Tóm tắt kiến trúc](plan-ci-cd-khong-can-server.md) | Sơ đồ và chi tiết kiến trúc |

---

Chúc bạn chạy test thành công!
