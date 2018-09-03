package com.amFlights.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(asyncSupported = true, urlPatterns = { "/AuthenticationFilter" })
public class AuthenticationFilter implements Filter {

	private Logger logger = Logger.getLogger(AuthenticationFilter.class);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		logger.info("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		if(session == null && !(uri.equals("/amFlights/") || uri.matches(".*(css|jpg|png|gif|js|html)"))){
			logger.error("Unauthorized access request");
			res.sendError(401, "Unauthorized");
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}	
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("AuthenticationFilter initialized");
	}

}
