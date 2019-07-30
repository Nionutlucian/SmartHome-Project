package com.smarthome.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.smarthome.model.Bulb;
import com.smarthome.repository.BulbRepository;
import com.smarthome.repository.BulbScheduleRepository;
import com.smarthome.repository.SmarthomeRepository;
import com.smarthome.utility.CommandPromptCommands;

@Service
@Scope("prototype")
public class BulbService {

	@Autowired
	BulbRepository bulbRepository;
	
	@Autowired
	SmarthomeRepository smarthomeRepository;
	
	@Autowired
	BulbScheduleRepository bulbScheduleRepository;
	
	CommandPromptCommands cmd;
		
	@Transactional
	public void turnOnBulb(String ip) throws Exception {
		cmd = new CommandPromptCommands();
		cmd.sendOnCommand(ip);
		bulbRepository.updateBulbState(1, ip);
	}
	
	@Transactional
	public void turnOffBulb(String ip) throws Exception {
		cmd = new CommandPromptCommands();
		cmd.sendOffCommand(ip);
		bulbRepository.updateBulbState(0, ip);
	}
	
	@Transactional
	public void setBulbBrightness(String ip,String value) throws Exception {
		cmd = new CommandPromptCommands();
		cmd.sendSetColorCommand(ip, value);
	}
	
	@Transactional
	public List<Bulb> getBulbsByHouseId(int houseId) throws Exception {
		return bulbRepository.getAllBulbsByHouseId(houseId);
	}
	
	@Transactional
	public int updateBulbState(int state, String ip) throws Exception {
		return bulbRepository.updateBulbState(state, ip);
	}
	
	@Transactional
	public int updateBulbBrightness(int brightness, String ip) throws Exception {
		return bulbRepository.updateBulbBrightness(brightness, ip);
	}
	
	@Transactional
	public int updateBulbName(String name, String ip) throws Exception {
		return bulbRepository.updateBulbName(name, ip);
	}	
}
