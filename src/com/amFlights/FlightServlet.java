package com.amFlights;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.amFlights.Model.Flight;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Flight
 */
@WebServlet("/Flight")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(LoginServlet.class);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set response content type
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		Connection con = (Connection) getServletContext().getAttribute("DBConnection");

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

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			out.print(gson.toJson(flightList));
			out.flush();
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
				logger.error("SQLException in closing PreparedStatement or ResultSet");
				
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
