package com.hammar.webservices.soap.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;


import com.hammar.webservices.soap.soapcoursemanagement.soap.bean.Course;

@Component
public class CourseDetailService {
	
	private static List<Course> courses = new ArrayList<>();
	
	static {
		Course course1 = new Course(1, "Math", "Learn math");
		courses.add(course1);
		
		Course course2 = new Course(2, "Cooking", "Learn cooking");
		courses.add(course2);
		
		Course course3 = new Course(3, "History", "Learn history");
		courses.add(course3);
		
		Course course4 = new Course(4, "Sports", "Learn sports");
		courses.add(course4);
	}
	
	public Course findById(int id) {
		for(Course course: courses) {
			if(course.getId() == id)
				return course;
		}
		return null;
	}
	
	public List<Course> findAll() {
		return courses;
	}
	
	public int deleteById(int id) {
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId() == id) {
				iterator.remove();
				return 1;
			}
			
		}
		return 0;
	}



}
