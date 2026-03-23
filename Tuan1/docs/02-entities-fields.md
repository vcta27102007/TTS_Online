# Entities & Fields (Week 01)

## User
- id: long
- ten: string (not blank)
- email: string (optional, basic format)
- role: Role (`USER`, `MANAGER`) - tuần 7 sẽ dùng auth
- dangHoatDong: boolean
- createdAt: LocalDateTime

## Project
- id: long
- tenProject: string (not blank)
- moTa: string (optional)
- dsTask: list<Task>
- members: list<User> (tuần 1 chưa quản lý membership riêng)

## Task
- id: long
- projectId: long
- tieuDe: string (not blank)
- moTa: string (optional)
- trangThai: TaskStatus (`CHUA_LAM`, `DANG_LAM`, `HOAN_THANH`)
- deadline: LocalDateTime (optional)
- userDuocGiao: Long (nullable)
- ngayTao: LocalDateTime
- ngayCapNhat: LocalDateTime
