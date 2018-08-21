package com.amFlights.model;

public class Booking {
	private int bookingId;
	private int flightId;
	private int bookingCharges;
	private int cancellationCharges;
	private Boolean isCancelled;
	private Boolean isMealsRequired;
	private String seats;
	private int seatType;
	public Booking(int bookingId, int flightId, int bookingCharges, int cancellationCharges, Boolean isCancelled,
			Boolean isMealsRequired, String seats, int seatType) {
		super();
		this.bookingId = bookingId;
		this.flightId = flightId;
		this.bookingCharges = bookingCharges;
		this.cancellationCharges = cancellationCharges;
		this.isCancelled = isCancelled;
		this.isMealsRequired = isMealsRequired;
		this.seats = seats;
		this.seatType = seatType;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getBookingCharges() {
		return bookingCharges;
	}
	public void setBookingCharges(int bookingCharges) {
		this.bookingCharges = bookingCharges;
	}
	public int getCancellationCharges() {
		return cancellationCharges;
	}
	public void setCancellationCharges(int cancellationCharges) {
		this.cancellationCharges = cancellationCharges;
	}
	public Boolean getIsCancelled() {
		return isCancelled;
	}
	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	public Boolean getIsMealsRequired() {
		return isMealsRequired;
	}
	public void setIsMealsRequired(Boolean isMealsRequired) {
		this.isMealsRequired = isMealsRequired;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public int getSeatType() {
		return seatType;
	}
	public void setSeatType(int seatType) {
		this.seatType = seatType;
	}

}
