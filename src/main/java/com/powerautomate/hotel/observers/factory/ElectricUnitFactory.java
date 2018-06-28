package com.powerautomate.hotel.observers.factory;

import java.util.Observer;

import com.powerautomate.hotel.Constants;
import com.powerautomate.hotel.observers.AirConditioner;
import com.powerautomate.hotel.observers.Fan;
import com.powerautomate.hotel.observers.Light;

/**
 * Used Factory Deisgn Pattern to Create the Electric Unit Observers.
 * 
 * @author skambapu
 *
 */
public class ElectricUnitFactory {

	public static Observer createElectriUnit(String electricUnit) {
		Observer electriUnit = null;
		if (Constants.LIGHT.equals(electricUnit)) {
			electriUnit = new Light(Constants.DEFAULT_LIGHT_ENERGY);
		} else if (Constants.AC.equals(electricUnit)) {
			electriUnit = new AirConditioner(Constants.DEFAULT_AC_ENERGY);
		} else if (Constants.FAN.equals(electricUnit)) {
			electriUnit = new Fan(Constants.DEFAULT_FAN_ENERGY);
		}
		return electriUnit;
	}
}
