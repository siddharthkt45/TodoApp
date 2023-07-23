package com.example.demo.entity;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String username;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Todo> todoList = new ArrayList<>();
	
	public Users() {
	}

	public Users(Long id, String username, String password, List<Todo> todoList) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.todoList = todoList;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Todo> getTodoList() {
		return todoList;
	}
	
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
	
}