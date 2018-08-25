package com.amFlights.util;

public class Constants {
	public static final String DB_CONNECTION = "DBConnection";
	
	public static final String SessionObject = "User";


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
			" seat s ON s.seat_id = sb.seat_id AND b.user_id = ?" + 
			" GROUP BY sb.booking_id ORDER BY b.is_cancelled ";
	
	public static final String CANCEL_BOOKING_BY_BOOKING_ID = "UPDATE booking " + 
			" INNER JOIN" + 
			" (SELECT " + 
			" COUNT(seat_id) * 200 AS cancellation_price" + 
			" FROM" + 
			" booking b" + 
			" INNER JOIN seat_booked s ON b.booking_id = s.booking_id" + 
			" AND b.booking_id = ?) as cancellation " + 
			" SET " + 
			" cancellation_charges = cancellation.cancellation_price," + 
			" is_cancelled = TRUE" + 
			" WHERE" + 
			" booking_id = ?;";
}
