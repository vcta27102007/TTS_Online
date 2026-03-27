# Bộ câu hỏi Q&A khi bảo vệ

## 1. JPA là gì?
JPA là chuẩn giúp map object Java với bảng trong database. Trong project, em dùng Spring Data JPA để giảm code SQL thủ công và thao tác dữ liệu nhanh hơn.

## 2. Vì sao dùng Repository?
Repository giúp tách phần truy cập dữ liệu ra khỏi service, code rõ ràng hơn và dễ test hơn.

## 3. Entity khác DTO ở đâu?
- **Entity**: map trực tiếp với bảng DB
- **DTO**: dữ liệu nhận vào/trả ra cho API
Tách DTO giúp tránh lộ dữ liệu nhạy cảm và giúp request/response rõ ràng.

## 4. JWT là gì?
JWT là token dùng để xác thực người dùng sau khi login. Sau khi đăng nhập thành công, server cấp token; client gửi lại token ở các request sau qua header Authorization.

## 5. Flow JWT trong project này như thế nào?
1. User login thành công
2. Server tạo JWT chứa email + role + userId
3. Client gửi `Bearer token`
4. `JwtAuthFilter` đọc token
5. Nếu hợp lệ thì gắn user vào `SecurityContext`
6. Spring Security kiểm tra quyền truy cập endpoint

## 6. Vì sao dùng BCrypt?
BCrypt để mã hóa password trước khi lưu DB, tránh lưu mật khẩu dạng plain text.

## 7. Phân quyền trong project đang làm thế nào?
Trong `SecurityConfig`, em dùng `.requestMatchers()` để quy định endpoint nào cho `MANAGER`, endpoint nào cho `USER`, endpoint nào public.

## 8. Business rule nổi bật của Task là gì?
- Deadline phải lớn hơn thời điểm hiện tại
- Task hoàn thành thì không assign lại
- Task hoàn thành thì không update nữa
- Không cho chuyển trực tiếp từ `CHUA_LAM` sang `HOAN_THANH`

## 9. GlobalExceptionHandler có tác dụng gì?
Giúp gom toàn bộ lỗi về một nơi, trả ra response thống nhất thay vì để lỗi văng stack trace lung tung.

## 10. Swagger có vai trò gì?
Swagger dùng để tài liệu hóa API và test API nhanh ngay trên trình duyệt.

## 11. Nếu mở rộng project tiếp thì sẽ làm gì?
- Tách DTO rõ hơn cho Project/Task
- Dùng MapStruct hoặc model mapper
- Thêm refresh token
- Thêm audit log
- Viết integration test
- Deploy thật lên server/cloud

## 12. Điểm mạnh hiện tại của project là gì?
- Có kiến trúc rõ ràng
- Có auth + phân quyền
- Có validate + handler lỗi
- Có unit test cho service
- Có Swagger để demo

## 13. Hạn chế hiện tại là gì?
- Chưa tách toàn bộ response DTO khỏi entity
- Chưa có refresh token
- Chưa có CI/CD
- Chưa deploy production thật
