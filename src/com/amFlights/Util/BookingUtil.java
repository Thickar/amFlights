package com.amFlights.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.amFlights.model.Booking;
import com.amFlights.model.Flight;
import com.amFlights.model.User;

public class BookingUtil {

	Connection con;
	Logger logger;

	public BookingUtil(Connection con) {
		super();
		this.con = con;
	}

	public int calculateBookingCost(int flight_id, int seat_type, int seat_count) throws Exception {
		return seat_count * getSeatPrice(flight_id, seat_type);
	}

	public int getSeatPrice(int flight_id, int seat_type) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuilder query = new StringBuilder("select ");
			query.append(seat_type == 0 ? "business_seat_price " : "business_seat_price ");
			query.append("as seat_price from flight where flight_id = ?");
			ps = con.prepareStatement(query.toString());
			ps.setInt(1, flight_id);

			rs = ps.executeQuery();

			if (rs != null && rs.next()) {
				return rs.getInt("seat_price");
			} else {
				throw new Exception("Not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				throw new ServletException("SQLException in closing PreparedStatement or ResultSet");

			}

		}
	}

	
	public List<Booking> getBookings(int flightId,Boolean cancelled) throws ServletException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement(Constants.GET_BOOKINGS_BY_FLIGHT);
			ps.setInt(1, flightId);
			rs = ps.executeQuery();

			List<Booking> bookingList = new ArrayList<Booking>();

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int booking_id = rs.getInt("booking_id");
				int flight_id = rs.getInt("flight_id");
				int booking_charges = rs.getInt("booking_charges");
				Boolean is_meals_required = rs.getInt("is_meals_required") == 1 ? true : false;
				Boolean is_cancelled = rs.getInt("is_cancelled") == 1 ? true : false;
				String seats = rs.getString("seats");
				int seat_type = rs.getInt("seat_type");
				int cancellation_charges = rs.getInt("cancellation_charges");

				bookingList.add(new Booking(booking_id, flight_id, booking_charges, cancellation_charges, is_cancelled, is_meals_required, seats, seat_type));
			}

			return bookingList;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				throw new ServletException("SQLException in closing PreparedStatement or ResultSet");				
			}

		}
	}
	
	public int bookTickets(int flight_id, int seat_type, int seat_count, Boolean isMealRequired) throws Exception {
		CallableStatement callableStatement = null;
		try {
			callableStatement =  con.prepareCall("{ CALL booktickets(?,?,?,?,?) }");
			callableStatement.setInt(1, flight_id);
			callableStatement.setInt(2, seat_type);
			callableStatement.setInt(3, seat_count);
			callableStatement.setInt(4, isMealRequired ? 1 : 0);
			callableStatement.registerOutParameter(5,java.sql.Types.INTEGER);

			callableStatement.executeUpdate();
			
			return callableStatement.getInt(5);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("DB Connection problem.");
		} finally {
			try {
				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (SQLException e) {
				throw new ServletException("SQLException in closing PreparedStatement or ResultSet");

			}

		}
	}
    
}
