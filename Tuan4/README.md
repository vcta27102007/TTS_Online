# TUẦN 4 - PROJECT & TASK (JPA RELATIONSHIP)

## Mục tiêu
- Xây dựng quan hệ giữa User - Project - Task
- Mapping entity bằng JPA
- Xây dựng API cho Task
- Test API bằng Postman

---

## Quan hệ dữ liệu

User 1 --- n Task  
Project 1 --- n Task

Task là bảng trung gian chứa:
- user_id
- project_id

---

## Chức năng đã làm

- Tạo project
- Tạo task
- Gán task cho user
- Lấy task theo user
- Lấy task theo project

---

## Công nghệ
- Spring Boot
- JPA / Hibernate
- SQL Server

---

## Cách chạy

1. Chạy DB (tuần 2)
2. Run project
3. Test bằng Postman

---

## Kết quả

- Mapping entity hoạt động đúng
- Quan hệ DB đúng
- API trả dữ liệu chính xác