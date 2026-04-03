# Kiến trúc tối ưu không thuê server + mẫu GitHub Actions

Bản lưu tài liệu kế hoạch (để đính kèm repo hoặc báo cáo). Triển khai tương ứng nằm trong repo: `.github/workflows/security-ci.yml`, `sonarcloud.yml`, `juice-shop-smoke.yml`, `sonar-project.properties`, `src/main.py`.

## Luồng tổng quan

```mermaid
flowchart LR
  subgraph dev [Máy dev]
    Code[Code + commit]
    Docker[Docker: Juice Shop]
  end
  subgraph github [GitHub]
    Repo[Repository]
    Actions[GitHub Actions]
  end
  subgraph cloud [Dịch vụ cloud miễn phí / freemium]
    SemgrepCloud[Semgrep hoặc upload SARIF]
    SonarCloud[SonarQube Cloud]
  end
  Code --> Repo
  Repo -->|push| Actions
  Actions -->|SAST| SemgrepCloud
  Actions -->|tùy chọn| SonarCloud
  Docker -->|test thủ công / DAST local| ManualTest[Kịch bản bảo mật]
```

- **Không cần VPS**: runner do GitHub host; Semgrep chạy trong job; Sonar dùng [SonarQube Cloud](https://www.sonarsource.com/products/sonarcloud/) (free cho public repo, private theo policy Sonar).
- **Juice Shop**: mục đích chính là **bài lab / minh chứng tương tác** — thường chạy **Docker trên máy** (`docker run -p 3000:3000 bkimminich/juice-shop`). Nếu cần **ảnh pipeline có bước “test với target”**, có thể thêm job dùng **service container** Juice Shop trong Actions và chạy script/ZAP nhẹ (tùy thời gian làm bài).

## Cấu trúc file trong repo

| Thành phần | Vai trò |
|------------|--------|
| [`.github/workflows/security-ci.yml`](../.github/workflows/security-ci.yml) | Push/PR: cài Semgrep CLI hoặc `returntocorp/semgrep-action`, quét thư mục mã nguồn, xuất log + (tùy chọn) SARIF lên GitHub Code Scanning |
| [`.github/workflows/sonarcloud.yml`](../.github/workflows/sonarcloud.yml) | SonarCloud scan: secret `SONAR_TOKEN`; `sonar.organization` / `sonar.projectKey` trong `sonar-project.properties` |
| [`sonar-project.properties`](../sonar-project.properties) (gốc repo) | Cấu hình nguồn, exclusions — bắt buộc cho SonarCloud CLI |
| [`.github/workflows/juice-shop-smoke.yml`](../.github/workflows/juice-shop-smoke.yml) | Juice Shop dạng service container + chờ port + gọi API public (minh chứng CI) |
| Tài liệu trong báo cáo | Ảnh chụp tab Actions, đoạn log, số finding, thời gian job; hướng dẫn chạy Juice Shop local (`docker run --rm -p 3000:3000 bkimminich/juice-shop`) |

## Nội dung kỹ thuật cốt lõi

**1. Semgrep (SAST trên code của bạn)**

- Cách phổ biến: `pip install semgrep` rồi `semgrep scan --config auto --error` hoặc rules OWASP/custom.
- Để có **bảng lỗi trên GitHub**: bật Code Scanning với upload SARIF (`actions/upload-sarif`) — repo public thường đủ; private cần GitHub Advanced Security (tùy gói). Nếu không có SARIF, vẫn **đủ điểm** với log job và exit code.

**2. SonarQube Cloud**

- Tạo project trên SonarCloud, lấy token, thêm secrets vào repo.
- Workflow dùng official action `SonarSource/sonarqube-scan-action` (hoặc scanner cũ tùy doc hiện tại) + `sonar-project.properties`.

**3. Juice Shop**

- **Đủ cho báo cáo**: mô tả + lệnh Docker + ảnh màn hình challenge — không bắt buộc nằm trong Actions.
- **Gộp vào CI (tùy chọn)**: job có `services.juice-shop.image: bkimminich/juice-shop`, chờ healthcheck/port, chạy vài request hoặc ZAP baseline — job sẽ lâu hơn và cần cấu hình rõ (timeout, bộ rule ZAP).

## Thứ tự triển khai gợi ý

1. Khởi tạo repo, đẩy mã (hoặc project mẫu nhỏ).
2. Thêm workflow Semgrep trước (nhanh, ít secret).
3. Kết nối SonarCloud nếu giáo trình yêu cầu “phân tích chất lượng/SAST tích hợp nền tảng”.
4. Juice Shop: Docker local + phần minh chứng trong báo cáo; chỉ thêm vào Actions nếu cần screenshot pipeline có bước dynamic test.

## Lưu ý tránh hiểu nhầm khi viết báo cáo

- **Semgrep/Sonar trên repo của bạn** = phân tích **mã bạn commit**, không tự động thay cho pentest toàn bộ Juice Shop trừ khi bạn clone Juice Shop vào repo hoặc chạy DAST riêng.
- **Private repo**: phút Actions theo quota GitHub; SonarCloud/Semgrep theo điều khoản từng dịch vụ.

## Phạm vi triển khai (đã làm trong repo)

- Thư mục `.github/workflows/` với workflow Semgrep, SonarCloud, và job Juice Shop (service + healthcheck/smoke).
- File `sonar-project.properties` mẫu (placeholder keys — cần thay bằng org/project SonarCloud thật và thêm `SONAR_TOKEN`).
