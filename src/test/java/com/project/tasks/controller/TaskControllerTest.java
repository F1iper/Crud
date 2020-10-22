package com.project.tasks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.project.tasks.domain.Task;
import com.project.tasks.domain.TaskDto;
import com.project.tasks.mapper.TaskMapper;
import com.project.tasks.service.DbService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DbService dbService;

  @MockBean
  private TaskMapper taskMapper;

  @Test
  public void shouldFetchEmptyTaskList() throws Exception {
    //Given
    List<TaskDto> taskList = new ArrayList<>();
    when(taskMapper.mapToTaskDtoList(new ArrayList<>())).thenReturn(taskList);

    //When & Then
    mockMvc.perform(get("/v1/task"
        + "/getTasks").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void shouldFetchTaskLists() throws Exception {
    //Given
    List<TaskDto> tasks = new ArrayList<>();
    TaskDto testTask = new TaskDto(1L, "title", "content");
    tasks.add(testTask);

    when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(tasks);

    //When & Then
    mockMvc.perform(get("/v1/task/getTasks")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].title", is("title")))
        .andExpect(jsonPath("$[0].content", is("content")));
  }

  @Test
  public void shouldFetchTask() throws Exception {
    //Given
    TaskDto taskDto = new TaskDto(1L, "title", "content");
    Task task = new Task(1L, "title", "content");

    when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
    when(dbService.getTask(1L)).thenReturn(Optional.of(task));

    //When & Then
    mockMvc.perform(get("/v1/task/1")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .param("id", "1"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.title", is("title")))
        .andExpect(jsonPath("$.content", is("content")));
  }

  @Test
  public void shouldCreateTask() throws Exception {
    //Given
    TaskDto taskDto = new TaskDto(1L, "title", "content");
    Task task = new Task(1L, "title", "content");

    when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);

    Gson gson = new Gson();

    //When & Then
    mockMvc.perform(post("/v1/task")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(gson.toJson(task)))
        .andExpect(status().is(200));
  }

  @Test
  public void shouldUpdateTask() throws Exception {
    //Given
    TaskDto taskDto = new TaskDto(1L, "title", "content");
    Task task = new Task(1L, "title", "content");

    when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
    when(dbService.saveTask(any(Task.class))).thenReturn(task);
    when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);

    Gson gson = new Gson();

    //When & Then
    mockMvc.perform(put("/v1/task")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(gson.toJson(task)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.title", is("title")))
        .andExpect(jsonPath("$.content", is("content")));
  }

  @Test
  public void shouldDeleteTask() throws Exception {
    //Given
    Task task = new Task(1L, "title", "content");

    when(dbService.getTask(1L)).thenReturn(Optional.of(task));

    //When & Then
    mockMvc.perform(delete("/v1/task/1")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .param("id", "1"))
        .andExpect(status().isOk());
  }

}
