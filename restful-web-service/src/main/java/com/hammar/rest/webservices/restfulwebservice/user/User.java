package com.hammar.rest.webservices.restfulwebservice.user;

import java.util.Date;

public class User {

	private String name;
	private Integer id;
	private Date birthDate;

	public User(String name, Integer id, Date birthDate) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", birthDate=" + birthDate + "]";
	}
	

}
