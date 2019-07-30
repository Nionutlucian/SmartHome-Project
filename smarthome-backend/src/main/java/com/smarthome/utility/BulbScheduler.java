package com.smarthome.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.transaction.Transactional;
import com.smarthome.model.BulbSchedule;
import com.smarthome.repository.BulbScheduleRepository;
import com.smarthome.service.BulbService;


public class BulbScheduler {

	Date now = new Date();
    SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    
    BulbService bulbService;
    BulbScheduleRepository bulbScheduleRepository;
    
    private String currentDay;
    private String currentTime;
    
    public BulbScheduler(BulbService bulbService, BulbScheduleRepository bulbScheduleRepository) {
    	this.currentDay = simpleDateformat.format(now);
    	this.currentTime = sdf.format(cal.getTime());
    	this.bulbService = bulbService;
    	this.bulbScheduleRepository = bulbScheduleRepository;
    }
    
    @Transactional
    public void startSchedule() throws Exception {
    	for(BulbSchedule bulbSchedule : bulbScheduleRepository.getAllSchedules()) {
    		if(bulbSchedule.getDays().contains(currentDay)) {
    			if(bulbSchedule.getTime().equals(currentTime)) {
    				if(bulbSchedule.getAction().equals("ON")) {
    					System.out.println("Comanda de aprindere trimisa!");
    					bulbService.turnOnBulb(bulbSchedule.getIp());
    				}else {
    					System.out.println("Comanda de stingere trimisa!");
    					bulbService.turnOffBulb(bulbSchedule.getIp());
    				}
    			}
    		}
    	}
    }
}
