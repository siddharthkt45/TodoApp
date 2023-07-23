package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Todo;
import com.example.demo.entity.Users;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Users user = new Users();
		user.setPassword("should be hashed");
		user.setUsername("John");
		
		Todo todo = new Todo();
		todo.setContent("Upload video to Yt");
		
		user.getTodoList().add(todo);
		
		userRepository.save(user);
	}

}