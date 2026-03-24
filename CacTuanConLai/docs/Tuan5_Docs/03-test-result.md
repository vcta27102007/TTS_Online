# API TASK - TUẦN 5

## 1. Tạo Task

POST /api/tasks

Body:
{
"tieuDe": "Làm API login",
"moTa": "Spring Boot",
"deadline": "2026-03-30T00:00:00",
"userId": 1,
"projectId": 1
}

---

## 2. Assign Task

PUT /api/tasks/{taskId}/assign/{userId}

---

## 3. Update trạng thái

PUT /api/tasks/{taskId}/status?status=DANG_LAM

---

## 4. Lấy task theo user

GET /api/tasks/user/{userId}

---

## 5. Lấy task theo project

GET /api/tasks/project/{projectId}