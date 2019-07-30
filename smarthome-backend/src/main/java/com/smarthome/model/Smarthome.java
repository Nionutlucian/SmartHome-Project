package com.smarthome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smarthome")
public class Smarthome {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	 
	@Column(unique = true)
	private int userId;

	@Column(unique = true)
	private String ip;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "Smarthome [id=" + id + ", userId=" + userId + ", ip=" + ip + "]";
	}
	
	
}
