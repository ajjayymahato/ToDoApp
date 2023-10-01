package com.os.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.os.model.Status;
import com.os.model.ToDoPojo;

@Repository
public interface ToDORepository extends JpaRepository<ToDoPojo, Integer> {
	List<ToDoPojo>findAllByStatus(Status status);
}
