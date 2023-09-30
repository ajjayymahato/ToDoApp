package com.os.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "todo_table")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ToDoPojo {
	
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE	)
	private int id;
	
	
	private String description;
	
	private LocalDate date;
	@Enumerated(EnumType.STRING)
	private Status status;
	private LocalTime time;

}
