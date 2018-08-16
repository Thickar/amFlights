package com.amFlights.user;

import java.io.BufferedReader;
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

import com.amFlights.LoginServlet;
import com.amFlights.Model.Flight;
import com.amFlights.Model.Seat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class SeatServlet
 */
@WebServlet(asyncSupported = true, name = "Seat", urlPatterns = { "/Seat" })
public class SeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginServlet.class);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeatServlet() {
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
		response.setContentType("application/json");

		int flight_id = Integer.valueOf(request.getParameter("flight_id"));
		int seat_type = Integer.valueOf(request.getParameter("seat_type"));
		String errorMsg = null;
		if (flight_id < 1) {
			errorMsg = "flight id can't be 0 or empty";
		}
		if (errorMsg != null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, errorMsg);
//				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
//				rd.include(request, response);
		} else {

			// Set response content type
//			response.setContentType("application/text");
			PrintWriter out = response.getWriter();

			Connection con = (Connection) getServletContext().getAttribute("DBConnection");

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {

				ps = con.prepareStatement(
						"Select count(seat_id) as seatCount from seat where seat_type = ? and seat_id not in (SELECT s.seat_id from booking b inner join seat_booked s on b.booking_id = s.booking_id and b.is_cancelled = false where b.flight_id = ?)");
				ps.setInt(1, seat_type);
				ps.setInt(2, flight_id);
				rs = ps.executeQuery();

				if(rs.next())
				{
					int seatCount = rs.getInt("seatCount");
					if(seatCount > 0)
					{
						out.println(seatCount);
					}else {
						response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED,"Seats fulll");
					}
				}
				
/**				List<Seat> seatList = new ArrayList<Seat>();

				// Extract data from result set
				while (rs.next()) {
					// Retrieve by column name
					int seat_id = rs.getInt("seat_id");
					int seat_type = rs.getInt("seat_type");
					int seat_number = rs.getInt("seat_number");

					seatList.add(new Seat(seat_id, seat_type, seat_number));

				}

				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				out.print(gson.toJson(seatList));
				out.flush();
				*/
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
