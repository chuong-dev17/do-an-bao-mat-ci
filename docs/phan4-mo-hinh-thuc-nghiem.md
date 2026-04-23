# PHẦN 4: MÔ HÌNH THỰC NGHIỆM CHÍNH CỦA ĐỀ TÀI

## 4.1. TỔNG QUAN MÔ HÌNH

**Tên đề tài**: Xây dựng hệ thống CI/CD bảo mật (DevSecOps) cho ứng dụng quản lý bán hàng ô tô (Car Dealership) sử dụng GitHub Actions và các công cụ phân tích bảo mật mã nguồn.

**Mục tiêu chính**:
- Tự động hóa quy trình kiểm thử bảo mật trong mỗi lần commit/push code
- Phát hiện lỗ hổng bảo mật sớm bằng các công cụ SAST (Semgrep, SonarCloud)
- Đảm bảo chất lượng code trước khi triển khai
- Chứng minh ứng dụng có thể chạy được trên môi trường CI/CD thực tế

---

## 4.2. KIẾN TRÚC HỆ THỐNG TỔNG QUAN

```
┌─────────────────────────────────────────────────────────────────────┐
│                         NGƯỜI PHÁT TRIỂN                            │
│                                                                      │
│  ┌────────────────────────────────────────────────────────────────┐│
│  │ Máy Dev (Local)                                                ││
│  │ ├─ Code + Commit                                               ││
│  │ ├─ Test Docker (Car_Dealership)                                ││
│  │ └─ Push lên GitHub                                             ││
│  └────────────────────────────────────────────────────────────────┘│
└──────────────────────────┬──────────────────────────────────────────┘
                           │ git push
                           ↓
┌─────────────────────────────────────────────────────────────────────┐
│                      GITHUB REPOSITORY                              │
│  ┌────────────────────────────────────────────────────────────────┐│
│  │ Source Code + Workflows                                        ││
│  │ ├─ .github/workflows/security-ci.yml                           ││
│  │ ├─ .github/workflows/sonarcloud.yml                            ││
│  │ ├─ .github/workflows/juice-shop-smoke.yml                      ││
│  │ └─ sonar-project.properties                                    ││
│  └────────────────────────────────────────────────────────────────┘│
└──────────────────────────┬──────────────────────────────────────────┘
                           │ Trigger Workflows
                           ↓
┌─────────────────────────────────────────────────────────────────────┐
│                    GITHUB ACTIONS (CI/CD)                           │
│                                                                      │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐ │
│  │  Security CI     │  │   SonarCloud     │  │  Docker Smoke    │ │
│  │  (Semgrep)       │  │   Scan           │  │  Test            │ │
│  │                  │  │                  │  │                  │ │
│  │ ✓ SAST Analysis  │  │ ✓ Code Quality   │  │ ✓ Build Image    │ │
│  │ ✓ Vuln Scan      │  │ ✓ Security Check │  │ ✓ Run Container  │ │
│  │ ✓ Upload SARIF   │  │ ✓ Quality Gate   │  │ ✓ Smoke Test     │ │
│  └────────┬─────────┘  └────────┬─────────┘  └────────┬─────────┘ │
│           │                     │                     │            │
│           ↓                     ↓                     ↓            │
│  ┌─────────────────────────────────────────────────────────────┐  │
│  │          Job Status & Logs                                 │  │
│  │  ✓ Success / ✗ Failure                                     │  │
│  │  • Duration: XX minutes                                    │  │
│  │  • Findings: XX issues                                     │  │
│  └─────────────────────────────────────────────────────────────┘  │
└──────────────────────────────┬──────────────────────────────────────┘
                               │
                ┌──────────────┼──────────────┐
                ↓              ↓              ↓
        ┌──────────────┐  ┌──────────────┐ ┌──────────────┐
        │  Semgrep     │  │ SonarCloud   │ │   GitHub     │
        │  Cloud       │  │              │ │  Code        │
        │              │  │              │ │  Scanning    │
        │ • Findings   │  │ • Issues     │ │ • SARIF      │
        │ • Report     │  │ • Dashboard  │ │ • Results    │
        └──────────────┘  └──────────────┘ └──────────────┘
```

---

## 4.3. LUỒNG DỮ LIỆU CHI TIẾT (DATA FLOW)

```
1. TRIGGER: Push Code to GitHub
   └─> Webhook → GitHub Actions Trigger

2. PARALLEL JOBS EXECUTION:
   
   ├─ JOB 1: Security CI (Semgrep)
   │  ├─ Setup: Checkout Code
   │  ├─ Install: Python + Semgrep CLI
   │  ├─ Scan: semgrep scan --config auto --error
   │  │  └─ Target: src/java/controller/, src/java/daos/, src/java/dtos/
   │  ├─ Detect: Security vulnerabilities, Code issues
   │  ├─ Output: JSON/SARIF report
   │  └─ Upload: GitHub Code Scanning (if enabled)
   │
   ├─ JOB 2: SonarCloud Quality Gate
   │  ├─ Setup: Checkout Code
   │  ├─ Install: Java + SonarScanner
   │  ├─ Scan: sonar-scanner (via sonar-project.properties)
   │  │  └─ Target: Car_Dealership source files
   │  ├─ Analyze: Code quality, Security hotspots
   │  ├─ Quality Gate: Pass/Fail check
   │  └─ Result: Dashboard on SonarCloud.io
   │
   └─ JOB 3: Docker Smoke Test
      ├─ Setup: Checkout Code
      ├─ Build: Docker image from Dockerfile
      │  └─ Base: Tomcat + Java
      ├─ Compile: Car_Dealership application
      ├─ Run: Container on port 8080
      ├─ Smoke Test: curl http://localhost:8080/
      ├─ Verify: HTTP 200 response
      └─ Logs: Build time, runtime, response status

3. AGGREGATE RESULTS:
   ├─ GitHub UI: Actions tab with job status
   ├─ Email Notification: To committer (if configured)
   ├─ Code Scanning: Security findings (if SARIF uploaded)
   └─ SonarCloud Dashboard: Quality metrics
```

---

## 4.4. CÁC THÀNH PHẦN CHÍNH

### A. **Mã Nguồn & Repository**
- **Ngôn ngữ**: Java (Backend), JSP (Frontend)
- **Framework**: Apache Tomcat
- **Cơ sở dữ liệu**: SQL (Script có sẵn)
- **Containerization**: Docker + Docker Compose

### B. **GitHub Actions Workflows**

| Workflow | Trigger | Mục Đích | Thời gian |
|----------|---------|---------|----------|
| `security-ci.yml` | Push/PR | SAST bằng Semgrep | 2-3 phút |
| `sonarcloud.yml` | Push/PR | Phân tích chất lượng | 3-5 phút |
| `juice-shop-smoke.yml` | Push | Build & run Docker | 5-10 phút |

### C. **Công Cụ Bảo Mật (Security Tools)**

1. **Semgrep** (SAST - Static Application Security Testing)
   - Quét mã nguồn Java tìm lỗ hổng bảo mật
   - Config: OWASP rules tự động
   - Output: JSON/SARIF report

2. **SonarCloud** (SAST + Code Quality)
   - Phân tích toàn diện chất lượng code
   - Kiểm tra: Security, Reliability, Maintainability
   - Dashboard: Metrics & trends theo thời gian

3. **GitHub Code Scanning**
   - Hiển thị findings trực tiếp trên UI
   - Yêu cầu: Upload SARIF hoặc Advanced Security

### D. **Docker & Deployment**

```
Dockerfile:
  ├─ Base Image: tomcat:9
  ├─ Add SQL script
  ├─ Copy WAR file
  ├─ Expose Port: 8080
  └─ Run: catalina.sh run

Docker Compose:
  ├─ Service: Car_Dealership
  ├─ Port mapping: 8080:8080
  └─ Build from Dockerfile
```

---

## 4.5. MÔ HÌNH TEST & VERIFICATION

### Bước 1: Local Testing (Máy Developer)
```bash
cd Car_Dealership
docker compose up --build
# Truy cập: http://localhost:8080
```

### Bước 2: Automated Testing (CI Pipeline)
```
┌────────────────────────────────────────┐
│ 1. Code Compile                        │
│    └─ Javac + Build system (ANT/Maven) │
├────────────────────────────────────────┤
│ 2. Security Scan (Parallel)            │
│    ├─ Semgrep: Lỗ hổng code           │
│    └─ SonarCloud: Chất lượng code      │
├────────────────────────────────────────┤
│ 3. Docker Build                        │
│    └─ Tạo image từ Dockerfile         │
├────────────────────────────────────────┤
│ 4. Smoke Test                          │
│    ├─ Run container                    │
│    ├─ HTTP Health check                │
│    └─ Log collection                   │
├────────────────────────────────────────┤
│ 5. Report Generation                   │
│    ├─ GitHub Actions UI                │
│    ├─ Semgrep findings                 │
│    ├─ SonarCloud dashboard             │
│    └─ Code Scanning alerts             │
└────────────────────────────────────────┘
```

---

## 4.6. KHI NÀO CI/CD PIPELINE CHẠY?

| Sự kiện | Workflow | Kết quả |
|--------|----------|--------|
| Push code lên branch | Tất cả 3 jobs | Kiểm tra đầy đủ |
| Tạo Pull Request | Tất cả 3 jobs | Bảo vệ chất lượng |
| Manual trigger | Có thể chạy lại | Re-run jobs |
| Schedule (tùy chọn) | Daily/Weekly | Quét định kỳ |

---

## 4.7. KỲ VỌNG OUTPUT & METRICS

### **Semgrep Findings**
- ✓ Số lỗ hổng tìm được: 0 - N
- ✓ Mức độ nghiêm trọng: High, Medium, Low
- ✓ Thời gian scan: 1-2 phút

### **SonarCloud Quality Gate**
- ✓ Security Hotspots: X
- ✓ Bugs: X
- ✓ Code Smells: X
- ✓ Quality Gate Status: PASS/FAIL

### **Docker Smoke Test**
- ✓ Build Status: Success/Fail
- ✓ Container Runtime: XX seconds
- ✓ HTTP Status Code: 200
- ✓ Response Time: XX ms

---

## 4.8. HIỂN THỊ KẾT QUẢ TRÊN GITHUB

### Actions Tab
```
✓ Workflow: Security CI
  └─ Job Semgrep scan: Success (2m 15s)
  
✓ Workflow: SonarCloud
  └─ Job SonarCloud scan: Success (4m 30s)
  
✓ Workflow: Smoke Test
  └─ Job Build & Test: Success (8m 45s)
```

### Code Scanning Alerts (nếu bật)
- Mỗi issue được highlight trực tiếp trên line code
- Có link tới OWASP/CWE reference

### SonarCloud Dashboard
- Real-time metrics
- Trend tracking
- Quality gate enforcement

---

## 4.9. LỢI ÍCH CỦA MÔ HÌNH

1. **Tự động hóa hoàn toàn**: Không cần chạy thủ công
2. **Phát hiện sớm**: Lỗ hổng được phát hiện trước khi merge
3. **Miễn phí**: Sử dụng dịch vụ cloud free tier
4. **Traceable**: Lịch sử tất cả builds & scans
5. **Scalable**: Dễ mở rộng thêm công cụ/job
6. **Compliance-ready**: Thích hợp cho yêu cầu bảo mật

---

## 4.10. MÔ HÌNH TRIỂN KHAI CÓ SẴN TRONG REPO

**File structure:**
```
repo/
├─ .github/workflows/
│  ├─ security-ci.yml          (Semgrep SAST)
│  ├─ sonarcloud.yml           (SonarCloud scan)
│  └─ juice-shop-smoke.yml     (Docker test)
├─ sonar-project.properties    (SonarCloud config)
├─ Car_Dealership/
│  ├─ Dockerfile              (Build image)
│  ├─ docker-compose.yml       (Local test)
│  └─ src/                     (Java source)
└─ docs/
   └─ plan-ci-cd-khong-can-server.md
```

---

## 4.11. CHI PHÍ & NGUỒN LỰC

| Thành phần | Chi phí | Ghi chú |
|-----------|--------|--------|
| GitHub Actions | Free | 2000 min/tháng cho public repo |
| Semgrep | Free | Community rules + cloud platform |
| SonarCloud | Free | Cho public repo; private có tiền |
| Docker | Free | Open source |
| VPS/Server | **Không** | Sử dụng GitHub's infrastructure |

**Kết luận**: Hoàn toàn không cần thuê server, toàn bộ chạy trên dịch vụ miễn phí.

---

## 4.12. KỲ VỌNG KẾT QUẢ SAU TRIỂN KHAI

✅ Mỗi commit được kiểm tra tự động  
✅ Lỗ hổng bảo mật được phát hiện trong 5-10 phút  
✅ Code quality được đo lường liên tục  
✅ Ứng dụng luôn có thể chạy được trên CI  
✅ Không cần VPS riêng, tiết kiệm chi phí  
✅ Tuân thủ best practices DevSecOps  

