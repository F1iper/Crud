package com.project.tasks.mapper;

import static org.junit.Assert.assertEquals;

import com.project.tasks.domain.Task;
import com.project.tasks.domain.TaskDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

  @InjectMocks
  private TaskMapper taskMapper;

  @Test
  public void shouldMapToTask() {
    //Given
    TaskDto taskDto = new TaskDto(1L, "test", "content");
    Task task = new Task(1L, "test", "content");

    //When
    Task result = taskMapper.mapToTask(taskDto);

    //Then
    assertEquals(task.getId(), result.getId());
    assertEquals(task.getContent(), result.getContent());
    assertEquals(task.getTitle(), result.getTitle());
  }

  @Test
  public void shouldMapToTaskDto() {
    //Given
    Task task = new Task(1L, "test", "content");
    TaskDto taskDto = new TaskDto(1L, "test", "content");

    //When
    TaskDto result = taskMapper.mapToTaskDto(task);

    //Then
    assertEquals(task.getId(), result.getId());
    assertEquals(task.getContent(), result.getContent());
    assertEquals(task.getTitle(), result.getTitle());
  }

  @Test
  public void shouldMapToTaskDtoList() {
    //Given
    Task task = new Task(1L, "test", "content");
    List<Task> taskList = Arrays.asList(task);

    TaskDto taskDto = new TaskDto(1L, "test", "content");
    List<TaskDto> taskDtoList = Arrays.asList(taskDto);

    //When
    List<TaskDto> resultList = taskMapper.mapToTaskDtoList(taskList);

    //Then
    resultList.forEach(mappedTask -> {
      assertEquals(taskDto.getId(), mappedTask.getId());
      assertEquals(taskDto.getTitle(), mappedTask.getTitle());
      assertEquals(taskDto.getContent(), mappedTask.getContent());
    });

  }
}
