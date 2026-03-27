# Business Description: User – Task – Project (Week 01)

## Quan hệ
- 1 Project có nhiều Task.
- 1 Task thuộc đúng 1 Project.
- 1 Task có thể được assign cho 0 hoặc 1 User.
- 1 User có thể nhận nhiều Task.

## Use cases
### UC1: Add Task
Input: projectId, tieuDe, deadline(optional)
Rule: tieuDe không được rỗng, project tồn tại, deadline > now (nếu có)
Output: Task mới với trạng thái `CHUA_LAM`.

### UC2: Update Task
Input: taskId + các field thay đổi
Rule: task tồn tại, tieuDe không được rỗng (nếu update), deadline > now (nếu update)
Output: `ngayCapNhat` của task thay đổi.

### UC3: Delete Task
Input: taskId
Rule: task tồn tại mới được xóa.

### UC4: Assign Task
Input: taskId, userId
Rule: task tồn tại, user tồn tại
Output: `task.userDuocGiao = userId`

### UC5: List Task
Hệ thống hỗ trợ list task theo:
- project
- user
- trạng thái
