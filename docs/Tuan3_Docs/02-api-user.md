# API USER

## 1. Lấy tất cả user

GET /api/users

Response:

{
"code": 200,
"message": "Success",
"data": [...]
}


---

## 2. Lấy user theo id

GET /api/users/{id}

---

## 3. Tạo user

POST /api/users

Body:

{
"ten": "Minh",
"email": "minh@gmail.com
",
"role": "USER",
"dangHoatDong": true
}


---

## 4. Update user

PUT /api/users/{id}

---

## 5. Xóa user

DELETE /api/users/{id}