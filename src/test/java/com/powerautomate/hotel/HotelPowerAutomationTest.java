package com.powerautomate.hotel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.powerautomate.hotel.entities.Hotel;
import com.powerautomate.hotel.subject.SensorRequest;
import com.powerautomate.hotel.subject.singleton.SensorHandler;

public class HotelPowerAutomationTest {
	
	// Careful : This is Singleton Object creation, one instance per JVM application.
	SensorHandler handler = null;
	@Before
	public void setUp(){
		handler = SensorHandler.getInstance();
	}
	
	@After
	public void cleanUp(){
		
	}
	
	@Test
	public void testHotelPowerSupplyAutomation(){
		int noOfFloors = 3;
		int noOfMainCorrs = 2;
		int noOfSubCorrs = 3;
		Hotel hotel = new Hotel.HotelBuilder("HAYAT", noOfFloors, noOfMainCorrs, noOfSubCorrs).build();
		hotel.displayStatus();
		boolean movementFound = false;
		int floorNo = 2;
		int corrNo = 3;
		SensorRequest sensorRequest = new SensorRequest(movementFound, floorNo, CorridorType.SUB, corrNo);
		handler.handleSensorAndPowerOtimize(sensorRequest, hotel);
	}

}
