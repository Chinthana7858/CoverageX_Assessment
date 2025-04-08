package com.todoapp.todoapp.repositories;

import com.todoapp.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTop5ByIsDoneFalseOrderByTimeDesc();
}
