package com.hammar.webservices.soap.soapcoursemanagement.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.hammar.courses.CourseDetails;
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
		
		
		return mapCourse(course);
	}

	private GetCourseDetailsResponse mapCourse(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails cd = new CourseDetails();
		
		cd.setId(course.getId());
		cd.setName(course.getName());
		cd.setDescription(course.getDescription());
		response.setCourseDetails(cd);
		
		return response;
	}


}
