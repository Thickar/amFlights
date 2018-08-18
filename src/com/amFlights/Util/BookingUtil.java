package com.amFlights.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.amFlights.Model.Booking;
import com.amFlights.Model.User;

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

	public Boolean bookTickets(int flight_id, int seat_type, int seat_count, Boolean isMealRequired) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("CALL booktickets(?,?,?,?)");
			ps.setInt(1, flight_id);
			ps.setInt(2, seat_type);
			ps.setInt(3, seat_count);
			ps.setInt(4, isMealRequired ? 1 : 0);

			rs = ps.executeQuery();

			return rs.next();
			
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

}
