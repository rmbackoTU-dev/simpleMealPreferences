package com.webAppDev.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;

public class SearchFood extends HttpServlet {

	public void doPost(HttpServletRequest request,
						HttpServletResponse response)
						throws IOException, ServletException
	{
	
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		out.println("Meals Found: <br>");
		String category=request.getParameter("meal-type");
		out.println("<br> Got meal category: "+category);
	}
}
