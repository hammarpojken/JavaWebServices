package com.hammar.webservices.soap.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.hammar.courses.CourseDetails;
import com.hammar.courses.GetAllCourseDetailsRequest;
import com.hammar.courses.GetAllCourseDetailsResponse;
import com.hammar.courses.GetCourseDetailsRequest;
import com.hammar.courses.GetCourseDetailsResponse;
import com.hammar.webservices.soap.soapcoursemanagement.soap.bean.Course;
import com.hammar.webservices.soap.soapcoursemanagement.soap.service.CourseDetailService;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailService service;
	
	@PayloadRoot(namespace="http://hammar.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		
		return mapCourseDetails(course);
	}
	@PayloadRoot(namespace="http://hammar.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = service.findAll();
		
		
		return mapAllCourseDetails(courses);
	}
	
	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		response.setCourseDetails(mapCourse(course));
		
		return response;
	}
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		
		for(Course course: courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		
		
		return response;
	}
	private CourseDetails mapCourse(Course course) {
		CourseDetails cd = new CourseDetails();
		
		cd.setId(course.getId());
		cd.setName(course.getName());
		cd.setDescription(course.getDescription());
		return cd;
	}




}
