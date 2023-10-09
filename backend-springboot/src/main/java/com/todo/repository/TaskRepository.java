package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todo.dto.CountType;
import com.todo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query("from Task t order by t.dueDate")
	List<Task> findAllSortedByDueDate();
	
	@Query(value = "Select new com.todo.dto.CountType(COUNT(*)/(select COUNT(*) from Task)*100,type) from Task GROUP BY type")
	List<CountType> getPercentageGroupByType();
}
