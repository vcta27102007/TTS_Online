# ENTITY MAPPING

## 1. User - Task

- Quan hệ: 1 User có nhiều Task
- Mapping:
  @ManyToOne trong TaskEntity


TaskEntity:
@ManyToOne
@JoinColumn(name = "user_duoc_giao")
private UserEntity user;


---

## 2. Project - Task

- Quan hệ: 1 Project có nhiều Task


TaskEntity:
@ManyToOne
@JoinColumn(name = "project_id")
private ProjectEntity project;


---

## 3. Task - trung tâm

Task chứa:
- tiêu đề
- mô tả
- trạng thái
- deadline
- user
- project

---

## 4. FetchType

- Sử dụng LAZY để tránh load dư dữ liệu
- Tránh lỗi performance

---

## 5. Vấn đề JSON loop

User → Task → User gây lặp vô hạn
