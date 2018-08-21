package com.amFlights.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.amFlights.model.Booking;
import com.amFlights.model.Flight;
import com.amFlights.model.User;
import com.amFlights.util.BookingUtil;
import com.amFlights.util.Constants;
import com.amFlights.util.FlightUtil;
import com.amFlights.util.SeatUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * Servlet implementation class Booking
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Booking" })
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(BookingServlet.class);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Default constructor.
	 */
	public BookingServlet() {
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
		PrintWriter out = response.getWriter();
		int flightId = Integer.valueOf(request.getParameter("flightId"));

		Connection con = (Connection) getServletContext().getAttribute(Constants.DB_CONNECTION);

		try {
			BookingUtil bookingUtil = new BookingUtil(con);
			List<Booking> bookingList = bookingUtil.getBookings(flightId, false);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			out.print(gson.toJson(bookingList));
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
		JsonParser parser = new JsonParser();
		BufferedReader reader = request.getReader();

		JsonObject jsonObject = (JsonObject) parser.parse(reader);
		response.setContentType("application/json");

		int seatCount = jsonObject.get("seatCount").getAsInt();
		int flightId = jsonObject.get("flightId").getAsInt();
		int seatType = jsonObject.get("seatType").getAsInt();
		Boolean isMealRequired = jsonObject.get("isMealRequired").getAsBoolean();

		String errorMsg = null;
		if (seatCount < 1) {
			errorMsg += "seat count must be greater than zero";
		}
		if (flightId < 1) {
			errorMsg += "flightId must be greater than zero";
		}

		if (errorMsg != null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, errorMsg);
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
//			rd.include(request, response);
		} else {

			// Set response content type
			response.setContentType("application/text");
			PrintWriter out = response.getWriter();

			Connection con = (Connection) getServletContext().getAttribute(Constants.DB_CONNECTION);

			try {

				int availableSeatCount = new SeatUtil(con).getAvailableSeatCount(flightId, seatType);
				if (availableSeatCount >= seatCount) {				
					int bookingId = new BookingUtil(con).bookTickets(flightId, seatType, seatCount, isMealRequired);
					out.print(bookingId);
				} else {
					response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Seats full");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
