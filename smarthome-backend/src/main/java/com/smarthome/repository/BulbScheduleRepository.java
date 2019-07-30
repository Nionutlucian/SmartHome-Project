package com.smarthome.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.smarthome.model.BulbSchedule;

public interface BulbScheduleRepository extends JpaRepository<BulbSchedule,Integer> {

	@Transactional
	@Modifying
	@Query("delete from BulbSchedule b")
	public int deleteFromSchedule();
	
	@Query("select b from BulbSchedule b where b.bulbId = ?1")
    List<BulbSchedule> getBulbScheduleByBulbId(int bulbId);
	
	@Query("select b from BulbSchedule b")
    List<BulbSchedule> getAllSchedules();
	
}
