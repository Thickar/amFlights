package com.amFlights.model;

public class Booking {
	private int booking_id;
	private int flight_id;
	private int booking_charges;
	private int cancellation_charges;
	private Boolean is_cancelled;
	private Boolean is_meals_required;
	private String seats;
	public int getBooking_id() {
		return booking_id;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	
	public int getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}
	public int getBooking_charges() {
		return booking_charges;
	}
	public void setBooking_charges(int booking_charges) {
		this.booking_charges = booking_charges;
	}
	public int getCancellation_charges() {
		return cancellation_charges;
	}
	public void setCancellation_charges(int cancellation_charges) {
		this.cancellation_charges = cancellation_charges;
	}
	public Boolean getIs_cancelled() {
		return is_cancelled;
	}
	public void setIs_cancelled(Boolean is_cancelled) {
		this.is_cancelled = is_cancelled;
	}
	public Boolean getIs_meals_required() {
		return is_meals_required;
	}
	public void setIs_meals_required(Boolean is_meals_required) {
		this.is_meals_required = is_meals_required;
	}
}
