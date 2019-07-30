package com.smarthome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthome.model.Bulb;
import com.smarthome.service.BulbService;

@RestController
public class BulbController {

	@Autowired
	BulbService bulbService;
	
	@RequestMapping(value = "/bulb/{type}{bulbIp}", method = RequestMethod.GET)
	public void turnOnOffBulb(@PathVariable String type,@RequestParam String bulbIp) throws Exception {
		switch(type) {
		case "1":
			bulbService.turnOnBulb(bulbIp);
			break;
		case "0":
			bulbService.turnOffBulb(bulbIp);
			break;
		}
	}
	
	@RequestMapping(value = "/bulb/brightness/{value}{bulbIp}", method = RequestMethod.GET)
	public void setBrightness(@PathVariable String value,@RequestParam String bulbIp) throws Exception {
		bulbService.setBulbBrightness(bulbIp, value);
	}

	@RequestMapping(value = "/bulbs/{houseId}", method = RequestMethod.GET)
	public List<Bulb> setBrightness(@PathVariable String houseId) throws Exception {
		return bulbService.getBulbsByHouseId(Integer.valueOf(houseId));
	}
	
	@RequestMapping(value = "/bulb/update/{state}{ip}", method = RequestMethod.GET)
	public int updateBulbState(@PathVariable String state,@RequestParam String bulbIp) throws Exception {
		return bulbService.updateBulbState(Integer.valueOf(state),bulbIp);
	}
	
	@RequestMapping(value = "/bulb/update/brightness/{brightness}{ip}", method = RequestMethod.GET)
	public int updateBulbBrightness(@PathVariable String brightness,@RequestParam String bulbIp) throws Exception {
		return bulbService.updateBulbBrightness(Integer.valueOf(brightness), bulbIp);
	}
	
	@RequestMapping(value = "/bulb/update/name/{name}{ip}", method = RequestMethod.GET)
	public int updateBulbName(@PathVariable String name,@RequestParam String bulbIp) throws Exception {
		return bulbService.updateBulbName(name, bulbIp);
	}
}
