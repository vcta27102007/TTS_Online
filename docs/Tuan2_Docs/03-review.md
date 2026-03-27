# Tuần 2 - Review quan hệ và tối ưu query

## 1. Vì sao khóa ngoại nằm ở bảng `tasks`
### `project_id`
Một task luôn thuộc một project cụ thể, còn một project có thể có nhiều task. Vì vậy khóa ngoại phải nằm ở bảng nhiều là `tasks`.

### `user_duoc_giao`
Một user có thể nhận nhiều task, nhưng một task chỉ gán cho tối đa một user tại một thời điểm. Vì vậy khóa ngoại cũng nằm ở bảng `tasks`.

## 2. Vì sao `user_duoc_giao` cho phép null
Trong nghiệp vụ thật, task có thể được tạo trước rồi mới phân công sau. Nếu bắt buộc có user ngay khi tạo task thì sẽ không phù hợp với luồng nghiệp vụ đó.

## 3. Constraint đã dùng
### Check role
Chỉ cho phép:
- `USER`
- `MANAGER`

### Check trạng thái task
Chỉ cho phép:
- `CHUA_LAM`
- `DANG_LAM`
- `HOAN_THANH`

### Check tiêu đề task
Không cho phép chuỗi rỗng hoặc chỉ có khoảng trắng.

### Check deadline
`deadline` phải lớn hơn `ngay_tao` để tránh dữ liệu sai logic.

## 4. Index đã thêm
Để phục vụ đúng các query yêu cầu trong đề, script SQL đã thêm các index sau:
- `IX_tasks_project_id`: tăng tốc query theo project
- `IX_tasks_user_duoc_giao`: tăng tốc query theo user
- `IX_tasks_trang_thai`: tăng tốc query theo trạng thái
- `IX_tasks_project_status`: hỗ trợ lọc theo project + trạng thái
- `IX_users_email`: tăng tốc tra cứu user theo email

## 5. Review query
### Query theo user
Dùng join giữa `tasks`, `users`, `projects` để lấy được thông tin task và project của user được giao.

### Query theo project
Dùng `WHERE t.project_id = ?` để lọc task trong một project cụ thể. Có thể kết hợp `LEFT JOIN users` để thấy task nào chưa assign.

### Query theo status
Dùng `WHERE trang_thai = ?` để lọc nhanh theo enum trạng thái.

### Query thống kê
Dùng `GROUP BY` để đếm:
- số task theo project
- số task theo trạng thái
- số task theo user

## 6. Kết luận
Thiết kế database tuần 2 đã bám sát mô hình OOP tuần 1, đủ để:
- tạo bảng thật trên SQL Server
- insert dữ liệu test
- query theo nghiệp vụ
- làm nền cho tuần 3 chuyển sang Spring Boot + JPA
