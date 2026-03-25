# TEST RESULT

## 1. Test tạo project
- Thành công
- Dữ liệu lưu DB

## 2. Test tạo task
- Thành công
- Gắn đúng user + project

## 3. Test lấy task theo user
- Trả về đúng danh sách

## 4. Test lấy task theo project
- Trả về đúng dữ liệu

---

## Test lỗi

### User không tồn tại
→ 404

### Project không tồn tại
→ 404

---

## Kết luận

- Quan hệ entity hoạt động đúng
- API chạy ổn định
- Không lỗi JSON loop