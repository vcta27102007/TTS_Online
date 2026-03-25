# Demo script tuần 10

## Mục tiêu buổi demo
Chứng minh hệ thống đã chạy được đầy đủ các phần chính:
- Xác thực bằng JWT
- Phân quyền MANAGER / USER
- Quản lý project
- Quản lý task
- Rule nghiệp vụ task

---

## Chuẩn bị trước demo
- Mở SQL Server
- Chạy project bằng:
```bash
mvn spring-boot:run
```
hoặc:
```bash
java -jar target/task-management.jar
```
- Mở Swagger: `http://localhost:8080/swagger-ui.html`
- Chuẩn bị sẵn 2 tài khoản:
  - 1 MANAGER
  - 1 USER

---

## Kịch bản demo đề xuất

### Bước 1 - Register MANAGER
**API:** `POST /api/auth/register`

Body mẫu:
```json
{
  "ten": "Manager Demo",
  "email": "manager@gmail.com",
  "password": "123456",
  "role": "MANAGER"
}
```

Kỳ vọng:
- API trả về 201
- Nhận được token JWT

---

### Bước 2 - Login MANAGER
**API:** `POST /api/auth/login`

Body mẫu:
```json
{
  "email": "manager@gmail.com",
  "password": "123456"
}
```

Kỳ vọng:
- API trả về token
- Dùng token này để authorize ở Swagger/Postman

---

### Bước 3 - Tạo project
**API:** `POST /api/projects`

Body mẫu:
```json
{
  "tenProject": "Task Management Internship",
  "moTa": "Project demo cho buoi bao ve"
}
```

Kỳ vọng:
- MANAGER tạo project thành công

---

### Bước 4 - Register USER
**API:** `POST /api/auth/register`

Body mẫu:
```json
{
  "ten": "User Demo",
  "email": "user@gmail.com",
  "password": "123456",
  "role": "USER"
}
```

Kỳ vọng:
- Tạo được tài khoản user thường

---

### Bước 5 - Tạo task
**API:** `POST /api/tasks`

Body mẫu:
```json
{
  "tieuDe": "Hoan thien slide tuan 10",
  "moTa": "Chuan bi bao cao demo",
  "deadline": "2026-03-30T18:00:00",
  "userId": 2,
  "projectId": 1
}
```

Kỳ vọng:
- Task được tạo với trạng thái mặc định `CHUA_LAM`

---

### Bước 6 - Assign task
**API:** `PUT /api/tasks/{taskId}/assign/{userId}`

Ví dụ:
```text
PUT /api/tasks/1/assign/2
```

Kỳ vọng:
- Task được gán cho user

---

### Bước 7 - USER xem task của mình
**API:** `GET /api/tasks/my`

Kỳ vọng:
- USER chỉ xem được task của chính mình
- Đây là điểm demo phân quyền quan trọng

---

### Bước 8 - Update status task
**API:** `PUT /api/tasks/{taskId}/status?status=DANG_LAM`

Sau đó:
```text
PUT /api/tasks/1/status?status=HOAN_THANH
```

Kỳ vọng:
- Phải chuyển `CHUA_LAM -> DANG_LAM -> HOAN_THANH`
- Nếu gọi thẳng từ `CHUA_LAM -> HOAN_THANH` thì báo lỗi

---

### Bước 9 - Demo lỗi nghiệp vụ
Có thể demo nhanh 2 lỗi:
- deadline ở quá khứ
- task đã hoàn thành thì không assign/update nữa

Mục đích:
- Cho thấy hệ thống không chỉ CRUD mà còn có business rule

---

## Câu chốt khi demo
"Project của em đã hoàn thiện được luồng xác thực JWT, phân quyền theo vai trò, quản lý project/task và có validate + exception handler để đảm bảo API trả lỗi rõ ràng."
