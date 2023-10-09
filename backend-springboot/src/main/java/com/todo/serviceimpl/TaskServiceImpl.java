package com.todo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.dto.CountType;
import com.todo.entity.Task;
import com.todo.repository.TaskRepository;
import com.todo.service.TaskService;

@Service
public  class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepository taskRepository;
	
	public List<Task> getAllTask(){
		return taskRepository.findAll();
	}

	@Override
	public Task saveTask(Task task) {
		taskRepository.save(task);
		
		return task;
		
	}

	@Override
	public boolean existedById(int id) {
		
		return taskRepository.existsById(id);
	}

	@Override
	public Optional<Task> getById(int id) {
		return taskRepository.findById(id);
	}

	@Override
	public void deleteTaskById(int id) {
		taskRepository.deleteById(id);
	}

	@Override
	public List<CountType> getPercentageGroupByType() {
		
		return taskRepository.getPercentageGroupByType();
	}

	@Override
	public List<Task> getAllTaskSortedByDueDate() {
		
		return taskRepository.findAllSortedByDueDate();
	}
}
