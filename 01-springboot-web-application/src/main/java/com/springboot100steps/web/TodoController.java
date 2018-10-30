package com.springboot100steps.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot100steps.model.Todo;
import com.springboot100steps.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value="/show-all-todos", method=RequestMethod.GET)
	public String showAllTodoList(ModelMap model) {
		String name = (String)model.get("name");
		model.put("todos", todoService.retriveTodos(name));
		return "todo-view-list";
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String showTodoAddPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String)model.get("name"), "Default Desc", new Date(), false));
		return "todo-add";
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addATodoValue(@Valid Todo todo, BindingResult results, ModelMap model) {
		if(results.hasErrors()) {
			return "todo-add";
		}
		String name = (String)model.get("name");
		todoService.addATodo(name, todo.getDesc(), todo.getTargetDate());
		return "redirect:/show-all-todos";
	}
	
	@RequestMapping(value="/delete-a-todo", method=RequestMethod.GET)
	public String deleteATodo(@RequestParam int id) {
		todoService.deleteATodo(id);
		return "redirect:/show-all-todos";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retriveSingleTodo(id);
		model.put("todo", todo);
		return "todo-add";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateATodo(@Valid Todo todo, BindingResult results, ModelMap model) {
		if(results.hasErrors()) {
			return "todo-add";
		}
		todoService.updateTodo(todo);
		return "redirect:/show-all-todos";
	}
}
