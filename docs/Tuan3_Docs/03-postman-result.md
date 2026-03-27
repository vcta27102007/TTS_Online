# POSTMAN TEST

## 1. Test GET /api/users
- Trả về danh sách user
- Status: 200 OK

## 2. Test POST /api/users
- Tạo user thành công
- Không bị trùng email

## 3. Test PUT /api/users/{id}
- Update thành công
- Dữ liệu thay đổi đúng

## 4. Test DELETE /api/users/{id}
- Xóa thành công
- Không còn trong DB

## 5. Test lỗi

### Email trùng
→ trả về 400

### User không tồn tại
→ trả về 404

### Dữ liệu sai
→ trả về 400

---

## Kết luận
- API hoạt động đúng
- Validate hoạt động
- Exception xử lý tốt