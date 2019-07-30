package com.smarthome.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthome.model.BulbSchedule;
import com.smarthome.repository.BulbScheduleRepository;

@Service
public class BulbScheduleService {

	@Autowired
	BulbScheduleRepository bulbScheduleRepository;
	
	@Transactional
	public BulbSchedule insertSchedule(BulbSchedule b) {
		return bulbScheduleRepository.save(b);
	}
	
	@Transactional
	public int deleteSchedule() {
		return bulbScheduleRepository.deleteFromSchedule();
	}
	
	@Transactional
	public List<BulbSchedule> getBulbScheduleByBulbId(int bulbId) {
		return bulbScheduleRepository.getBulbScheduleByBulbId(bulbId);
	}
}
