package com.powerautomate.hotel.observers;

import java.util.Observable;
import java.util.Observer;

import com.powerautomate.hotel.subject.SensorSystem;

/**
 * Here, We use Observer design pattern, So here SensorSystem is subject and
 * Lights and AC are Observers,
 */
public class AirConditioner implements Observer {

	boolean status;
	double powerConsumptionPerHour;

	public AirConditioner(double powerConsumptionPerHour) {
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
	public void update(Observable o, Object arg) {
		Boolean isMovement = ((SensorSystem) o).getSensorReq().isMovement();
		if (!isMovement) {
			setStatus(Boolean.FALSE);
		} else {
			setStatus(Boolean.TRUE);
		}
	}
}
