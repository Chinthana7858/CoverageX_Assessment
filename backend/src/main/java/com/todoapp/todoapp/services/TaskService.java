package com.todoapp.todoapp.services;
import com.todoapp.todoapp.models.Task;
import com.todoapp.todoapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getLatestPendingTasks() {
        return taskRepository.findTop5ByIsDoneFalseOrderByTimeDesc();
    }

    public boolean markTaskAsDone(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDone(true);
            taskRepository.save(task);
            return true;
        }
        return false;
    }
}
