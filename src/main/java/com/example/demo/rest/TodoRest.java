package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;

@RestController
@RequestMapping(value = "/api")
public class TodoRest {

	@Autowired
	private TodoRepository todoRepository;

	@GetMapping("/todos")
	public List<Todo> all() {
		return todoRepository.findAll();
	}

	@GetMapping("/todos/{id}")
	public Todo getById(@PathVariable(value = "id") Integer id) {
		return todoRepository.findById(id).get();
	}

	@PostMapping("/todo")
	public Todo save(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@DeleteMapping("/todo")
	public void delete(@RequestBody Todo todo) {
		todoRepository.delete(todo);
	}

	@PutMapping("/todo")
	public Todo update(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@PostMapping("/todos/done/{id}")
	public void doneById(@PathVariable(value = "id") Integer id) {
		Todo todo = todoRepository.findById(id).get();
		todo.setDone(true);
		todoRepository.save(todo);
	}

	@PostMapping("/todo/undone/{id}")
	public void undoneById(@PathVariable(value = "id") Integer id) {
		Todo todo = todoRepository.findById(id).get();
		todo.setDone(false);
		todoRepository.save(todo);
	}

	@PostMapping("/todos/delete/{id}")
	public void deleteById(@PathVariable(value = "id") Integer id) {
		Todo todo = todoRepository.findById(id).get();
		todoRepository.delete(todo);
	}

}
