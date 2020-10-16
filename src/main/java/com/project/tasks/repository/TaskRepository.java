package com.project.tasks.repository;

import com.project.tasks.domain.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

  @Override
  List<Task> findAll();

  @Override
  Optional<Task> findById(Long taskId);

  @Override
  Task save(Task task);

  @Override
  void deleteById(Long taskId);

  @Override
  long count();

}
