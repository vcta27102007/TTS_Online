# API TASK

## 1. Tạo project

POST /api/projects

Body:
{
"tenProject": "Website bán hàng",
"moTa": "Backend API"
}

---

## 2. Tạo task

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

## 3. Lấy task theo user

GET /api/tasks/user/{userId}

---

## 4. Lấy task theo project

GET /api/tasks/project/{projectId}