package com.todoapp.todoapp;

import com.todoapp.todoapp.models.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createTaskAndGetTasks() {
        Task task = new Task();
        task.setTitle("E2E Task");
        task.setDescription("Test E2E flow");
        task.setDone(false);

        ResponseEntity<Task> createResponse = restTemplate.postForEntity("/tasks", task, Task.class);

        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        assertNotNull(Objects.requireNonNull(createResponse.getBody()).getId());


        ResponseEntity<Task[]> response = restTemplate.getForEntity("/tasks", Task[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length > 0);
    }
}
