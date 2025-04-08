package com.todoapp.todoapp.controllers;
import com.todoapp.todoapp.models.Task;
import com.todoapp.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*") // Allow frontend access
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getLatestTasks() {
        return ResponseEntity.ok(taskService.getLatestPendingTasks());
    }

    @PatchMapping("/{id}/done")
    public ResponseEntity<Void> markAsDone(@PathVariable Long id) {
        boolean updated = taskService.markTaskAsDone(id);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
