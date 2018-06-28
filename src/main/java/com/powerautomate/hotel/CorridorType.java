package com.powerautomate.hotel;

public enum CorridorType {

	MAIN("MAIN"), SUB("SUB");

	private String corridorType;

	CorridorType(String corridorType) {
		this.corridorType = corridorType;
	}

	public String getCorridorType() {
		return corridorType;
	}

	public static CorridorType getCorridor(String corridorType) {
		if (corridorType == null)
			return null;

		for (CorridorType corrType : CorridorType.values()) {
			return corrType.getCorridorType().equalsIgnoreCase(corridorType) ? corrType : null;
		}
		return null;
	}

}
