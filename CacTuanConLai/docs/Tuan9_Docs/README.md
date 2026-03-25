# Tuần 9 - Build & Deploy + Swagger

Mục tiêu
Build project thành file `.jar`, cấu hình môi trường dev/prod, tích hợp Swagger để test API và hoàn thiện bảo mật JWT.

Nội dung đã làm

1. Build project
- Build thành công file `.jar` bằng Maven
- Chạy ứng dụng bằng lệnh:
```bash
mvn spring-boot:run
java -jar target/demo.jar
```

2. Cấu hình môi trường
- Cấu hình application cho môi trường dev
- Chuẩn bị cấu hình cho môi trường prod
- Đảm bảo project chạy ổn định local

3. Tích hợp Swagger
- Tích hợp Swagger (OpenAPI) để test API
- Truy cập tại:
```
http://localhost:8080/swagger-ui.html
```
- Hiển thị đầy đủ danh sách API

4. Cấu hình Security với JWT
- Sử dụng JWT để xác thực người dùng
- Thêm filter JwtAuthFilter
- Phân quyền theo role (MANAGER, USER)
- Mở quyền cho Swagger:
```java
.requestMatchers("/api/auth/**").permitAll()
.requestMatchers("/swagger-ui.html").permitAll()
.requestMatchers("/swagger-ui/**").permitAll()
.requestMatchers("/v3/api-docs/**").permitAll()
```

5. Test API
- Test API bằng Postman → hoạt động bình thường
- Test Swagger → gọi API trực tiếp OK
- Các API chính:
   - Auth: /api/auth/login
   - Product: /api/products
   - Category: /api/categories
   - Order: /api/orders

Công nghệ sử dụng
- Spring Boot
- Spring Security + JWT
- SQL Server
- Maven
- Swagger (springdoc-openapi)

Kết quả
- Build `.jar` thành công
- Ứng dụng chạy ổn định local
- Swagger hoạt động bình thường
- API test OK bằng Postman và Swagger

Ý nghĩa
- Đóng gói ứng dụng để deploy
- Cung cấp tài liệu API trực quan
- Đảm bảo hệ thống có bảo mật JWT
- Sẵn sàng cho bước deploy tuần tiếp theo