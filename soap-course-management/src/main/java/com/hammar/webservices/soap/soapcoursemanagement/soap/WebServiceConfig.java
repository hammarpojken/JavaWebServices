package com.hammar.webservices.soap.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
	
	
	@Bean
	ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet mds = new MessageDispatcherServlet();
		mds.setApplicationContext(context);
		mds.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean(mds, "/ws/*");
	}
	
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema courseSchema) {
		DefaultWsdl11Definition def = new DefaultWsdl11Definition();
		
		def.setPortTypeName("CoursePort");
		def.setTargetNamespace("http://hammar.com/courses");
		def.setLocationUri("/ws");
		def.setSchema(courseSchema);
		
		return def;
	}
	
	@Bean
	public XsdSchema courseSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}

}
