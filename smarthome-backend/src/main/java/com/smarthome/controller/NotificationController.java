package com.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.model.Notification;
import com.smarthome.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/notification/{type}{emailTo}", method = RequestMethod.GET)
	public void sendEmail(@PathVariable String type,@RequestParam String emailTo) {
		notificationService.sendEmail(emailTo,type);
	}

	@RequestMapping(value = "/notification/{houseId}", method = RequestMethod.PUT)
	public Notification updateNotification(@RequestBody Notification notification, @PathVariable String houseId) {
		return notificationService.updateNotification(notification.getDoorNotificationPhone(), notification.getDoorNotificationEmail(),
												notification.getGasNotificationPhone(), notification.getGasNotificationEmail(),Integer.valueOf(houseId));
	}
	
	@RequestMapping(value = "/notification/{houseId}", method = RequestMethod.GET)
	public Notification getNotification(@PathVariable String houseId) {
		return notificationService.getNotificationStatus(Integer.valueOf(houseId));
	}
}
