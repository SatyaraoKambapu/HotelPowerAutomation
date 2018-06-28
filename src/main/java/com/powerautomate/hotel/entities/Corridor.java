package com.powerautomate.hotel.entities;

import com.powerautomate.hotel.CorridorType;
import com.powerautomate.hotel.observers.AirConditioner;
import com.powerautomate.hotel.observers.Fan;
import com.powerautomate.hotel.observers.Light;

public class Corridor {

	CorridorBuilder corridorBuilder;
/*
	public Corridor(CorridorType corridorType, int corridorNo, Light light, AirConditioner ac) {
		this.corridorType = corridorType;
		this.corridorNo = corridorNo;
		this.light = light;
		this.ac = ac;
	}

	public Corridor(CorridorType corridorType, int corridorNo) {
		this.corridorType = corridorType;
		this.corridorNo = corridorNo;
	}*/

	public static class CorridorBuilder {
		CorridorType corridorType;
		int corridorNo;
		Light light;
		AirConditioner ac;
		Fan fan;// Optional

		public CorridorBuilder(CorridorType corridorType, int corridorNo, Light light, AirConditioner ac) {
			this.corridorType = corridorType;
			this.corridorNo = corridorNo;
			this.light = light;
			this.ac = ac;
		}
		
		public CorridorBuilder(CorridorType corridorType, int corridorNo) {
			this.corridorType = corridorType;
			this.corridorNo = corridorNo;
		}

		public CorridorBuilder setFan(Fan fan) {
			this.fan = fan;
			return this;
		}
		
		public Corridor build(){
			return new Corridor(this);
		}

	}

	public double getTotalEnergyConsumed() {
		double necessary_resources_total_powerconsume = this.corridorBuilder.light.energyConsumed() + this.corridorBuilder.ac.energyConsumed();
		return this.corridorBuilder.fan != null ? necessary_resources_total_powerconsume + this.corridorBuilder.fan.energyConsumed() : necessary_resources_total_powerconsume;
	}

	public void displayStatus() {
		System.out.print(this.getCorridorType().toString() + " Corridor " + this.getCorridorNo());
		System.out.print("	Light :" + (this.corridorBuilder.light.isStatus() ? "ON" : "OFF"));
		System.out.print("	AC status:" + (this.corridorBuilder.ac.isStatus() ? "ON" : "OFF"));
		System.out.println();
	}

	/*public Corridor(int corridorNo) {
		this.corridorNo = corridorNo;
	}*/

	private Corridor(CorridorBuilder corridorBuilder) {
		this.corridorBuilder = corridorBuilder;
	}

	public CorridorType getCorridorType() {
		return corridorBuilder.corridorType;
	}

	public int getCorridorNo() {
		return corridorBuilder.corridorNo;
	}

	public Light getLight() {
		return corridorBuilder.light;
	}

	public AirConditioner getAc() {
		return corridorBuilder.ac;
	}
	
	public Fan getFan() {
		return corridorBuilder.fan;
	}
}
