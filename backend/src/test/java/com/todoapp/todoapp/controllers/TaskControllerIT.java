package com.todoapp.todoapp.controllers;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoapp.todoapp.models.Task;
import com.todoapp.todoapp.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

@ActiveProfiles("test")
class TaskControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll(); // Clean DB before each test
    }

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task();
        task.setTitle("Integration Task");
        task.setDescription("Created via integration test");
        task.setDone(false);
        task.setTime(LocalDateTime.now());

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Integration Task"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void testGetLatestTasks() throws Exception {
        Task task = new Task();
        task.setTitle("Latest Task");
        task.setDescription("Pending task");
        task.setDone(false);
        task.setTime(LocalDateTime.now());
        taskRepository.save(task);

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Latest Task"));
    }

    @Test
    void testMarkAsDone_Success() throws Exception {
        Task task = new Task();
        task.setTitle("Mark Me");
        task.setDescription("Test patch");
        task.setDone(false);
        task.setTime(LocalDateTime.now());
        Task saved = taskRepository.save(task);

        mockMvc.perform(patch("/tasks/" + saved.getId() + "/done"))
                .andExpect(status().isOk());
    }

    @Test
    void testMarkAsDone_NotFound() throws Exception {
        mockMvc.perform(patch("/tasks/999/done"))
                .andExpect(status().isNotFound());
    }
}
