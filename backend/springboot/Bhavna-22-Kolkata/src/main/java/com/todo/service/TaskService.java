package com.todo.service;

import java.util.List;
import java.util.Optional;

import com.todo.dto.CountType;
import com.todo.entity.Task;

public interface TaskService {

	// method to create a task
	Task saveTask(Task task);

	// method to get all the task
	List<Task> getAllTask();

	boolean existedById(int id);

	Optional<Task> getById(int id);

	void deleteTaskById(int id);

	List<CountType> getPercentageGroupByType();

	// method to get all the task
	List<Task> getAllTaskSortedByDueDate();
}
