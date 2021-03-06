package com.example.demo.controller;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;

@Controller
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

//		@GetMapping
//		public String index(Model model) {
//			List<Todo> all = todoRepository.findAll();
//			model.addAttribute("todos", all);
//			model.addAttribute("");
//			return "index";
//		}

	@GetMapping(value = "/")
	public ModelAndView index(Model model) {
		model.addAttribute("todo", new Todo());
		ModelAndView modelAndView = new ModelAndView("index");
		List<Todo> todos = todoRepository.findAll();
		modelAndView.addObject("todos", todos);
		return modelAndView;
	}

	@PostMapping(value = "/todo")
	public String create(Todo todo) {
		try {
			todoRepository.save(todo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			Todo todo = todoRepository.findById(id).get();
			todoRepository.delete(todo);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/";
	}

	@PostMapping(value = "/done")
	@ResponseBody
	public String done(@RequestParam Integer id) {
		Todo todo = todoRepository.findById(id).get();
		todo.setDone(todo.isDone() ? false : true);
		todoRepository.save(todo);
		return "redirect:/";
	}

	private static final String MSG_SUCESS_INSERT = "Todo inserted successfully.";
	private static final String MSG_SUCESS_DELETE = "Todo Student successfully.";
	private static final String MSG_ERROR = "Error.";

}
