package com.hammar.rest.webservices.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int userCOunt = 4;
	static {
		users.add(new User("Niclas",1, new Date()));
		users.add(new User("Robin",2, new Date()));
		users.add(new User("Fredrik",3, new Date()));
		users.add(new User("Björn",4, new Date()));
	}
	
	public User FindOne(int id) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getId() == id)
				return users.get(i);
		}
		return null;
	}
		public User DeleteById(int id) {
			Iterator<User> iterator = users.iterator();
			while(iterator.hasNext()) {
				User user = iterator.next();
				if(user.getId() == id) {
					iterator.remove();
					return user;
				}
			}
			
			return null;
	}
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null)
			user.setId(++userCOunt);
		users.add(user);
		return user;
	}

}
