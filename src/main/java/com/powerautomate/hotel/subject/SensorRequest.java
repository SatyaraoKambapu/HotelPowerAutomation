package com.powerautomate.hotel.subject;

import com.powerautomate.hotel.CorridorType;

public class SensorRequest {

	boolean isMovement;
	int floorNo;
	CorridorType corridorType;
	int corridorNo;

	public SensorRequest(boolean isMovement, int floorNo, CorridorType corridorType, int corridorNo) {
		this.isMovement = isMovement;
		this.floorNo = floorNo;
		this.corridorType = corridorType;
		this.corridorNo = corridorNo;
	}

	public boolean isMovement() {
		return isMovement;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public CorridorType getCorridorType() {
		return corridorType;
	}

	public int getCorridorNo() {
		return corridorNo;
	}

	public String toString() {
		return "\nMovement:" + this.isMovement + "\nFloorNo:" + this.floorNo + "\nCorridorType:" + this.corridorType
				+ "\nCorrNo:" + this.corridorNo;
	}

}
