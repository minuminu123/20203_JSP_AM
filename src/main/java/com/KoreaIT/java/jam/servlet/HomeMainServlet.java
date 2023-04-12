package com.KoreaIT.java.jam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.exception.SQLErrorException;
import com.KoreaIT.java.jam.util.DB;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = DB.conn(response);

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());

			request.setCharacterEncoding("UTF-8");

			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String, Object> loginedMemberRow = null;

			if (session.getAttribute("loginedMemberId") != null) {
				isLogined = true;
				loginedMemberId = (int) session.getAttribute("loginedMemberId");

				SecSql sql = SecSql.from("SELECT * FROM `member`");
				sql.append("WHERE id = ?", loginedMemberId);

				loginedMemberRow = DBUtil.selectRow(conn, sql);
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMemberRow", loginedMemberRow);

			request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}