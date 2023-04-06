package com.KoreaIT.java.jam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home/main/printDan")
public class HomeMainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Hello World! sfd ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
		String inputedDan = request.getParameter("dan");
		String inputedLimit = request.getParameter("limit");
		
		if(inputedDan == null) {
			inputedDan = "1";
		}
		
		if(inputedLimit == null) {
			inputedLimit = "1";
		}
		
		int i = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedLimit);

		for (int j = 1; j <= limit; j++) {
			
			response.getWriter().append(String.format("%d * %d = %d<br>", i,j,i*j));
		}
		
	}

}
