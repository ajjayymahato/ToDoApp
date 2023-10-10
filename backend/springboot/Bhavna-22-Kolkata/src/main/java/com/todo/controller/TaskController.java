package com.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.CountType;
import com.todo.entity.Task;
import com.todo.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@AllArgsConstructor
public class TaskController {

	@Autowired(required = true)
	TaskService taskService;

	@GetMapping("/getAll")
	public List<Task> getAllTask() {
		return taskService.getAllTask();
	}
	
	@GetMapping("/getAllTaskByDueDate")
	public List<Task> getAllTaskSortBy() {
		return taskService.getAllTaskSortedByDueDate();
	}

	@PostMapping("/saveTask")
	public Task saveTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}

	@PutMapping("/updateTask/{id}")
	public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable int id) {
		if (taskService.existedById(id)) {
			Task t = taskService.getById(id).orElseThrow(() -> new EntityNotFoundException("Requested Task not found"));
			t.setTitle(task.getTitle());
			t.setType(task.getType());
			t.setDueDate(task.getDueDate());
			t.setDescription(task.getDescription());
			taskService.saveTask(t);
			return ResponseEntity.ok().body(t);
		} else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", id + " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	
	@GetMapping("/task/{id}")
	public Task getById(@PathVariable int id) {
		return taskService.getById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found"));
	}
	
	@DeleteMapping("/deleteTask/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable int id) {
		if (taskService.existedById(id)) {
			taskService.deleteTaskById(id);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		} else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", id + " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	
	@GetMapping("/getPercentageGroupByType")
	public List<CountType> getPercentageGroupByType(){
		return taskService.getPercentageGroupByType();
	}
}
