package com.smarthome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensor")
public class Sensor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(unique = false)
	private int temperature;
	
	@Column(unique = false)
	private int humidity;
	
	@Column(unique = false)
	private int gasStatus;
	
	@Column(unique = false)
	private int doorStatus;
	
	@Column(unique = false)
	private long timestamp;
	
	@Column(unique = true)
	private int houseId;
	
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public  int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public int getGasStatus() {
		return gasStatus;
	}
	public void setGasStatus(int gasStatus) {
		this.gasStatus = gasStatus;
	}
	public int getDoorStatus() {
		return doorStatus;
	}
	public void setDoorStatus(int doorStatus) {
		this.doorStatus = doorStatus;
	}
	@Override
	public String toString() {
		return "Sensor [id=" + id + ", temperature=" + temperature + ", humidity=" + humidity + ", gasStatus="
				+ gasStatus + ", doorStatus=" + doorStatus + ", timestamp=" + timestamp + ", houseId=" + houseId + "]";
	}
	
	
}
