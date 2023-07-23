package com.example.demo.controller;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Todo;
import com.example.demo.entity.Users;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.AddTodoRequest;
import com.example.demo.request.AddUserRequest;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserRepository userRepository;
	private TodoRepository todoRepository;
	
	public UserController(UserRepository userRepository, TodoRepository todoRepository) {
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
	}
	
	@GetMapping("/{userId}")
	public Users getUserById(@PathVariable Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	}
	
	@PostMapping
	public Users addUser(@RequestBody AddUserRequest userRequest) {
		Users user = new Users();
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		return userRepository.save(user);
	}
	
	@PostMapping("/{userId}/todos")
	public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest) {
		Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		Todo todo = new Todo();
		todo.setContent(todoRequest.getContent());
		user.getTodoList().add(todo);
		userRepository.save(user);
	}
	
	@PostMapping("/todos/{todoId}")
	public void toggleTodoCompleted(@PathVariable Long todoId) {
		Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
		todo.setCompleted(!todo.getCompleted());
		todoRepository.save(todo);
	}
	
	@DeleteMapping("/{userId}/todos/{todoId}")
	public void deleteTodo(@PathVariable Long userId, @PathVariable Long todoId) {
		Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
		user.getTodoList().remove(todo);
		todoRepository.delete(todo);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		userRepository.delete(user);
	}
}