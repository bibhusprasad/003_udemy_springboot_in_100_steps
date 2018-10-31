package com.springboot100steps.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot100steps.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	List<Todo> findByName(String name);
}
