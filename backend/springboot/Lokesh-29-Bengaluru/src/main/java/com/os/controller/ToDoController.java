package com.os.controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.os.exception.ToDOListNotFoundException;
import com.os.model.Status;
import com.os.model.ToDoPojo;
import com.os.service.impl.ToDoServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class ToDoController {
	
	private final ToDoServiceImpl service;
	
	
	@PostMapping("/add")
	private ResponseEntity<ToDoPojo>newToDOList(@RequestBody ToDoPojo list){
		
		ToDoPojo res = service.addToDO(list);
		
		
		return new ResponseEntity<ToDoPojo>(res ,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/edit")
	private ResponseEntity<?>editToDOList(@RequestBody ToDoPojo list){
		
		try {
			ToDoPojo res = service.editToDo(list);
			
				return new ResponseEntity<ToDoPojo>(res ,HttpStatus.CREATED);



		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND)	;
			}

		
		
	}
	
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<String>deleteTODO(@PathVariable int id) throws ToDOListNotFoundException{
		
		try {
			
			return new  ResponseEntity<String>(service.deleteToDo(id),HttpStatus.NO_CONTENT);

		} catch (Exception e) {
//			// TODO: handle exception
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND)	;
		}
			
		}
		
		@GetMapping("/getByID/{id}")
		private ResponseEntity<?>findByID(@PathVariable int id) throws ToDOListNotFoundException{
			
			try {
				
				return new  ResponseEntity<ToDoPojo>(service.findById(id),HttpStatus.OK);

			} catch (Exception e) {
//				// TODO: handle exception
				
				return new ResponseEntity<String>(e.getLocalizedMessage(),HttpStatus.NOT_FOUND)	;

				
			}

	}
	
	
	@GetMapping("/all")
	
	private ResponseEntity<List<ToDoPojo>>getAll()
	{
		return new ResponseEntity( service.getAll(),HttpStatus.OK);
		
	}

	
	@GetMapping("/filter/{status}")
	private ResponseEntity<List<ToDoPojo>>getAllByFilter(@PathVariable Status status){
		return new ResponseEntity(service.filterPojoByStatus(status),HttpStatus.OK);
	}
	
	@GetMapping("/updateStatus")
	private ResponseEntity<List<ToDoPojo>>updateStaus(@RequestParam int id ,@RequestParam Status status) throws ToDOListNotFoundException{
		return new ResponseEntity(service.changeStatus(id,status),HttpStatus.OK);
	}
}
