# Entities & Fields (Week 01)

## User
- id: long
- name: string (not blank)
- email: string (optional, basic format)
- role: Role (USER, MANAGER) - tuần 7 sẽ dùng auth
- active: boolean

## Project
- id: long
- name: string (not blank)
- description: string (optional)
- tasks: list<Task>
- members: list<User> (week 1 có thể chưa ép)

## Task
- id: long
- projectId: long
- title: string (not blank)
- description: string (optional)
- status: TaskStatus (TODO, IN_PROGRESS, DONE)
- deadline: LocalDateTime (optional)
- assigneeUserId: Long (nullable)
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
