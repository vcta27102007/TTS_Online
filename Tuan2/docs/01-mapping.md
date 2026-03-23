# Tuần 2 - Mapping entity sang database

## Mục tiêu
Chuẩn hóa mô hình OOP của tuần 1 thành mô hình cơ sở dữ liệu quan hệ để chuẩn bị cho các tuần tiếp theo dùng SQL và Spring Boot.

## 1. User -> bảng `users`
Entity `User` của tuần 1 được ánh xạ sang bảng `users`.

| Thuộc tính OOP | Cột DB | Kiểu dữ liệu | Ghi chú |
|---|---|---|---|
| id | id | BIGINT | Khóa chính, tự tăng |
| ten | ten | NVARCHAR(100) | Bắt buộc |
| email | email | VARCHAR(150) | Unique |
| role | role | VARCHAR(30) | USER / MANAGER |
| dangHoatDong | dang_hoat_dong | BIT | 1 = active, 0 = inactive |
| createdAt | created_at | DATETIME2 | Thời gian tạo |

## 2. Project -> bảng `projects`
Entity `Project` của tuần 1 được ánh xạ sang bảng `projects`.

| Thuộc tính OOP | Cột DB | Kiểu dữ liệu | Ghi chú |
|---|---|---|---|
| id | id | BIGINT | Khóa chính, tự tăng |
| tenProject | ten_project | NVARCHAR(150) | Bắt buộc |
| moTa | mo_ta | NVARCHAR(500) | Cho phép null |
| createdAt | created_at | DATETIME2 | Thời gian tạo |

## 3. Task -> bảng `tasks`
Entity `Task` của tuần 1 được ánh xạ sang bảng `tasks`.

| Thuộc tính OOP | Cột DB | Kiểu dữ liệu | Ghi chú |
|---|---|---|---|
| id | id | BIGINT | Khóa chính, tự tăng |
| projectId | project_id | BIGINT | FK sang `projects(id)` |
| tieuDe | tieu_de | NVARCHAR(200) | Không được rỗng |
| moTa | mo_ta | NVARCHAR(1000) | Cho phép null |
| trangThai | trang_thai | VARCHAR(30) | `CHUA_LAM`, `DANG_LAM`, `HOAN_THANH` |
| userDuocGiao | user_duoc_giao | BIGINT | FK sang `users(id)`, cho phép null |
| deadline | deadline | DATETIME2 | Bắt buộc |
| ngayTao | ngay_tao | DATETIME2 | Mặc định thời điểm tạo |
| ngayCapNhat | ngay_cap_nhat | DATETIME2 | Mặc định thời điểm cập nhật |

## 4. Lý do đặt tên cột
- Tên bảng dùng số nhiều: `users`, `projects`, `tasks`.
- Tên cột dùng `snake_case` để dễ đọc trong SQL.
- Tên enum trạng thái đồng bộ với source code tuần 1 để tránh lệch giữa tài liệu và chương trình.

## 5. Kết luận
Sau khi mapping, hệ thống gồm 3 bảng chính:
- `users`
- `projects`
- `tasks`

Đây là nền tảng để viết ERD, ràng buộc dữ liệu, insert dữ liệu test và query theo nghiệp vụ ở tuần 2.
