# Tuần 10 - Tổng kết, demo và bảo vệ project

## Mục tiêu
Hoàn thiện phần trình bày cuối kỳ cho project **Task Management API** gồm: slide kiến trúc, sơ đồ flow JWT, demo script, bộ câu hỏi Q&A và phần tổng kết những gì đã học được.

---

## 1. Kiến trúc hệ thống
Project được xây dựng theo mô hình nhiều tầng:
- **Controller**: nhận request/response REST API
- **Service**: xử lý nghiệp vụ
- **Repository**: truy cập dữ liệu bằng Spring Data JPA
- **Entity/DTO**: mô hình dữ liệu và dữ liệu trao đổi
- **Security**: JWT Filter + SecurityConfig để xác thực và phân quyền
- **Exception Handler**: chuẩn hóa lỗi trả về API

Các module chính:
- Auth
- User
- Project
- Task
- Common (response, exception)

---

## 2. Nội dung demo
### Flow demo chính
1. Đăng ký tài khoản `MANAGER`
2. Đăng nhập và lấy JWT token
3. Tạo project mới
4. Đăng ký thêm tài khoản `USER`
5. Tạo task trong project
6. Assign task cho user
7. User dùng token riêng gọi API `/api/tasks/my`
8. Manager cập nhật trạng thái task
9. Giải thích rule phân quyền

---

## 3. Phân quyền đã triển khai
- `MANAGER` có quyền tạo/sửa project
- `MANAGER` có quyền tạo task, assign task, xem task theo user/project
- `USER` chỉ xem được task của chính mình qua `/api/tasks/my`
- Các API auth và Swagger được mở public

---

## 4. Bộ tài liệu tuần 10
Trong thư mục này có:
- `01-kien-truc-he-thong.md`: mô tả kiến trúc tổng thể
- `02-demo-script.md`: kịch bản demo từng bước
- `03-qa-jpa-jwt.md`: bộ Q&A khi bảo vệ
- `04-feedback-va-tong-ket.md`: feedback, bài học rút ra và định hướng cải thiện
- `Presentation_Tuan10.pptx`: slide thuyết trình tuần 10

---

## 5. Kết quả
- Hoàn thiện bộ tài liệu trình bày cuối
- Có flow demo rõ ràng, bám đúng chức năng đã code
- Có sơ đồ JWT để giải thích lúc bảo vệ
- Có sẵn câu trả lời cho phần hỏi đáp kỹ thuật

---

## 6. Ghi chú
- Phần **"Khóa repo"** là thao tác thủ công trên Git hosting nên chưa thể thực hiện trong bộ tài liệu này.
- Project hiện chạy local theo cấu hình SQL Server trong `application-dev.properties`.
- Trong môi trường hiện tại chưa thể chạy build Maven online do thiếu quyền tải dependency, nên tài liệu demo được xây dựng dựa trên code source hiện có.
