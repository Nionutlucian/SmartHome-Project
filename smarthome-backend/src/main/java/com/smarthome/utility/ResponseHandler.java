package com.smarthome.utility;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;

import com.smarthome.model.Sensor;

public class ResponseHandler {

	public static final String HOUSE_IP = "http://192.168.43.221";
	
	private HttpClient client = new HttpClient();
	private String responseToHandle;
	private Sensor sensor;

	public String getSensorValues(String houseIp) throws ClientProtocolException, IOException, HttpException {
		responseToHandle = client.GETRequest(houseIp);
		System.out.println("AICI: " + StringUtils.substringBetween(responseToHandle, "<div>", "</div>"));
		return StringUtils.substringBetween(responseToHandle, "<div>", "</div>");
	}

	public Sensor getSensorObject(String houseIp) throws Exception {
		String response = null;
		sensor = new Sensor();
		while (true) {
			response = getSensorValues(houseIp);
			if (response == null) {

			} else {
				break;
			}
		}

		int temperature = Integer.valueOf(response.substring(0, 2));
		System.out.println(temperature);
		int door = Integer.valueOf(response.substring(5, 6));
		System.out.println(door);
		int gas = Integer.valueOf(response.substring(6, 7));
		System.out.println(gas);
		int humidity = Integer.valueOf(response.substring(7, 9));
		System.out.println(humidity);
		sensor.setTemperature(temperature);
		sensor.setDoorStatus(door);
		sensor.setGasStatus(gas);
		sensor.setHumidity(humidity);
		return sensor;

	}
}
