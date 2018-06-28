package com.powerautomate.hotel.subject.singleton;

import com.powerautomate.hotel.entities.Corridor;
import com.powerautomate.hotel.entities.Hotel;
import com.powerautomate.hotel.subject.SensorRequest;
import com.powerautomate.hotel.subject.SensorSystem;

public class SensorHandler {
	/**
	 * Here, We use Observer design pattern, So here SensorSystem is subject and
	 * Lights and AC are Observers, so registering the observers to subject to
	 * capture the subect's behavior and change the Observers status
	 * accordingly.
	 * 
	 * @param sensorRequest
	 * @param hotel
	 */
	private static SensorHandler instance = null;

	/**
	 * This Class is Singleton design pattern.
	 */
	private SensorHandler() {

	}

	public static SensorHandler getInstance() {
		if (instance == null) {
			synchronized (SensorHandler.class) {
				if (instance == null) {
					instance = new SensorHandler();
				}
			}
		}
		return instance;
	}

	private SensorSystem registerObservers(SensorRequest sensorRequest, Hotel hotel) {
		SensorSystem subject = new SensorSystem(sensorRequest, hotel);
		Corridor corr = hotel.getFloor(sensorRequest.getFloorNo()).getCorridor(sensorRequest.getCorridorType(),
				sensorRequest.getCorridorNo());
		if(corr != null){
			subject.addObserver(corr.getLight());
			subject.addObserver(corr.getAc());
			subject.addObserver(corr.getFan());
		}else{
			System.err.println("No Such corridor Exist!! , Try for other sub corridor");
			System.exit(0);
		}
		return subject;
	}
	

	public void handleSensorAndPowerOtimize(SensorRequest sensorRequest, Hotel hotel) {
		SensorSystem subject = registerObservers(sensorRequest, hotel);
		subject.handleSensorRequest();
		hotel.displayStatus();
	}


}
