package com.smarthome.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.smarthome.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
	
	@Transactional
	@Modifying
	@Query("Update Notification n SET n.doorNotificationPhone = ?1, n.doorNotificationEmail = ?2,"
			+ "n.gasNotificationPhone = ?3, n.gasNotificationEmail = ?4  WHERE n.id= ?5")
	Integer updateNotification(int doorNotificationPhone, int doorNotificationEmaiil, 
								int gasNotificationPhone, int gasNotificationEmail,int id);
	
	@Query("select n from Notification n where n.id = ?1")
    Notification getNotificationByHouseId(int id);
}
