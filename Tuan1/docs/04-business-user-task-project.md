# Business Description: User – Task – Project (Week 01)

## Quan hệ
- 1 Project có nhiều Task.
- 1 Task thuộc đúng 1 Project.
- 1 Task có thể được assign cho 0 hoặc 1 User.
- 1 User có thể nhận nhiều Task.

## Use cases
### UC1: Add Task
Input: projectId, title, deadline(optional)
Rule: title not blank, project tồn tại, deadline > now (nếu có)
Output: Task mới với status TODO.

### UC2: Update Task
Input: taskId + các field thay đổi
Rule: task tồn tại, title not blank (nếu update), deadline > now (nếu update)
Output: Task updatedAt đổi.

### UC3: Delete Task
Input: taskId
Rule: tồn tại mới xóa

### UC4: Assign Task
Input: taskId, userId
Rule: task tồn tại, user tồn tại
Output: task.assigneeUserId = userId
