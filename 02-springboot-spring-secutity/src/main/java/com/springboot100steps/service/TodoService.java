package com.springboot100steps.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot100steps.model.Todo;

@Service
public class TodoService {

	public static List<Todo> todoList = new ArrayList<>();
	public static int count = 3;
	
	static {
		todoList.add(new Todo(1, "bibhu", "learn springboot", new Date(), false));
		todoList.add(new Todo(2, "bibhu", "learn rest", new Date(), false));
		todoList.add(new Todo(3, "bibhu", "learn java", new Date(), false));
	}
	
	public List<Todo> retriveTodos(String name) {
		List<Todo> list = new ArrayList<>();
		for(Todo todo : todoList) {
			if(name.equals(todo.getName())) {
				list.add(todo);
			}
		}
		return list;
	}

	public void addATodo(String name, String desc, Date targetDate) {
		todoList.add(new Todo(++count, name, desc, targetDate, false));
	}

	public void deleteATodo(int id) {
		Iterator<Todo> iterator = todoList.iterator();
		while(iterator.hasNext()) {
			Todo todo = iterator.next();
			if(id == todo.getId()) {
				iterator.remove();
			}
		}
	}
	
	public Todo retriveSingleTodo(int id) {
		for(Todo todo : todoList) {
			if(id == todo.getId()) {
				return todo;
			}
		}
		return null;
	}
	
	public void updateTodo(Todo todo) {
		Iterator<Todo> iterator = todoList.iterator();
		while(iterator.hasNext()) {
			Todo todoItr = iterator.next();
			if(todoItr.getId() == todo.getId()) {
				iterator.remove();
			}
		}
		todoList.add(todo);
	}

}
