/*
package com.pj.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.model.Appuser;
import com.pj.model.Login;
import com.pj.model.Task;
import com.pj.repo.AppUserRepository;
import com.pj.service.AppuserService;
import com.pj.service.LoginService;
import com.pj.service.TaskService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {TaskController.class, LoginController.class, AppuserController.class})
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private LoginService loginService;

    @MockBean
    private AppuserService appuserService;

    @MockBean
    private AppUserRepository appUserRepository;

    @Test
    public void testGetAllTasks() throws Exception {
        List<Task> tasks = Arrays.asList(
                new Task("1", "Task 1", "Description 1", "To Do"),
                new Task("2", "Task 2", "Description 2", "In Progress")
        );
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/v1/tasks/find/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].title").value("Task 2"));
    }

    @Test
    public void testGetTaskById() throws Exception {
        Task task = new Task("1", "Task 1", "Description 1", "To Do");
        when(taskService.getTaskById("1")).thenReturn(Optional.of(task));

        mockMvc.perform(get("/api/v1/tasks/find/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Task 1"));
    }

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task("1", "Task 1", "Description 1", "To Do");
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskJson = objectMapper.writeValueAsString(task);

        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Task 1"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task task = new Task("1", "Updated Task", "Updated Description", "In Progress");
        when(taskService.updateTask(any(Task.class))).thenReturn(task);

        ObjectMapper objectMapper = new ObjectMapper();
        String taskJson = objectMapper.writeValueAsString(task);

        mockMvc.perform(put("/api/v1/tasks/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Updated Task"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/api/v1/tasks/delete/id/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testLoginSuccess() throws Exception {
        Login login = new Login("testuser", "password");
        when(loginService.authenticateUser(any(Login.class))).thenReturn(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String loginJson = objectMapper.writeValueAsString(login);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":\"success\"}"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        Login login = new Login("testuser", "wrongpassword");
        when(loginService.authenticateUser(any(Login.class))).thenReturn(false);

        ObjectMapper objectMapper = new ObjectMapper();
        String loginJson = objectMapper.writeValueAsString(login);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("{\"result\":\"failed\"}"));
    }

    @Test
    public void testSaveUser() throws Exception {
        Appuser appuser = new Appuser("testuser", "password");
        when(appuserService.insertAppuser(any(Appuser.class))).thenReturn(appuser);

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(appuser);

        mockMvc.perform(post("/api/v1/appuser/saveuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    public void testGetAppUserById() throws Exception {
        Appuser appuser = new Appuser("1", "testuser", "password");
        when(appuserService.findById("1")).thenReturn(Optional.of(appuser));

        mockMvc.perform(get("/api/v1/appuser/find/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    public void testGetAllAppUser() throws Exception {
        List<Appuser> appusers = Arrays.asList(
                new Appuser("1", "testuser1", "password"),
                new Appuser("2", "testuser2", "password")
        );
        when(appuserService.findAll()).thenReturn(appusers);

        mockMvc.perform(get("/api/v1/appuser/find/allappuser"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username").value("testuser1"))
                .andExpect(jsonPath("$[1].username").value("testuser2"));
    }

    @Test
    public void testUpdateAppUser() throws Exception {
        Appuser appuser = new Appuser("1", "updateduser", "updatedpassword");
        when(appuserService.updateAppuser(any(Appuser.class))).thenReturn(appuser);

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(appuser);

        mockMvc.perform(put("/api/v1/appuser/updateappuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("updateduser"));
    }

    @Test
    public void testDeleteAppUser() throws Exception {
        mockMvc.perform(delete("/api/v1/appuser/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":\"success\"}"));
    }
}
     */