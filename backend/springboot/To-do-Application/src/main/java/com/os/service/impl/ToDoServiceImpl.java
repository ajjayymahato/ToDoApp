package com.os.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.os.exception.ToDOListNotFoundException;
import com.os.model.Status;
import com.os.model.ToDoPojo;
import com.os.repository.ToDORepository;
import com.os.service.ToDOService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDOService{

	
	private final ToDORepository repo;
	@Override
	public ToDoPojo addToDO(ToDoPojo todo) {
		// TODO Auto-generated method stub
		
		
		todo.setStatus(Status.PENDING);
		ToDoPojo addpojo = repo.save(todo);
		return addpojo ;
	}

	@Override
	public ToDoPojo editToDo(ToDoPojo todo) throws ToDOListNotFoundException {
		// TODO Auto-generated method stub
		int id = todo.getId();
		
		Optional<ToDoPojo> exist = repo.findById(id);
		if(exist.isEmpty()) {
			throw new ToDOListNotFoundException("There is no such to do list");
			
		}else {
		
		exist.get().setDescription(todo.getDescription());
		exist.get().setStatus(todo.getStatus());
		exist.get().setDate(todo.getDate());
		
		repo.save(exist.get());
		return exist.get();
		}
	}

	@Override
	public String deleteToDo(int id) throws ToDOListNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<ToDoPojo> exist = repo.findById(id);
		if(exist.isEmpty()) {
			throw new ToDOListNotFoundException("There is no such to do list");
		}else {
			
			repo.deleteById(id);
			return "ToDo List deleted successfully" +" /n " + exist.toString();

		}
	}

	@Override
	public List<ToDoPojo> filterPojoByStatus(Status status) {
		// TODO Auto-generated method stub
		return repo.findAllByStatus(status);
	}

	@Override
	public List<ToDoPojo> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public ToDoPojo findById(int id) throws ToDOListNotFoundException {
		
		
		Optional<ToDoPojo>exist = repo.findById(id);
		
		
		if(exist.isPresent()) {
			return exist.get();
		}else {
			throw new ToDOListNotFoundException("Not found");
		}
	}
	
	public ToDoPojo changeStatus(int id,Status status) throws ToDOListNotFoundException {
		Optional<ToDoPojo>exist = repo.findById(id);
		if(exist.isPresent()) {
			
			ToDoPojo ele = exist.get();
			ele.setStatus(status);
			
			repo.save(ele);
			return ele;
		}else {
			throw new ToDOListNotFoundException("Not found");
		}
	}
		

}


