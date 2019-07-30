package com.smarthome.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthome.model.User;
import com.smarthome.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Transactional
	public void register(User user) {
		if (user.getLastName() != null && user.getFirstName() != null && user.getEmail() != null
				&& user.getPassword() != null && user.getPhoneNumber() != null && user.getAdress() != null) {
			userRepository.save(user);
		}
	}
	
	@Transactional
	public User login(String email, String password){
		List<User> resultList = userRepository.loginQuery(email, password); 
		
		if(resultList.size() > 0) {
			return resultList.get(0);
		}else {
			return new User();
		}
	}
	
	@Transactional
	public User getUserByEmail(String email){
		List<User> resultList = userRepository.getUserByEmail(email); 
		
		if(resultList.size() > 0) {
			return resultList.get(0);
		}else {
			return new User();
		}
	}
	
	@Transactional
	public void updateUserDetails(String phoneNumber, String password, int id) {
		userRepository.updateUserById(phoneNumber, password, id);
	}
	
	@Transactional
	public List<String> getAllEmails() {
		return userRepository.getAllEmails();
	}
	
	@Transactional
	public List<String> getAllPhoneNumbers() {
		return userRepository.getAllPhoneNumbers();
	}
	
	@Transactional
	public User getUserByHouseId(int houseId) {
		return userRepository.getUserByHouseId(houseId);
	}
	
}
