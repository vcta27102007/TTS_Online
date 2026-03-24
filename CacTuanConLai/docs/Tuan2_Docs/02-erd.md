# Tuần 2 - ERD chi tiết

## 1. Danh sách bảng
- `users`
- `projects`
- `tasks`

## 2. ERD dạng text
```text
+------------------+
|      users       |
+------------------+
| id (PK)          |
| ten              |
| email (UQ)       |
| role             |
| dang_hoat_dong   |
| created_at       |
+------------------+

          1
          |
          | n
+------------------+
|      tasks       |
+------------------+
| id (PK)          |
| project_id (FK)  |
| tieu_de          |
| mo_ta            |
| trang_thai       |
| user_duoc_giao   |
| deadline         |
| ngay_tao         |
| ngay_cap_nhat    |
+------------------+
          |
          | n
          |
          1

+------------------+
|     projects     |
+------------------+
| id (PK)          |
| ten_project      |
| mo_ta            |
| created_at       |
+------------------+
```

## 3. Quan hệ giữa các bảng
### Project - Task
- Một `project` có nhiều `task`.
- Một `task` chỉ thuộc đúng một `project`.
- Kiểu quan hệ: **1 - n**.
- Khóa ngoại: `tasks.project_id -> projects.id`

### User - Task
- Một `user` có thể được giao nhiều `task`.
- Một `task` tại một thời điểm chỉ gán cho tối đa một `user`.
- Kiểu quan hệ: **1 - n**.
- Khóa ngoại: `tasks.user_duoc_giao -> users.id`

## 4. Ghi chú thiết kế
- `user_duoc_giao` được phép `NULL` để hỗ trợ trường hợp task mới tạo nhưng chưa assign cho ai.
- `deadline` được lưu riêng trong `tasks` vì đây là thông tin gắn trực tiếp với công việc.
- `created_at`, `ngay_tao`, `ngay_cap_nhat` hỗ trợ tracking dữ liệu và mở rộng cho audit ở các tuần sau.

## 5. ERD final
ERD final của tuần 2 chốt lại gồm 3 bảng và 2 quan hệ chính:
- `projects (1) -> (n) tasks`
- `users (1) -> (n) tasks`
