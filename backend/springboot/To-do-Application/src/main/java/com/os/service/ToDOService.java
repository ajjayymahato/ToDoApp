package com.os.service;

import java.util.List;

import com.os.exception.ToDOListNotFoundException;
import com.os.model.Status;
import com.os.model.ToDoPojo;

public interface ToDOService {
	public ToDoPojo addToDO(ToDoPojo todo);
	public ToDoPojo editToDo(ToDoPojo todo) throws ToDOListNotFoundException;
	public String deleteToDo(int id) throws ToDOListNotFoundException;
	public List<ToDoPojo>getAll();
	public List<ToDoPojo> filterPojoByStatus(Status status);
}
