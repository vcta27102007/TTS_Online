# TUẦN 3 - SPRING BOOT CRUD USER

## Mục tiêu
- Khởi tạo Spring Boot project
- Kết nối database SQL Server
- Xây dựng CRUD cho User
- Xử lý exception
- Test API bằng Postman

---

## Công nghệ sử dụng
- Spring Boot
- Spring Data JPA
- SQL Server
- Maven

---

## Cấu trúc project
com.example.demo
├── common
│ ├── exception
│ └── response
├── user
│ ├── controller
│ ├── dto
│ ├── entity
│ ├── repository
│ └── service

---

## Chức năng đã làm
- Lấy danh sách user
- Lấy user theo id
- Tạo user
- Cập nhật user
- Xóa user

---

## Cách chạy project

1. Chạy SQL tuần 2 để tạo DB
2. Mở project bằng IntelliJ
3. Run `DemoApplication`
4. Test API bằng Postman

---

## Kết quả
- API hoạt động ổn định
- Kết nối DB thành công
- CRUD đầy đủ