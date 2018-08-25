package com.amFlights.servlet;

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

import com.amFlights.model.Flight;
import com.amFlights.util.Constants;
import com.amFlights.util.FlightUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Flight
 */
@WebServlet("/api/Flight")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(FlightServlet.class);
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

		Connection con = (Connection) getServletContext().getAttribute(Constants.DB_CONNECTION);

		try {
			FlightUtil flightUtil = new FlightUtil(con);
			List<Flight> flightList = flightUtil.getFlights();

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			out.print(gson.toJson(flightList));
			out.flush();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
