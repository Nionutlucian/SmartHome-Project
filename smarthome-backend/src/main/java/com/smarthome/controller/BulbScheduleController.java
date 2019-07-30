package com.smarthome.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.smarthome.model.BulbSchedule;
import com.smarthome.service.BulbScheduleService;

@RestController
public class BulbScheduleController {

	@Autowired
	BulbScheduleService bulbScheduleService;
	
	@RequestMapping(value = "/bulb/schedule", method = RequestMethod.POST)
	public BulbSchedule insertSchedule(@RequestBody BulbSchedule bulbSchedule) {

		return bulbScheduleService.insertSchedule(bulbSchedule);
	}
	
	@RequestMapping(value = "/bulb/schedule", method = RequestMethod.DELETE)
	public int deleteSchedule() {

		return bulbScheduleService.deleteSchedule();
	}
	
	@RequestMapping(value = "/bulb/schedule/{bulbId}", method = RequestMethod.GET)
	public List<BulbSchedule> getBulbScheduleByBulbId(@PathVariable String bulbId) {

		return bulbScheduleService.getBulbScheduleByBulbId(Integer.valueOf(bulbId));
	}

}
