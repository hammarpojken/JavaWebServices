package com.hammar.rest.webservices.restfulwebservice.user;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

	@Autowired
	private UserDaoService dao;
	
	@Autowired
	private UserRepo repo;

	@GetMapping(path = ("/jpa/users"))
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@GetMapping(path = "/jpa//users/{id}")
	public Resource<User> getUserById(@PathVariable int id) {
		Optional<User> user = repo.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-" + id);
		
		Resource<User> resource = new Resource<User>(user.get());
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	@DeleteMapping(path = "/jpa//users/{id}")
	public void deleteUserById(@PathVariable int id) {
		repo.deleteById(id);
		

	}

	@PostMapping("/jpa//users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = repo.save(user);
		
		URI uri = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
