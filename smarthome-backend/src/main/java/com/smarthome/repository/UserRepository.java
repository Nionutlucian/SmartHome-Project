package com.smarthome.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.smarthome.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	@Query("select u from User u where u.email = ?1 and u.password = ?2")
    List<User> loginQuery(String email,String password);
	
	@Query("select u from User u where u.email = ?1")
    List<User> getUserByEmail(String email);
	
	@Query("select u from User u where u.id = ?1")
    User  getUserByHouseId(int id);
	
	@Transactional
	@Modifying
	@Query("Update User u SET u.phoneNumber = ?1, u.password = ?2  WHERE u.id= ?3")
	Integer updateUserById(String phoneNumber, String password, int id);
	
	@Query("select u.email from User u")
    List<String> getAllEmails();
	
	@Query("select u.phoneNumber from User u")
    List<String> getAllPhoneNumbers();
}
