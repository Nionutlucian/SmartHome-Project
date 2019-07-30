package com.smarthome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smarthome.model.Smarthome;

public interface SmarthomeRepository extends JpaRepository<Smarthome,Integer> {
	
	 
	@Query("select s from Smarthome s")
    List<Smarthome> getAllHouses();
	
}
