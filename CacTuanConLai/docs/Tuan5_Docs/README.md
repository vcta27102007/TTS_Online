# TUẦN 5 - BUSINESS LOGIC TASK

## Mục tiêu
- Áp dụng business logic vào Task
- Quản lý trạng thái Task
- Thực hiện assign Task cho User
- Validate dữ liệu theo rule nghiệp vụ

---

## Nội dung thực hiện

### 1. Xây dựng enum trạng thái Task
- CHUA_LAM
- DANG_LAM
- HOAN_THANH

---

### 2. Xây dựng logic nghiệp vụ

#### a. Tạo Task
- Kiểm tra user tồn tại
- Kiểm tra project tồn tại
- Gán trạng thái mặc định: CHUA_LAM

#### b. Assign Task
- Cho phép gán lại user cho task
- Không cho assign nếu task đã HOAN_THANH

#### c. Update trạng thái Task
- CHUA_LAM → DANG_LAM → HOAN_THANH
- Không được nhảy trực tiếp CHUA_LAM → HOAN_THANH
- Không cho update nếu đã HOAN_THANH

---

## Công nghệ sử dụng
- Spring Boot
- JPA / Hibernate
- SQL Server

---

## Kết quả đạt được
- API hoạt động đúng business logic
- Dữ liệu đảm bảo tính hợp lệ
- Hệ thống chặn được các trường hợp sai