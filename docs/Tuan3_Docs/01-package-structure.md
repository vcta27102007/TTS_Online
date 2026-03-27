# PACKAGE STRUCTURE

## 1. user.entity
Chứa class ánh xạ database (UserEntity)

## 2. user.repository
Interface JPA để truy vấn DB

## 3. user.service
Xử lý logic nghiệp vụ:
- validate dữ liệu
- kiểm tra email trùng
- mapping entity → response

## 4. user.controller
Expose API:
- GET
- POST
- PUT
- DELETE

## 5. common.exception
Xử lý lỗi:
- NotFoundException
- BadRequestException

## 6. common.response
Chuẩn hóa response:
- code
- message
- data