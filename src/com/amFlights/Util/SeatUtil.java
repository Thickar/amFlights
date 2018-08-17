package com.amFlights.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class SeatUtil {
	Connection con;

	public SeatUtil(Connection con) {
		super();
		this.con = con;
	}
	
	public int getAvailableSeatCount(int flightId,int seatType) throws ServletException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement(
					"Select count(seat_id) as seatCount from seat where seat_type = ? and seat_id not in (SELECT s.seat_id from booking b inner join seat_booked s on b.booking_id = s.booking_id and b.is_cancelled = false where b.flight_id = ?)");
			ps.setInt(1, seatType);
			ps.setInt(2, flightId);
			rs = ps.executeQuery();

			if(rs.next())
			{
				return rs.getInt("seatCount");
			}else {
				return 0;
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				throw new ServletException("SQLException in closing PreparedStatement or ResultSet");

			}

		}
		return 0;
	}

}
