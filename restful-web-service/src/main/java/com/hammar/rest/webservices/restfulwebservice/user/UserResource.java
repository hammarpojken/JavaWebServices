package com.hammar.rest.webservices.restfulwebservice.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService dao;

	@GetMapping(path = ("/users"))
	public List<User> getAllUsers() {
		return dao.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public User getUserById(@PathVariable int id) {
		User user = dao.FindOne(id);
		if(user == null)
			throw new UserNotFoundException("id-" + id);
		return user;
	}
	@DeleteMapping(path = "/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		User user = dao.DeleteById(id);
		if(user == null)
			throw new UserNotFoundException("id-" + id);

	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = dao.save(user);
		
		URI uri = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
