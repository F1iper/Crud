package com.project.tasks.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.project.tasks.domain.TaskDto;
import com.project.tasks.exceptions.TaskNotFoundException;
import com.project.tasks.mapper.TaskMapper;
import com.project.tasks.service.DbService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/task")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TaskController {

  private final DbService service;
  private final TaskMapper taskMapper;

  @GetMapping(value = "/getTasks")
  public List<TaskDto> getTasks() {
    return taskMapper.mapToTaskDtoList(service.getAllTasks());
  }

  @GetMapping("/{id}")
  public TaskDto getTask(@RequestParam Long id) throws TaskNotFoundException {
    return taskMapper.mapToTaskDto(service.getTask(id).orElseThrow(TaskNotFoundException::new));
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@RequestParam Long id) {
    service.deleteTask(id);
  }

  @PutMapping
  public TaskDto updateTask(@RequestBody TaskDto taskDto) {
    return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public void createTask(@RequestBody TaskDto taskDto) {
    service.saveTask(taskMapper.mapToTask(taskDto));

  }
}
