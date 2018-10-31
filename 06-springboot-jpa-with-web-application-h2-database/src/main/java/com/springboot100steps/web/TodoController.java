package com.springboot100steps.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot100steps.model.Todo;
import com.springboot100steps.service.TodoRepository;

@Controller
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value="/show-all-todos", method=RequestMethod.GET)
	public String showAllTodoList(ModelMap model) {
		String name = getLoggedInUser(model);
		model.put("todos", todoRepository.findByName(name));
		return "todo-view-list";
	}

	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showTodoAddPage(ModelMap model) {
		model.addAttribute("todo", new Todo(getLoggedInUser(model), "Default Desc", new Date(), false));
		return "todo-add";
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addATodoValue(@Valid Todo todo, BindingResult results, ModelMap model) throws Exception {
		if(results.hasErrors()) {
			return "todo-add";
		}
		String name = getLoggedInUser(model);
		todo.setName(name);
		todoRepository.save(todo);
		return "redirect:/show-all-todos";
	}
	
	@RequestMapping(value="/delete-a-todo", method=RequestMethod.GET)
	public String deleteATodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:/show-all-todos";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		String name = getLoggedInUser(model);
		List<Todo> todos = todoRepository.findByName(name);
		Todo todo = null;
		for(Todo todoNew : todos) {
			if(id == todoNew.getId()) {
				todo = todoNew;
			}
		}
		model.put("todo", todo);
		return "todo-add";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateATodo(@Valid Todo todo, BindingResult results, ModelMap model) {
		if(results.hasErrors()) {
			return "todo-add";
		}
		String name = getLoggedInUser(model);
		Iterator<Todo> iterator = todoRepository.findByName(name).iterator();
		while(iterator.hasNext()) {
			Todo todoItr = iterator.next();
			if(todoItr.getId() == todo.getId()) {
				iterator.remove();
			}
		}
		todoRepository.save(todo);
		return "redirect:/show-all-todos";
	}
	
	private String getLoggedInUser(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
}
