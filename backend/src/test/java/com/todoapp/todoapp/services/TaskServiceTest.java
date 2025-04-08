package com.todoapp.todoapp.services;
import org.springframework.test.context.ActiveProfiles;
import com.todoapp.todoapp.models.Task;
import com.todoapp.todoapp.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class TaskServiceTest {
    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void testCreateTask() {
        // Arrange
        Task sampleTask = new Task();
        sampleTask.setTitle("Test Task");
        sampleTask.setDescription("This is a test task.");
        sampleTask.setDone(false);

        when(taskRepository.save(sampleTask)).thenReturn(sampleTask);


        Task createdTask = taskService.createTask(sampleTask);


        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getTitle());
        assertEquals("This is a test task.", createdTask.getDescription());
        verify(taskRepository, times(1)).save(sampleTask);
    }

    @Test
    void testGetLatestPendingTasks() {

        Task task1 = new Task();
        task1.setTitle("Pending Task 1");

        Task task2 = new Task();
        task2.setTitle("Pending Task 2");

        List<Task> mockTasks = Arrays.asList(task1, task2);
        when(taskRepository.findTop5ByIsDoneFalseOrderByTimeDesc()).thenReturn(mockTasks);


        List<Task> latestPendingTasks = taskService.getLatestPendingTasks();


        assertEquals(2, latestPendingTasks.size());
        assertEquals("Pending Task 1", latestPendingTasks.get(0).getTitle());
        verify(taskRepository, times(1)).findTop5ByIsDoneFalseOrderByTimeDesc();
    }

    @Test
    void testMarkTaskAsDoneSuccess() {

        Task task = new Task();
        task.setId(1L);
        task.setDone(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        boolean result = taskService.markTaskAsDone(1L);


        assertTrue(result);
        assertTrue(task.isDone());
        verify(taskRepository).findById(1L);
        verify(taskRepository).save(task);
    }

    @Test
    void testMarkTaskAsDoneNotFound() {

        when(taskRepository.findById(99L)).thenReturn(Optional.empty());


        boolean result = taskService.markTaskAsDone(99L);


        assertFalse(result);
        verify(taskRepository).findById(99L);
        verify(taskRepository, never()).save(any());
    }
}
