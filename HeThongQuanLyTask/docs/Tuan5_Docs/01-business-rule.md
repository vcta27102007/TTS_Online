# BUSINESS RULE - TASK

## 1. Quy tắc tạo Task
- Task phải có user
- Task phải có project

---

## 2. Quy tắc assign
- Task chỉ assign cho user tồn tại
- Không assign nếu task đã HOAN_THANH

---

## 3. Quy tắc trạng thái

Flow hợp lệ:

CHUA_LAM → DANG_LAM → HOAN_THANH

---

## 4. Rule chặn

- Không update nếu task đã HOAN_THANH
- Không được bỏ qua trạng thái trung gian
- Không cho dữ liệu null

---

## 5. Ý nghĩa

- Đảm bảo dữ liệu nhất quán
- Tránh lỗi logic nghiệp vụ
- Mô phỏng hệ thống thực tế