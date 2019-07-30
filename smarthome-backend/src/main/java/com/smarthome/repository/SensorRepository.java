package com.smarthome.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.smarthome.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor,Integer> {
	
	@Transactional
	@Modifying
	@Query("Update Sensor s SET s.temperature = ?1, s.humidity = ?2,"
			+ "s.gasStatus = ?3, s.doorStatus = ?4, s.timestamp = ?5  WHERE s.houseId= ?6")
	Integer updateSensor(int temperature, int humidity, 
								int gasStatus, int doorStatus,long timestamp,int id);
	
	@Query("select s from Sensor s")
    List<Sensor> getSensorValuesFromDatabase();
	
	@Query("select s from Sensor s WHERE s.houseId=?1")
	Sensor getSensorValuesByHouseId(int houseId);
	
}
