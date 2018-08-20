package com.amFlights.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.amFlights.Model.Flight;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FlightUtil {
Connection con;

    public FlightUtil(Connection con) {
	super();
	this.con = con;
}

	public List<Flight> getFlights() throws Exception
    {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement("SELECT * FROM FLIGHT");
			rs = ps.executeQuery();

			List<Flight> flightList = new ArrayList<Flight>();

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int flight_id = rs.getInt("flight_id");
				String flight_name = rs.getString("flight_name");
				String economy_seat_price = rs.getString("economy_seat_price");
				String business_seat_price = rs.getString("business_seat_price");
				flightList.add(new Flight(flight_id, flight_name, economy_seat_price, business_seat_price));

			}

			return flightList;
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

}
