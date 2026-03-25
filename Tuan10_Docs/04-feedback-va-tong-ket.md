# Feedback và tổng kết tuần 10

## 1. Những gì đã hoàn thành sau 10 tuần
- Đi từ phân tích nghiệp vụ ban đầu đến xây dựng API hoàn chỉnh
- Thiết kế entity và database
- Xây dựng CRUD cho user, project, task
- Thêm validation và xử lý exception
- Thêm xác thực JWT và phân quyền role
- Viết unit test cho `TaskService`
- Tích hợp Swagger và chuẩn bị tài liệu demo cuối kỳ

---

## 2. Những điều học được
### Về kỹ thuật
- Hiểu cách tổ chức project Spring Boot theo module
- Biết cách viết REST API chuẩn hơn
- Hiểu cách dùng JPA để map entity
- Biết cách áp dụng JWT trong xác thực
- Biết xử lý lỗi thống nhất bằng `@RestControllerAdvice`
- Biết dùng Mockito để test logic service

### Về tư duy làm việc
- Chia nhỏ công việc theo tuần giúp dễ theo dõi tiến độ
- Nên ưu tiên xong chức năng chính trước rồi mới tối ưu
- Viết tài liệu song song với code sẽ dễ demo hơn rất nhiều

---

## 3. Feedback giả định sau buổi demo
- Cấu trúc project rõ ràng, dễ hiểu
- Flow auth và phân quyền trình bày ổn
- Demo nên đi đúng một luồng ngắn, tránh quá nhiều endpoint
- Cần nhấn mạnh hơn phần business rule để thể hiện tư duy backend
- Nếu có thời gian nên bổ sung deploy thực tế

---

## 4. Hướng cải thiện tiếp theo
- Tách lớp response/request DTO đầy đủ hơn
- Thêm pagination cho list API
- Tách role thành enum hoặc bảng riêng
- Bổ sung refresh token + logout
- Thêm test cho AuthService và Controller
- Deploy project lên Render / Railway / VPS

---

## 5. Kết luận
Sau 10 tuần, project đã đạt được mục tiêu chính của một bài backend internship: có CRUD, có bảo mật, có business rule, có test cơ bản và có tài liệu trình bày. Đây là nền tảng tốt để tiếp tục học sâu hơn về kiến trúc backend và triển khai thực tế.
