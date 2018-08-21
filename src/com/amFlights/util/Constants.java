package com.amFlights.util;

public class Constants {
	public static final String DB_CONNECTION = "DBConnection";
	

	//queries
	public static final String GET_BOOKINGS_BY_FLIGHT ="SELECT " + 
			" sb.booking_id," + 
			" b.flight_id," + 
			" b.booking_charges," + 
			" b.cancellation_charges," + 
			" b.is_meals_required," + 
			" s.seat_type," + 
			" b.is_cancelled," + 
			" GROUP_CONCAT(seat_number) AS seats" + 
			" FROM" + 
			" seat_booked sb" + 
			" INNER JOIN" + 
			" booking b ON sb.flight_id = ?" + 
			" AND b.booking_id = sb.booking_id" + 
			" INNER JOIN" + 
			" seat s ON s.seat_id = sb.seat_id" + 
			" GROUP BY sb.booking_id";
}
