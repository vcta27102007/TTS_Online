# Tuần 8 - Unit Test TaskService

## Mục tiêu
Viết unit test cho TaskService để kiểm tra logic nghiệp vụ bằng JUnit 5 và Mockito.

---

## Nội dung đã làm

### 1. Test tạo task (create)
- Tạo task hợp lệ → 201 OK
- User không tồn tại → 404 Not Found
- Project không tồn tại → 404 Not Found
- Deadline quá khứ → 400 Bad Request

---

### 2. Test assign task
- Gán task thành công
- Task không tồn tại → 404
- User không tồn tại → 404
- Task đã hoàn thành → không được assign

---

### 3. Test update status
- CHUA_LAM → DANG_LAM → OK
- Status null → 400
- CHUA_LAM → HOAN_THANH → lỗi (sai rule)
- Task đã hoàn thành → không được update

---

### 4. Test get task
- Lấy task theo user → OK
- User không tồn tại → 404
- Lấy task theo project → OK
- Project không tồn tại → 404
- Lấy task theo email → OK
- Email không tồn tại → 404

---

## Công nghệ sử dụng

- JUnit 5
- Mockito
- @Mock, @InjectMocks
- when(...).thenReturn(...)
- verify(...)
- assertEquals(...)
- assertThrows(...)

---

## Kết quả

- Tổng số test: 18
- Pass: 18/18
- Không có lỗi

---

## Ý nghĩa

- Đảm bảo logic nghiệp vụ của TaskService hoạt động đúng
- Kiểm tra đầy đủ các trường hợp thành công và lỗi
- Giúp phát hiện bug sớm khi code thay đổi

---