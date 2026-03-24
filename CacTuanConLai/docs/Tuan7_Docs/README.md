# Tuần 7 - Authentication & Authorization

## Mô tả

* Xây dựng chức năng đăng ký, đăng nhập
* Mã hóa mật khẩu bằng BCrypt
* Sử dụng JWT để xác thực
* Phân quyền USER / MANAGER

---

## Business rule

* Email phải unique
* Password không được để trống
* Role chỉ có: USER, MANAGER
* USER chỉ xem task của mình
* MANAGER được tạo project và quản lý task

---

## API

### Register

POST /api/auth/register

### Login

POST /api/auth/login

### Lấy task của tôi

GET /api/tasks/my

### Tạo project (MANAGER)

POST /api/projects

---

## Response chuẩn

```json
{
  "code": 200,
  "message": "Đăng nhập thành công",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "email": "user1@gmail.com",
    "role": "USER",
    "ten": "User 1"
  }
}
```

---

## Exception mapping

* BadRequestException -> HTTP 400
* NotFoundException -> HTTP 404
* MethodArgumentNotValidException -> HTTP 400
* HttpMessageNotReadableException -> HTTP 400
* Exception -> HTTP 500

---

## Test Postman

* Register USER hợp lệ -> 201 Created
* Register email trùng -> 400 Bad Request
* Login đúng -> 200 OK
* Login sai mật khẩu -> 400 Bad Request
* Không có token -> 401/403
* USER gọi /api/tasks/my -> 200 OK
* USER tạo project -> 403 Forbidden
* MANAGER tạo project -> 201 Created

---

## Ghi chú

* Password được mã hóa bằng BCrypt
* Token phải gửi ở header:
  Authorization: Bearer <token>
* Bảng users phải có cột password

---

## Kết luận

* Hoàn thành chức năng đăng nhập, đăng ký
* Áp dụng JWT cho xác thực
* Phân quyền USER / MANAGER hoạt động đúng
