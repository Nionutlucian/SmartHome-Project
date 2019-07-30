package com.smarthome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.model.User;
import com.smarthome.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user) {

		userService.register(user);
		return user;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody User user) {
		User u = userService.login(user.getEmail(), user.getPassword());
		User returnUser = new User();
		
		returnUser.setFirstName(u.getFirstName());
		returnUser.setLastName(u.getLastName());
		returnUser.setEmail(u.getEmail());
		
		return returnUser;
	}
	
	@RequestMapping(value = "/user{email}", method = RequestMethod.GET)
	public User getUserByEmail(@RequestParam String email) {
		User u = userService.getUserByEmail(email);
		
		return u;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		userService.updateUserDetails(user.getPhoneNumber(), user.getPassword(), user.getId());
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public List<String> getAllEmails() {
		
		return userService.getAllEmails();
	}
	
	@RequestMapping(value = "/phone", method = RequestMethod.GET)
	public List<String> getAllPhoneNumbers() {
		
		return userService.getAllPhoneNumbers();
	}
	
}
