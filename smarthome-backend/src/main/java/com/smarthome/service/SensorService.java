package com.smarthome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthome.model.Sensor;
import com.smarthome.model.Smarthome;
import com.smarthome.repository.SensorRepository;
import com.smarthome.repository.SmarthomeRepository;
import com.smarthome.utility.ResponseHandler;

@Service
public class SensorService {

	@Autowired
	SensorRepository sensorRepository;
	
	@Autowired
	SmarthomeRepository smarthomeRepository;
	
	private Sensor sensor;
	private ResponseHandler responseHandler = new ResponseHandler();
	
	List<Smarthome> houses = new ArrayList<>();

	public List<Sensor> getSensorsValues() throws Exception {
		houses = smarthomeRepository.getAllHouses();
		List<Sensor> resultList = new ArrayList<>();
		for (Smarthome smarthome : houses) {
			sensor = null;
			System.out.println("Smarthome: " + smarthome.toString());
			while (true) {
				if (responseHandler.getSensorObject(smarthome.getIp()) == null) {

				} else {
					sensor = responseHandler.getSensorObject(smarthome.getIp());
					System.out.println("senzorul este : " + sensor.toString());
					break;
				}
			}
			sensorRepository.updateSensor(sensor.getTemperature(), sensor.getHumidity(), sensor.getGasStatus(),
					sensor.getDoorStatus(), System.currentTimeMillis(), smarthome.getId());
			resultList.add(sensor);
		}
		return resultList;
	}

	public List<Sensor> getUpdatedSensorValuesFromDatabase() {
		return sensorRepository.getSensorValuesFromDatabase();
	}
	
	public Sensor getUpdatedSensorValuesFromDatabaseByHouseId(int houseId) {
		return sensorRepository.getSensorValuesByHouseId(houseId);
	}

}
