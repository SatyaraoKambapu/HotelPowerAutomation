package com.powerautomate.hotel.entities;

import java.util.ArrayList;
import java.util.List;

import com.powerautomate.hotel.Constants;
import com.powerautomate.hotel.CorridorType;
import com.powerautomate.hotel.observers.AirConditioner;
import com.powerautomate.hotel.observers.Fan;
import com.powerautomate.hotel.observers.Light;
import com.powerautomate.hotel.observers.factory.ElectricUnitFactory;

/**
 * Used Builder Design Pattern to Build the Hotel.
 * 
 * @author skambapu
 *
 */
public class Hotel {

	String hotelName;
	List<Floor> floors = new ArrayList<Floor>();
	HotelBuilder hotelBuilder;

	private Hotel(HotelBuilder hotelBuilder) {
		this.floors = hotelBuilder.floors;
		this.hotelName = hotelBuilder.hotelName;
	}

	public String getHotelName() {
		return hotelName;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public Floor getFloor(int floorId) {
		return this.getFloors().stream().filter(floor -> floor.getFloorNo() == floorId).findAny().orElse(null);
	}

	public void displayStatus() {
		for (Floor floor : this.getFloors()) {
			System.out.println("Floor No:" + floor.getFloorNo());
			for (Corridor corridor : floor.getCorridorList()) {
				corridor.displayStatus();
			}
		}
	}

	public double getTotalEnergyConsumption() {
		double energy = 0.0;
		for (Floor floor : this.getFloors()) {
			energy += floor.getCorridorList().stream().mapToDouble(corr -> corr.getTotalEnergyConsumed()).sum();
		}
		return energy;
	}

	public int floorCount() {
		return this.floors.size();
	}

	public long getTotalCorrCountByType(CorridorType corrType) {
		long totalCorr = 0l;
		for (Floor floor : this.getFloors()) {
			totalCorr += floor.getCorridorList().stream().filter(corr -> corr.getCorridorType() == corrType).count();
		}
		return totalCorr;
	}

	public long getTotalCorrCount(CorridorType corrType) {
		long totalCorr = 0l;
		for (Floor floor : this.getFloors()) {
			totalCorr += floor.getCorridorList().stream().count();
		}
		return totalCorr;
	}

	public static class HotelBuilder {
		String hotelName;
		List<Floor> floors = new ArrayList<Floor>();
		private int noOfFloors;
		private int noOfMainCorrs;
		private int noOfSubCorrs;
		private int noOfParkingAreas; // Optional field

		public HotelBuilder(String hotelName, int noOfFloors, int noOfMainCorrs, int noOfSubCorrs) {
			this.hotelName = hotelName;
			this.noOfFloors = noOfFloors;
			this.noOfMainCorrs = noOfMainCorrs;
			this.noOfSubCorrs = noOfSubCorrs;
		}
		
		public HotelBuilder setNoOfParkingAreas(int noOfParkingAreas){
			this.noOfParkingAreas = noOfParkingAreas;
			return this;
		}

		public Hotel build() {
			List<Floor> floorList = floorBuilder();
			this.floors = floorList;
			return new Hotel(this);
		}

		private List<Floor> floorBuilder() {
			Light light = null;
			AirConditioner ac = null;
			List<Floor> floorList = new ArrayList<>();
			Fan fan=null;

			for (int index = 1; index <= noOfFloors; index++) {
				List<Corridor> corrList = new ArrayList<>();
				for (int i = 1; i <= noOfMainCorrs; i++) {
					light = (Light) ElectricUnitFactory.createElectriUnit(Constants.LIGHT);
					light.setStatus(true);
					ac = (AirConditioner) ElectricUnitFactory.createElectriUnit(Constants.AC);
					ac.setStatus(true);
					corrList.add(new Corridor.CorridorBuilder(CorridorType.MAIN, i, light, ac).build());
				}

				for (int j = 1; j <= noOfSubCorrs; j++) {
					light = (Light) ElectricUnitFactory.createElectriUnit(Constants.LIGHT);
					ac = (AirConditioner) ElectricUnitFactory.createElectriUnit(Constants.AC);
					ac.setStatus(true);
					fan = (Fan) ElectricUnitFactory.createElectriUnit(Constants.FAN);
					corrList.add(new Corridor.CorridorBuilder(CorridorType.SUB, j, light, ac).setFan(fan).build());
				}

				floorList.add(new Floor(index, corrList));
			}
			return floorList;
		}

	}

}
