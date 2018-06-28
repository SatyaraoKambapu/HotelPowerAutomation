package com.powerautomate.hotel.observers;

import java.util.Observable;
import java.util.Observer;

import com.powerautomate.hotel.subject.SensorSystem;

public class Fan implements Observer {

	boolean status;
	double powerConsumptionPerHour;

	public Fan(double powerConsumptionPerHour) {
		this.powerConsumptionPerHour = powerConsumptionPerHour;
	}

	public double energyConsumed() {
		return this.status ? this.powerConsumptionPerHour : 0;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public void update(Observable o, Object status) {
		Boolean isMovement = ((SensorSystem) o).getSensorReq().isMovement();
		if (!isMovement) {
			setStatus(Boolean.FALSE);
		} else {
			setStatus(Boolean.TRUE);
		}
	}

}
