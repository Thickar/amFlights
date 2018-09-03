package com.amFlights.servlet;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.amFlights.model.User;
import com.amFlights.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(LoginServlet.class);
	  Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			User user = (User) session.getAttribute(Constants.SessionObject);
			response.getWriter().print(gson.toJson(user));
		}else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        JsonParser parser = new JsonParser();
        BufferedReader reader = request.getReader();

        JsonObject jsonObject = (JsonObject) parser.parse(reader);
		response.setContentType("application/json");
		HttpSession session = request.getSession(true);

		String username = jsonObject.get("email").getAsString();
		String password = jsonObject.get("password").getAsString();
		String errorMsg = null;
		if(username == null || username.equals("")){
			errorMsg ="User Email can't be null or empty";
		}
		if(password == null || password.equals("")){
			errorMsg += "Password can't be null or empty";
		}
		
		if(errorMsg != null){
			response.sendError(HttpServletResponse.SC_NOT_FOUND,errorMsg);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute(Constants.DB_CONNECTION);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select user_type,username,email,user_id from user where email= ? and password= ? limit 1");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs != null && rs.next()){
				
				User user = new User(rs.getString("username"), rs.getString("email"), rs.getInt("user_type"),rs.getInt("user_id"));
				logger.info("User found with details="+user.getUsername());
				session.setAttribute(Constants.SessionObject, user);
				response.getWriter().print(gson.toJson(user));
			}else{
				logger.error("User not found with email="+username);
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement or ResultSet");;
			}
			
		}
		}
	}
	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
