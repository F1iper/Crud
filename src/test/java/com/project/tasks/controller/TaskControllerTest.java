package com.project.tasks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.project.tasks.domain.TaskDto;
import com.project.tasks.mapper.TaskMapper;
import com.project.tasks.service.DbService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

}
