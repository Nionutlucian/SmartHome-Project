package com.smarthome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(unique = false)
	private int doorNotificationPhone;
	
	@Column(unique = false)
	private int doorNotificationEmail;
	
	@Column(unique = false)
	private int gasNotificationPhone;
	
	@Column(unique = false)
	private int gasNotificationEmail;
	
	@Column(unique = true)
	private int houseId;
	
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoorNotificationPhone() {
		return doorNotificationPhone;
	}
	public void setDoorNotificationPhone(int doorNotificationPhone) {
		this.doorNotificationPhone = doorNotificationPhone;
	}
	public int getDoorNotificationEmail() {
		return doorNotificationEmail;
	}
	public void setDoorNotificationEmail(int doorNotificationEmail) {
		this.doorNotificationEmail = doorNotificationEmail;
	}
	public int getGasNotificationPhone() {
		return gasNotificationPhone;
	}
	public void setGasNotificationPhone(int gasNotificationPhone) {
		this.gasNotificationPhone = gasNotificationPhone;
	}
	public int getGasNotificationEmail() {
		return gasNotificationEmail;
	}
	public void setGasNotificationEmail(int gasNotificationEmail) {
		this.gasNotificationEmail = gasNotificationEmail;
	}
	
}
