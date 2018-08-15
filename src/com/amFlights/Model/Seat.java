package com.amFlights.Model;

public class Seat {
	private int seat_id;
	private int seat_number;
	private int seat_type;
	public Seat(int seat_id, int seat_number, int seat_type) {
		super();
		this.seat_id = seat_id;
		this.seat_number = seat_number;
		this.seat_type = seat_type;
	}
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getSeat_number() {
		return seat_number;
	}
	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}
	public int getSeat_type() {
		return seat_type;
	}
	public void setSeat_type(int seat_type) {
		this.seat_type = seat_type;
	}
}
