package com.hammar.rest.webservices.restfulwebservice.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService dao;
	
	@GetMapping(path =("/users"))
	public List<User> getAllUsers() {
		return dao.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public User getUserById(@PathVariable int id) {
		return dao.FindOne(id);
	}
	

}
