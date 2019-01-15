package com.hammar.rest.webservices.restfulwebservice.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	@GetMapping("/filtering")
	public SomeBean retriveSomeBean() {
		return new SomeBean("Value1", "Value2","Value3");
	}
	
	
}
