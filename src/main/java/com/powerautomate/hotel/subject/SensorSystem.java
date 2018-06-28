package com.powerautomate.hotel.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.powerautomate.hotel.PowerOptimizer;
import com.powerautomate.hotel.entities.Corridor;
import com.powerautomate.hotel.entities.Hotel;

/**
 * Here, We use Observer design pattern, So here SensorSystem is subject and
 * Lights and AC are Observers, so registering / notifying the observers to
 * subject to capture the subect's behavior and change the Observers status
 * accordingly.
 * 
 * @param sensorRequest
 * @param hotel
 */
public class SensorSystem extends Observable {

	// Define a one-to-many dependency between objects so that when one object
	// changes state, all its dependents are notified and updated automatically.
	private List<Observer> observers = new ArrayList<Observer>();

	private SensorRequest sensorReq;
	private Hotel hotel;

	public SensorSystem(SensorRequest sensorReq, Hotel hotel) {
		this.sensorReq = sensorReq;
		this.hotel = hotel;
	}

	@Override
	public void addObserver(Observer observer) {
		if (observer == null)
			throw new NullPointerException("Null Observer");
		observers.add(observer);
	}

	@Override
	public void deleteObserver(Observer observer) {
		observers.remove(observer);

	}

	@Override
	public void notifyObservers() {
		for (Observer ob : observers) {
			ob.update(this, null);
		}
	}

	/**
	 * based on motion, sitch on/off light and call powerOptimizer
	 * 
	 * @param sensorReq
	 * @param hotel
	 */
	public void handleSensorRequest() {
		Corridor corr = hotel.getFloor(sensorReq.getFloorNo()).getCorridor(sensorReq.getCorridorType(),
				sensorReq.getCorridorNo());

		if (!sensorReq.isMovement()) {
			notifyObservers();
			PowerOptimizer.optimize(this);
		} else {
			if (!corr.getLight().isStatus()) {
				notifyObservers();
				PowerOptimizer.optimize(this);
			}
		}
		setChanged();
	}

	public SensorRequest getSensorReq() {
		return sensorReq;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public List<Observer> getObservers() {
		return observers;
	}
}
