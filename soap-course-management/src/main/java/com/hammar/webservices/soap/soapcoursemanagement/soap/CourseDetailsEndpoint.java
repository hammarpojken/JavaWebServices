package com.hammar.webservices.soap.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.hammar.courses.CourseDetails;
import com.hammar.courses.GetCourseDetailsRequest;
import com.hammar.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	
	@PayloadRoot(namespace="http://hammar.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processRequest(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		CourseDetails cd = new CourseDetails();
		cd.setId(request.getId());
		cd.setName("Micro");
		cd.setDescription("Fun with microservices");
		response.setCourseDetails(cd);
		
		return response;
	}


}
