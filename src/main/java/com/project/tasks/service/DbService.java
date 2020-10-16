package com.project.tasks.service;

import com.project.tasks.domain.Task;
import com.project.tasks.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {

  @Autowired
  private TaskRepository repository;

  public List<Task> getAllTasks() {
    return repository.findAll();
  }

  public Optional<Task> getTask(final Long id) {
    return repository.findById(id);
  }

  public Task saveTask(final Task task) {
    return repository.save(task);
  }

  public void deleteTask(final Long taskId) {
    repository.deleteById(taskId);
  }
}
