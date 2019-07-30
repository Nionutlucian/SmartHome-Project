package com.smarthome.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.smarthome.model.Sensor;
import com.smarthome.model.User;
import com.smarthome.repository.BulbScheduleRepository;
import com.smarthome.service.BulbService;
import com.smarthome.service.NotificationService;
import com.smarthome.service.SensorService;
import com.smarthome.service.UserService;

@Component
public class Scheduler {
	@Autowired
	SensorService sensorService;

	@Autowired
	NotificationService notificationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BulbService bulbService;
	
	@Autowired
	BulbScheduleRepository bulbScheduleRepository;
	
	BulbScheduler bulbScheduler;
	
	private User user;
	private List<Sensor> initialSensorsValues;
	private List<Sensor> currentSensorValues;
	private int k = 0;
	private boolean notificationPhoneDoorWasSent = false;
	private boolean notificationEmailGasWasSent = false;
	private boolean notificationEmailDoorWasSent = false;
	private boolean notificationPhoneGasWasSent = false;
	
	public void sendNotifications() throws Exception {
		sensorService.getSensorsValues();
		if (k%5==0) {//verificare o data la fiecare 5 minute
			initialSensorsValues = sensorService.getUpdatedSensorValuesFromDatabase(); 
			notificationEmailDoorWasSent = false;
			notificationEmailGasWasSent = false;
			notificationPhoneDoorWasSent = false;
			notificationPhoneGasWasSent = false;
		}else {
			currentSensorValues = sensorService.getUpdatedSensorValuesFromDatabase();
			for(Sensor currentSensor : currentSensorValues) {
				for(Sensor initialSensor : initialSensorsValues) {
					if(initialSensor.getHouseId() == currentSensor.getHouseId()) {
						System.out.println("initial :" + initialSensor.getHouseId());
						System.out.println("current :" + currentSensor.getHouseId());
						System.out.println("initial :" + initialSensor.getDoorStatus());
						System.out.println("current :" + currentSensor.getDoorStatus());
						user = userService.getUserByHouseId(currentSensor.getHouseId());
						if(initialSensor.getDoorStatus() != currentSensor.getDoorStatus()) {
							System.out.println("House id: " + initialSensor.getHouseId());
							System.out.println("Notification email : " + notificationService.getNotificationStatus(currentSensor.getHouseId()).getDoorNotificationEmail());
							if(notificationService.getNotificationStatus(currentSensor.getHouseId()).getDoorNotificationEmail() == 1 && notificationEmailDoorWasSent == false) {
								notificationService.sendEmail(user.getEmail(), "door");
								notificationEmailDoorWasSent = true;
							}
							if(notificationService.getNotificationStatus(currentSensor.getHouseId()).getDoorNotificationPhone() == 1 && notificationPhoneDoorWasSent == false) {
								notificationService.sensSMSNotification(user.getPhoneNumber(), "door");
								notificationPhoneDoorWasSent = true;
							}
						}
						if(currentSensor.getGasStatus() == 1) {
							if(notificationService.getNotificationStatus(currentSensor.getHouseId()).getGasNotificationEmail() == 1 && notificationEmailGasWasSent == false ) {
								notificationService.sendEmail(user.getEmail(), "gas");
								notificationEmailGasWasSent = true;
							}
							if(notificationService.getNotificationStatus(currentSensor.getHouseId()).getGasNotificationPhone() == 1 && notificationPhoneGasWasSent == false) {
								notificationService.sensSMSNotification(user.getPhoneNumber(), "gas");
								notificationPhoneGasWasSent = true;
							}
						}
					}
				}
			}
		}
		k++;
	}

	@Scheduled(fixedRate = 60000)
	public void execSchedFunction() throws Exception {
		bulbScheduler = new BulbScheduler(bulbService, bulbScheduleRepository);
		bulbScheduler.startSchedule();
		sendNotifications();
	}
	
}
