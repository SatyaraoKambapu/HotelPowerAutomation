package com.powerautomate.hotel;

import java.util.List;

import com.powerautomate.hotel.entities.Corridor;
import com.powerautomate.hotel.entities.Floor;
import com.powerautomate.hotel.entities.Hotel;
import com.powerautomate.hotel.subject.SensorSystem;

public class PowerOptimizer {

	/**
	 * Optimization of power based on threshold power and energy consumed
	 * 
	 * sub corridor's acs are switched on/off for optimization
	 * 
	 * @param hotel
	 * @param floorNo
	 */
	public static void optimize(SensorSystem subject) {

		Hotel hotel = subject.getHotel();
		int floorNo = subject.getSensorReq().getFloorNo();
		Floor floor = hotel.getFloor(floorNo);
		if (floor != null) {
			List<Corridor> subCorridorList = floor.getCorridorList(CorridorType.SUB);

			double thresholdPower = (floor.getCorridorList(CorridorType.MAIN).size() * Constants.MAINPOWER)
					+ (floor.getCorridorList(CorridorType.SUB).size() * Constants.SUBPOWER);

			double totalEnergyConsumed = floor.getTotalEnergyConsumed();
			for (Corridor corridor : subCorridorList) {
				if (totalEnergyConsumed > thresholdPower) {
					if (corridor.getAc().isStatus()
							&& (subject.getSensorReq().getCorridorNo() != corridor.getCorridorNo())) {
						corridor.getAc().setStatus(Boolean.FALSE);
						totalEnergyConsumed = floor.getTotalEnergyConsumed();
					}
					if (totalEnergyConsumed <= thresholdPower)
						break;
				} else if (thresholdPower - totalEnergyConsumed >= Constants.DEFAULT_AC_ENERGY) {
					if (!corridor.getAc().isStatus()) {
						corridor.getAc().setStatus(Boolean.TRUE);
						totalEnergyConsumed = floor.getTotalEnergyConsumed();
					}
					if (totalEnergyConsumed >= thresholdPower)
						break;
				}
			}
		}else{
			System.err.println("No such Floor or corridor Exist!!!:-(");
		}
	}

}
