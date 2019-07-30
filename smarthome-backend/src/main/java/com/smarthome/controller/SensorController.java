package com.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.model.Sensor;
import com.smarthome.service.SensorService;

@RestController
public class SensorController {

	@Autowired
	SensorService sensorService;
	
//	@RequestMapping(value = "/sensor", method = RequestMethod.GET)
//	public Sensor getSensorsValues() throws Exception {
//				
//		return sensorService.getSensorsValues();
//	}
	
	@RequestMapping(value = "/sensor/values{id}", method = RequestMethod.GET)
	public Sensor getUpdatedSensorsValuesFromDatabaseByHouseId(@RequestParam int id) throws Exception {
				
		return sensorService.getUpdatedSensorValuesFromDatabaseByHouseId(id);
	}
}
