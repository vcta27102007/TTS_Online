# Domain Notes (Week 01)

## Mục tiêu bài toán
Quản lý Project - Task - User (console OOP). Tập trung nghiệp vụ + validate + xử lý lỗi logic.

## Khái niệm
- User: người dùng hệ thống.
- Project: dự án.
- Task: công việc thuộc 1 Project, có trạng thái và có thể assign cho 1 User.

## Luồng nghiệp vụ tối thiểu (week 1)
1) Tạo Project
2) Tạo User
3) Tạo Task trong Project
4) Update Task (title, deadline, status)
5) Delete Task
6) Assign Task cho User
7) List Task theo project/user/status (console)

## Quy tắc logic (bản tuần 1, chưa ép business phức tạp)
- Task phải thuộc 1 Project.
- Task title không được rỗng.
- Deadline nếu có thì phải > hiện tại.
- Mỗi Task có id duy nhất.
- Không được add Task trùng id.
- Update/Delete phải tồn tại mới làm.
- Assign: task phải tồn tại, user phải tồn tại.
