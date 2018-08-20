package com.amFlights.Model;

import java.util.List;

public class FlightSummary {
	Flight flight;
	List<Booking> BookingList;

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public List<Booking> getBookingList() {
		return BookingList;
	}

	public void setBookingList(List<Booking> bookingList) {
		BookingList = bookingList;
	}

}
