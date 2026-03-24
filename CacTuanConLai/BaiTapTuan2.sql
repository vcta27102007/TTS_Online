-- =========================================================
-- TUAN 2 - TASK MANAGEMENT DATABASE
-- SQL Server
-- Full script: create database + tables + constraints
-- + indexes + sample data + queries
-- =========================================================

-- 1) TAO DATABASE
IF DB_ID('ThucTap_TaskManagement') IS NULL
BEGIN
    CREATE DATABASE ThucTap_TaskManagement;
END
GO

USE ThucTap_TaskManagement;
GO

-- =========================================================
-- 2) XOA BANG NEU DA TON TAI (de chay lai script cho de)
-- =========================================================
IF OBJECT_ID('dbo.tasks', 'U') IS NOT NULL
    DROP TABLE dbo.tasks;
GO

IF OBJECT_ID('dbo.projects', 'U') IS NOT NULL
    DROP TABLE dbo.projects;
GO

IF OBJECT_ID('dbo.users', 'U') IS NOT NULL
    DROP TABLE dbo.users;
GO

-- =========================================================
-- 3) TAO BANG USERS
-- =========================================================
CREATE TABLE dbo.users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ten NVARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    role VARCHAR(30) NOT NULL,
    dang_hoat_dong BIT NOT NULL DEFAULT 1,
    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT CK_users_role
        CHECK (role IN ('USER', 'MANAGER'))
);
GO

-- =========================================================
-- 4) TAO BANG PROJECTS
-- =========================================================
CREATE TABLE dbo.projects (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ten_project NVARCHAR(150) NOT NULL,
    mo_ta NVARCHAR(500) NULL,
    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME()
);
GO

-- =========================================================
-- 5) TAO BANG TASKS
-- =========================================================
CREATE TABLE dbo.tasks (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    project_id BIGINT NOT NULL,
    tieu_de NVARCHAR(200) NOT NULL,
    mo_ta NVARCHAR(1000) NULL,
    trang_thai VARCHAR(30) NOT NULL DEFAULT 'CHUA_LAM',
    user_duoc_giao BIGINT NULL,
    deadline DATETIME2 NOT NULL,
    ngay_tao DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    ngay_cap_nhat DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT FK_tasks_project
        FOREIGN KEY (project_id) REFERENCES dbo.projects(id),

    CONSTRAINT FK_tasks_user
        FOREIGN KEY (user_duoc_giao) REFERENCES dbo.users(id),

    CONSTRAINT CK_tasks_trang_thai
        CHECK (trang_thai IN ('CHUA_LAM', 'DANG_LAM', 'HOAN_THANH')),

    CONSTRAINT CK_tasks_tieu_de_not_blank
        CHECK (LEN(LTRIM(RTRIM(tieu_de))) > 0),

    CONSTRAINT CK_tasks_deadline_after_created
        CHECK (deadline > ngay_tao)
);
GO

-- =========================================================
-- 6) TAO INDEX
-- =========================================================
CREATE INDEX IX_tasks_project_id ON dbo.tasks(project_id);
GO

CREATE INDEX IX_tasks_user_duoc_giao ON dbo.tasks(user_duoc_giao);
GO

CREATE INDEX IX_tasks_trang_thai ON dbo.tasks(trang_thai);
GO

CREATE INDEX IX_tasks_project_status ON dbo.tasks(project_id, trang_thai);
GO

CREATE INDEX IX_users_email ON dbo.users(email);
GO

-- =========================================================
-- 7) INSERT USERS
-- =========================================================
INSERT INTO dbo.users (ten, email, role, dang_hoat_dong)
VALUES
(N'An',    'an@gmail.com',    'USER',    1),
(N'Binh',  'binh@gmail.com',  'USER',    1),
(N'Cuong', 'cuong@gmail.com', 'USER',    1),
(N'Dung',  'dung@gmail.com',  'USER',    0),
(N'Hoa',   'hoa@gmail.com',   'MANAGER', 1),
(N'Lan',   'lan@gmail.com',   'USER',    1);
GO

-- =========================================================
-- 8) INSERT PROJECTS
-- =========================================================
INSERT INTO dbo.projects (ten_project, mo_ta)
VALUES
(N'Project thực tập',          N'Project quản lý task nội bộ'),
(N'Project website bán hàng',  N'Backend + database'),
(N'Project mobile app',        N'App quản lý công việc'),
(N'Project nội bộ HR',         N'Phần mềm quản lý nhân sự');
GO

-- =========================================================
-- 9) INSERT TASKS (24 records)
-- Tong cong: 6 users + 4 projects + 24 tasks = 34 records
-- =========================================================
INSERT INTO dbo.tasks (project_id, tieu_de, mo_ta, trang_thai, user_duoc_giao, deadline)
VALUES
-- Project 1
(1, N'Đọc yêu cầu domain',      N'Phân tích bài toán tuần 1',                  'HOAN_THANH', 1, DATEADD(DAY, 3, SYSDATETIME())),
(1, N'Xác định entity',         N'User Project Task',                           'HOAN_THANH', 1, DATEADD(DAY, 4, SYSDATETIME())),
(1, N'Viết enum trạng thái',    N'Trang thai task',                             'DANG_LAM',   2, DATEADD(DAY, 5, SYSDATETIME())),
(1, N'Viết class User',         N'Code class User',                             'CHUA_LAM',   2, DATEADD(DAY, 6, SYSDATETIME())),
(1, N'Viết class Task',         N'Code class Task',                             'CHUA_LAM',   3, DATEADD(DAY, 7, SYSDATETIME())),
(1, N'Test console',            N'Chạy App.java',                               'DANG_LAM',   6, DATEADD(DAY, 8, SYSDATETIME())),

-- Project 2
(2, N'Tạo database',            N'Khởi tạo DB SQL Server',                      'HOAN_THANH', 5, DATEADD(DAY, 2, SYSDATETIME())),
(2, N'Tạo bảng users',          N'Bảng người dùng',                             'HOAN_THANH', 5, DATEADD(DAY, 3, SYSDATETIME())),
(2, N'Tạo bảng projects',       N'Bảng dự án',                                  'HOAN_THANH', 5, DATEADD(DAY, 4, SYSDATETIME())),
(2, N'Tạo bảng tasks',          N'Bảng công việc',                              'DANG_LAM',   1, DATEADD(DAY, 5, SYSDATETIME())),
(2, N'Tạo foreign key',         N'Liên kết bảng',                               'CHUA_LAM',   2, DATEADD(DAY, 6, SYSDATETIME())),
(2, N'Tạo index',               N'Tối ưu truy vấn',                             'CHUA_LAM',   3, DATEADD(DAY, 7, SYSDATETIME())),

-- Project 3
(3, N'Thiết kế API user',       N'CRUD user',                                   'CHUA_LAM',   1, DATEADD(DAY, 5, SYSDATETIME())),
(3, N'Thiết kế API task',       N'CRUD task',                                   'CHUA_LAM',   2, DATEADD(DAY, 6, SYSDATETIME())),
(3, N'Thiết kế API project',    N'CRUD project',                                'DANG_LAM',   3, DATEADD(DAY, 7, SYSDATETIME())),
(3, N'Validate input',          N'Check null blank',                            'CHUA_LAM',   6, DATEADD(DAY, 8, SYSDATETIME())),
(3, N'Xử lý exception',         N'AppException',                                'CHUA_LAM', NULL, DATEADD(DAY, 9, SYSDATETIME())),
(3, N'Viết test service',       N'Test logic chính',                            'CHUA_LAM',   1, DATEADD(DAY, 10, SYSDATETIME())),

-- Project 4
(4, N'Khảo sát nghiệp vụ HR',   N'Phân tích yêu cầu người dùng',                'HOAN_THANH', 5, DATEADD(DAY, 3, SYSDATETIME())),
(4, N'Tạo tài liệu đặc tả',     N'Viết docs nghiệp vụ',                         'DANG_LAM',   6, DATEADD(DAY, 4, SYSDATETIME())),
(4, N'Xây dựng ERD',            N'Vẽ quan hệ bảng',                             'CHUA_LAM',   2, DATEADD(DAY, 5, SYSDATETIME())),
(4, N'Tạo dữ liệu test',        N'Insert sample data',                          'CHUA_LAM',   3, DATEADD(DAY, 6, SYSDATETIME())),
(4, N'Viết query báo cáo',      N'Thống kê task',                               'CHUA_LAM',   1, DATEADD(DAY, 7, SYSDATETIME())),
(4, N'Kiểm thử query',          N'Chạy query thực tế',                          'CHUA_LAM', NULL, DATEADD(DAY, 8, SYSDATETIME()));
GO

-- =========================================================
-- 10) KIEM TRA DU LIEU
-- =========================================================
SELECT * FROM dbo.users;
SELECT * FROM dbo.projects;
SELECT * FROM dbo.tasks;
GO

-- =========================================================
-- 11) QUERY TASK THEO USER
-- =========================================================

-- 11.1 Task cua user id = 1
SELECT 
    t.id,
    t.tieu_de,
    t.mo_ta,
    t.trang_thai,
    t.deadline,
    p.ten_project,
    u.ten AS ten_user
FROM dbo.tasks t
JOIN dbo.projects p ON t.project_id = p.id
JOIN dbo.users u ON t.user_duoc_giao = u.id
WHERE t.user_duoc_giao = 1;
GO

-- 11.2 Task cua user theo email
SELECT 
    t.id,
    t.tieu_de,
    t.trang_thai,
    t.deadline,
    p.ten_project
FROM dbo.tasks t
JOIN dbo.users u ON t.user_duoc_giao = u.id
JOIN dbo.projects p ON t.project_id = p.id
WHERE u.email = 'an@gmail.com';
GO

-- =========================================================
-- 12) QUERY TASK THEO PROJECT
-- =========================================================

-- 12.1 Task cua project id = 2
SELECT 
    t.id,
    t.tieu_de,
    t.trang_thai,
    t.deadline,
    u.ten AS user_duoc_giao
FROM dbo.tasks t
LEFT JOIN dbo.users u ON t.user_duoc_giao = u.id
WHERE t.project_id = 2
ORDER BY t.id;
GO

-- 12.2 Dem so task moi project
SELECT 
    p.id,
    p.ten_project,
    COUNT(t.id) AS tong_task
FROM dbo.projects p
LEFT JOIN dbo.tasks t ON p.id = t.project_id
GROUP BY p.id, p.ten_project
ORDER BY p.id;
GO

-- =========================================================
-- 13) QUERY TASK THEO STATUS
-- =========================================================

-- 13.1 Lay task dang lam
SELECT 
    t.id,
    t.tieu_de,
    t.trang_thai,
    p.ten_project,
    u.ten AS user_duoc_giao
FROM dbo.tasks t
JOIN dbo.projects p ON t.project_id = p.id
LEFT JOIN dbo.users u ON t.user_duoc_giao = u.id
WHERE t.trang_thai = 'DANG_LAM';
GO

-- 13.2 Dem task theo trang thai
SELECT 
    trang_thai,
    COUNT(*) AS so_luong
FROM dbo.tasks
GROUP BY trang_thai;
GO

-- =========================================================
-- 14) QUERY BO SUNG DE REVIEW / TOI UU
-- =========================================================

-- 14.1 Task qua han
SELECT 
    id,
    tieu_de,
    trang_thai,
    deadline
FROM dbo.tasks
WHERE deadline < SYSDATETIME()
  AND trang_thai <> 'HOAN_THANH';
GO

-- 14.2 Task chua gan user
SELECT 
    id,
    tieu_de,
    trang_thai
FROM dbo.tasks
WHERE user_duoc_giao IS NULL;
GO

-- 14.3 So task moi user
SELECT 
    u.id,
    u.ten,
    COUNT(t.id) AS so_task
FROM dbo.users u
LEFT JOIN dbo.tasks t ON u.id = t.user_duoc_giao
GROUP BY u.id, u.ten
ORDER BY so_task DESC, u.id;
GO

-- 14.4 So task theo project va trang thai
SELECT 
    p.ten_project,
    t.trang_thai,
    COUNT(*) AS so_luong
FROM dbo.tasks t
JOIN dbo.projects p ON t.project_id = p.id
GROUP BY p.ten_project, t.trang_thai
ORDER BY p.ten_project, t.trang_thai;
GO