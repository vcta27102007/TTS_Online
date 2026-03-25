package com.example.demo;

import com.example.demo.common.exception.BadRequestException;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.repository.ProjectRepository;
import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.entity.TaskEntity;
import com.example.demo.task.enums.TaskStatus;
import com.example.demo.task.repository.TaskRepository;
import com.example.demo.task.service.TaskService;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TaskService taskService;

    private UserEntity user;
    private ProjectEntity project;
    private TaskRequest request;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1L);
        user.setTen("An");
        user.setEmail("an@gmail.com");
        user.setRole("USER");
        user.setDangHoatDong(true);

        project = new ProjectEntity();
        project.setTenProject("Project test");
        project.setMoTa("Mo ta project");

        request = new TaskRequest();
        request.setTieuDe("Task 1");
        request.setMoTa("Mo ta task");
        request.setDeadline(LocalDateTime.now().plusDays(3));
        request.setUserId(1L);
        request.setProjectId(1L);
    }

    @Test
    void create_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        TaskEntity savedTask = new TaskEntity();
        savedTask.setTieuDe("Task 1");
        savedTask.setMoTa("Mo ta task");
        savedTask.setDeadline(request.getDeadline());
        savedTask.setTrangThai(TaskStatus.CHUA_LAM);
        savedTask.setUser(user);
        savedTask.setProject(project);

        when(taskRepository.save(any(TaskEntity.class))).thenReturn(savedTask);

        TaskEntity result = taskService.create(request);

        assertNotNull(result);
        assertEquals("Task 1", result.getTieuDe());
        assertEquals("Mo ta task", result.getMoTa());
        assertEquals(TaskStatus.CHUA_LAM, result.getTrangThai());
        assertEquals(user, result.getUser());
        assertEquals(project, result.getProject());

        ArgumentCaptor<TaskEntity> captor = ArgumentCaptor.forClass(TaskEntity.class);
        verify(taskRepository).save(captor.capture());

        TaskEntity taskToSave = captor.getValue();
        assertEquals("Task 1", taskToSave.getTieuDe());
        assertEquals("Mo ta task", taskToSave.getMoTa());
        assertEquals(request.getDeadline(), taskToSave.getDeadline());
        assertEquals(TaskStatus.CHUA_LAM, taskToSave.getTrangThai());
        assertEquals(user, taskToSave.getUser());
        assertEquals(project, taskToSave.getProject());
    }

    @Test
    void create_userNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> taskService.create(request)
        );

        assertEquals("User không tồn tại", ex.getMessage());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void create_projectNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> taskService.create(request)
        );

        assertEquals("Project không tồn tại", ex.getMessage());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void create_deadlineInPast() {
        request.setDeadline(LocalDateTime.now().minusDays(1));

        BadRequestException ex = assertThrows(
                BadRequestException.class,
                () -> taskService.create(request)
        );

        assertEquals("Deadline phải lớn hơn thời điểm hiện tại", ex.getMessage());
        verify(userRepository, never()).findById(any());
        verify(projectRepository, never()).findById(any());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void assign_success() {
        TaskEntity task = new TaskEntity();
        task.setTieuDe("Task can assign");
        task.setTrangThai(TaskStatus.CHUA_LAM);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(taskRepository.save(any(TaskEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TaskEntity result = taskService.assign(1L, 1L);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        verify(taskRepository).save(task);
    }

    @Test
    void assign_taskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> taskService.assign(1L, 1L)
        );

        assertEquals("Task không tồn tại", ex.getMessage());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void assign_userNotFound() {
        TaskEntity task = new TaskEntity();
        task.setTrangThai(TaskStatus.CHUA_LAM);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> taskService.assign(1L, 1L)
        );

        assertEquals("User không tồn tại", ex.getMessage());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void assign_failWhenTaskDone() {
        TaskEntity task = new TaskEntity();
        task.setTrangThai(TaskStatus.HOAN_THANH);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        BadRequestException ex = assertThrows(
                BadRequestException.class,
                () -> taskService.assign(1L, 1L)
        );

        assertEquals("Task đã hoàn thành, không thể assign", ex.getMessage());
        verify(userRepository, never()).findById(any());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void updateStatus_success_CHUA_LAM_to_DANG_LAM() {
        TaskEntity task = new TaskEntity();
        task.setTrangThai(TaskStatus.CHUA_LAM);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(TaskEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TaskEntity result = taskService.updateStatus(1L, TaskStatus.DANG_LAM);

        assertEquals(TaskStatus.DANG_LAM, result.getTrangThai());
        verify(taskRepository).save(task);
    }

    @Test
    void updateStatus_fail_statusNull() {
        TaskEntity task = new TaskEntity();
        task.setTrangThai(TaskStatus.CHUA_LAM);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        BadRequestException ex = assertThrows(
                BadRequestException.class,
                () -> taskService.updateStatus(1L, null)
        );

        assertEquals("Status không được để trống", ex.getMessage());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void updateStatus_fail_CHUA_LAM_to_HOAN_THANH() {
        TaskEntity task = new TaskEntity();
        task.setTrangThai(TaskStatus.CHUA_LAM);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        BadRequestException ex = assertThrows(
                BadRequestException.class,
                () -> taskService.updateStatus(1L, TaskStatus.HOAN_THANH)
        );

        assertEquals("Phải chuyển sang DANG_LAM trước", ex.getMessage());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void updateStatus_fail_whenTaskAlreadyDone() {
        TaskEntity task = new TaskEntity();
        task.setTrangThai(TaskStatus.HOAN_THANH);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        BadRequestException ex = assertThrows(
                BadRequestException.class,
                () -> taskService.updateStatus(1L, TaskStatus.DANG_LAM)
        );

        assertEquals("Task đã hoàn thành, không được update", ex.getMessage());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void getByUser_success() {
        TaskEntity task = new TaskEntity();
        task.setTieuDe("Task user");

        when(userRepository.existsById(1L)).thenReturn(true);
        when(taskRepository.findByUser_Id(1L)).thenReturn(List.of(task));

        List<TaskEntity> result = taskService.getByUser(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Task user", result.get(0).getTieuDe());
    }

    @Test
    void getByUser_userNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> taskService.getByUser(1L)
        );

        assertEquals("User không tồn tại", ex.getMessage());
        verify(taskRepository, never()).findByUser_Id(any());
    }

    @Test
    void getByProject_success() {
        TaskEntity task = new TaskEntity();
        task.setTieuDe("Task project");

        when(projectRepository.existsById(1L)).thenReturn(true);
        when(taskRepository.findByProject_Id(1L)).thenReturn(List.of(task));

        List<TaskEntity> result = taskService.getByProject(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Task project", result.get(0).getTieuDe());
    }

    @Test
    void getByProject_projectNotFound() {
        when(projectRepository.existsById(1L)).thenReturn(false);

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> taskService.getByProject(1L)
        );

        assertEquals("Project không tồn tại", ex.getMessage());
        verify(taskRepository, never()).findByProject_Id(any());
    }

    @Test
    void getMyTasks_success() {
        when(userRepository.findByEmail("an@gmail.com")).thenReturn(Optional.of(user));
        when(taskRepository.findByUser_Id(1L)).thenReturn(Collections.singletonList(new TaskEntity()));

        List<TaskEntity> result = taskService.getMyTasks("an@gmail.com");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(taskRepository).findByUser_Id(1L);
    }

    @Test
    void getMyTasks_userNotFound() {
        when(userRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(
                NotFoundException.class,
                () -> taskService.getMyTasks("abc@gmail.com")
        );

        assertEquals("Không tìm thấy user với email = abc@gmail.com", ex.getMessage());
        verify(taskRepository, never()).findByUser_Id(any());
    }
}