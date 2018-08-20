package com.amFlights.model;

public class Flight {
	private int flight_id;
	private String flight_name;
	private String economy_seat_price;
	private String business_seat_price;
	
	public Flight(int flight_id, String flight_name, String economy_seat_price, String business_seat_price) {
		super();
		this.flight_id = flight_id;
		this.flight_name = flight_name;
		this.economy_seat_price = economy_seat_price;
		this.business_seat_price = business_seat_price;
	}
	public int getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}
	public String getFlight_name() {
		return flight_name;
	}
	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}
	public String getEconomy_seat_price() {
		return economy_seat_price;
	}
	public void setEconomy_seat_price(String economy_seat_price) {
		this.economy_seat_price = economy_seat_price;
	}
	public String getBusiness_seat_price() {
		return business_seat_price;
	}
	public void setBusiness_seat_price(String business_seat_price) {
		this.business_seat_price = business_seat_price;
	}

}
