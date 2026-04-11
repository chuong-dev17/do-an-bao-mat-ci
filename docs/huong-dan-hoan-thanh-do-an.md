# Hướng dẫn từng bước hoàn thành đồ án (CI/CD bảo mật, không cần thuê server)

Tài liệu này đi kèm repo mẫu có sẵn workflow GitHub Actions (Semgrep, SonarCloud, Car_Dealership smoke). Bạn có thể đọc thêm tóm tắt kiến trúc trong [plan-ci-cd-khong-can-server.md](plan-ci-cd-khong-can-server.md).

---

## Phần 1 — Chuẩn bị

<!-- ### Bước 1.1: Công cụ trên máy

<!-- - Cài [Git](https://git-scm.com/) và đăng nhập GitHub. -->
<!-- - (Khuyến nghị) Cài [Docker Desktop](https://www.docker.com/products/docker-desktop/) để chạy Car_Dealership bằng container. -->
<!-- - Trình duyệt để làm việc với GitHub, SonarCloud. -->

### Bước 1.2: Tài khoản dịch vụ

<!-- - **GitHub**: tài khoản miễn phí đủ cho repo public và runner hosted.
- **SonarQube Cloud** (nếu đồ án yêu cầu phân tích Sonar): đăng ký tại [sonarcloud.io](https://sonarcloud.io), có thể đăng nhập bằng GitHub. --> -->

### Bước 1.3: Hiểu luồng cần chứng minh trong báo cáo

Luồng tối thiểu thường được chấp nhận:

`Push code → GitHub Actions chạy → Công cụ scan (Semgrep / Sonar) → Kết quả (log, báo cáo, Code Scanning) → Test với Car_Dealership (Docker local + smoke job trên CI)`

---

<!-- ## Phần 2 — Đưa mã nguồn lên GitHub

### Bước 2.1: Tạo repository trên GitHub

1. Vào GitHub → **New repository**.
2. Đặt tên repo (ví dụ `do-an-bao-mat-ci`).
3. Chọn **Public** nếu muốn dùng Code Scanning (SARIF) miễn phí theo chính sách GitHub hiện tại; private vẫn chạy workflow nhưng SARIF/Code Scanning có thể bị giới hạn theo gói.
4. Không cần thêm README nếu bạn đã có sẵn thư mục dự án. -->

<!-- ### Bước 2.2: Đẩy code từ máy (nếu chưa có remote)

Trong thư mục dự án (có `.github/workflows/`, `src/`, `sonar-project.properties`):

```bash
git init
git add .
git commit -m "Initial: workflows Semgrep, SonarCloud, Car_Dealership smoke"
git branch -M main
git remote add origin https://github.com/<user>/<repo>.git
git push -u origin main
``` -->

Nếu nhánh mặc định của bạn là `master` hoặc `develop`, workflow đã cấu hình sẵn các nhánh đó trong file YAML; bạn có thể đổi nhánh hoặc sửa `on.push.branches` cho khớp.

---

## Phần 3 — Semgrep trên GitHub Actions (SAST)

### Bước 3.1: Xác nhận workflow chạy

1. Vào repo trên GitHub → tab **Actions**.
2. Chọn workflow **Security CI (Semgrep)**.
3. Mở run mới nhất sau lần push; đợi job **Semgrep scan** hoàn tất.

### Bước 3.2: Đọc kết quả phục vụ báo cáo

- Trong log bước **Run Semgrep (SARIF + log)**: ghi lại **thời gian chạy job**, **số cảnh báo** (nếu có trong output).
- Nếu repo public và upload SARIF thành công: vào **Security** → **Code scanning** để chụp màn hình danh sách finding (nếu có).

### Bước 3.3: (Tùy chọn) Muốn job fail khi có lỗi nghiêm trọng

Hiện workflow cố tình **không fail** khi Semgrep báo finding để pipeline dễ demo. Nếu thầy/cô yêu cầu “CI đỏ khi có lỗi”, bạn có thể đổi bước chạy Semgrep sang dùng `semgrep scan ... --error` và bỏ `set +e` / `true` — chỉnh trong [`.github/workflows/security-ci.yml`](../.github/workflows/security-ci.yml).

---

## Phần 4 — SonarQube Cloud

### Bước 4.1: Tạo project trên SonarCloud

1. Đăng nhập [SonarCloud](https://sonarcloud.io).
2. **Analyze new project** → chọn repo GitHub (cần cấp quyền SonarCloud truy cập GitHub nếu được hỏi).
3. Ghi nhận **Organization key** và **Project key** (hoặc lấy từ màn hình hướng dẫn tích hợp).

### Bước 4.2: Sửa `sonar-project.properties`

Mở file [`sonar-project.properties`](../sonar-project.properties) ở gốc repo, thay:

- `YOUR_SONARCLOUD_ORG_KEY` → organization key thật.
- `YOUR_SONARCLOUD_PROJECT_KEY` → project key thật.

Commit và push.

### Bước 4.3: Thêm secret `SONAR_TOKEN` trên GitHub

1. SonarCloud: **My Account** → **Security** → tạo **token** (Analyze).
2. GitHub repo → **Settings** → **Secrets and variables** → **Actions** → **New repository secret**.
3. Tên: `SONAR_TOKEN`, giá trị: token vừa tạo.

### Bước 4.4: Chạy workflow SonarCloud

1. Push một commit (hoặc **Re-run jobs** trên workflow **SonarCloud**).
2. Vào SonarCloud → project của bạn để xem **Issues**, **Security**, **Quality Gate**; chụp màn hình cho báo cáo.

Nếu job lỗi, đọc log bước scan: thường do sai `sonar.organization` / `sonar.projectKey` hoặc thiếu `SONAR_TOKEN`.

---

## Phần 5 — Car_Dealership (môi trường chạy thật của đồ án)

### Bước 5.1: Chạy Car_Dealership trên máy (Docker)

```bash
cd Car_Dealership
docker compose up --build
```

Mở trình duyệt: `http://localhost:8080`. Đây là bản chạy container của chính dự án đồ án để chụp minh chứng vận hành.

### Bước 5.2: Minh chứng trên CI (đã có sẵn)

Workflow [**Car_Dealership smoke (docker)**](../.github/workflows/juice-shop-smoke.yml) chạy khi push lên `main` / `master` / `develop`, hoặc chạy tay: Actions → workflow đó → **Run workflow**.

Trong báo cáo, giải thích: đây là bước **kiểm tra ứng dụng thật của đồ án** trong pipeline (build image + run container + `curl` smoke test).

---

## Phần 6 — Gộp mã nguồn đồ án của bạn

### Bước 6.1: Đặt mã vào đúng phạm vi scan

- **Semgrep** và **Sonar** đang quét toàn repo (Semgrep) và mã Java trong `Car_Dealership/src` (Sonar theo `sonar.sources=Car_Dealership/src`).
- Hãy đặt mã chính trong `Car_Dealership/` để đồng bộ với workflow Docker smoke và cấu hình Sonar.

### Bước 6.2: Push và kiểm tra lại cả ba workflow

Sau mỗi thay đổi lớn: xem tab **Actions**, đảm bảo các job cần thiết xanh (hoặc ghi nhận cố ý để job đỏ nếu bạn bật `--error`).

---

## Phần 7 — Thu thập minh chứng nộp bài

Chuẩn bị trong báo cáo hoặc phụ lục:

| Nội dung | Cách lấy |
|----------|----------|
| Ảnh pipeline | Tab **Actions**, chụp danh sách workflow / run thành công |
| File workflow | Copy hoặc trích đoạn từ `.github/workflows/*.yml` |
| Log scan | Vào từng run → mở job → copy đoạn log Semgrep / Sonar / Car_Dealership smoke |
| Số lỗi / cảnh báo | Log Semgrep; màn hình SonarCloud Issues; Code scanning (nếu có) |
| Thời gian | GitHub hiển thị duration mỗi job |
| Car_Dealership | Ảnh giao diện chạy Docker + kết quả smoke test trên Actions |

---

## Phần 8 — Gợi ý cấu trúc báo cáo

1. **Giới thiệu**: Mục tiêu đồ án, phạm vi (SAST, CI, môi trường lab).
2. **Kiến trúc**: Sơ đồ (có thể dùng lại mermaid trong [plan-ci-cd-khong-can-server.md](plan-ci-cd-khong-can-server.md)).
3. **Công cụ**: GitHub Actions, Semgrep, SonarCloud, Docker (Car_Dealership) — vai trò từng cái.
4. **Triển khai**: Các bước tương ứng Phần 2–5 của tài liệu này (trình bày ngắn, chi tiết để phụ lục).
5. **Kết quả**: Ảnh, bảng số liệu, nhận xét.
6. **Kết luận & hướng phát triển**: Ví dụ thêm DAST (ZAP), self-hosted runner, v.v.

---

## Phần 9 — Checklist trước khi nộp

- [ ] Repo đã push, có ít nhất một run **Security CI (Semgrep)** thành công.
- [ ] (Nếu bắt buộc Sonar) Đã điền đúng `sonar-project.properties` và secret `SONAR_TOKEN`; có ảnh hoặc link kết quả trên SonarCloud.
- [ ] Đã chạy Car_Dealership local bằng Docker hoặc có log job smoke trên Actions.
- [ ] Báo cáo có **ảnh pipeline**, **trích log**, **số finding / thời gian** theo yêu cầu thầy/cô.
- [ ] Đã nêu rõ: Semgrep/Sonar phân tích **mã trong repo**, smoke job chỉ kiểm tra khả năng khởi chạy/response cơ bản của Car_Dealership.

---

## Phần 10 — Xử lý sự cố thường gặp

| Hiện tượng | Hướng xử lý |
|------------|-------------|
| Workflow không chạy sau push | Kiểm tra nhánh có trong `on.push.branches` của file YAML; kiểm tra Actions có bị tắt trong Settings. |
| Upload SARIF lỗi | Repo private có thể cần tính năng trả phí cho Code Scanning; xem thông báo lỗi trong job. Vẫn có thể nộp bài bằng **log Semgrep**. |
| SonarCloud báo lỗi xác thực | Kiểm tra `SONAR_TOKEN`, organization/project key, và quyền token. |
| Car_Dealership smoke timeout | Lần đầu build image có thể lâu; có thể tăng vòng lặp chờ trong workflow hoặc chạy lại. |
| Hết phút GitHub Actions (repo private) | Giảm tần suất push, gộp commit, hoặc chạy workflow tùy chọn bằng `workflow_dispatch` thay vì mọi push (cần chỉnh YAML nếu muốn). |

---

## Tài liệu tham chiếu nhanh trong repo

| File | Mô tả ngắn |
|------|------------|
| [`.github/workflows/security-ci.yml`](../.github/workflows/security-ci.yml) | Semgrep + upload SARIF |
| [`.github/workflows/sonarcloud.yml`](../.github/workflows/sonarcloud.yml) | SonarCloud scan |
| [`.github/workflows/juice-shop-smoke.yml`](../.github/workflows/juice-shop-smoke.yml) | Car_Dealership smoke test trong CI |
| [`sonar-project.properties`](../sonar-project.properties) | Cấu hình Sonar |
| [`Car_Dealership/`](../Car_Dealership/) | Mã nguồn dự án Car_Dealership để scan/build |

Chúc bạn hoàn thành đồ án thuận lợi.
