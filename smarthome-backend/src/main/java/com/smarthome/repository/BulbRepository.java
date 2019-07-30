package com.smarthome.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.smarthome.model.Bulb;


public interface BulbRepository extends JpaRepository<Bulb,Integer> {
	
	@Query("select b from Bulb b where b.houseId = ?1")
    List<Bulb> getAllBulbsByHouseId(int houseId);

	@Transactional
	@Modifying
	@Query("Update Bulb b SET b.state = ?1  WHERE b.ip= ?2")
	int updateBulbState(int state, String ip);
	
	@Transactional
	@Modifying
	@Query("Update Bulb b SET b.brightness = ?1  WHERE b.ip= ?2")
	int updateBulbBrightness(int brightness, String ip);
	
	@Transactional
	@Modifying
	@Query("Update Bulb b SET b.name = ?1  WHERE b.ip= ?2")
	int updateBulbName(String name, String ip);
}
