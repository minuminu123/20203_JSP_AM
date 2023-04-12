package com.KoreaIT.java.jam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = DB.conn(response);

		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			response.getWriter().append(
					String.format("<script>alert('로그인 후 이용해주세요'); location.replace('../member/login');</script>"));
			return;
		}

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());

			request.setCharacterEncoding("UTF-8");

			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int loginedMemberId = (int) session.getAttribute("loginedMemberId");

			SecSql sql = SecSql.from("INSERT INTO article");
			sql.append("SET regDate = NOW(),");
			sql.append("memberId = ?,", loginedMemberId);
			sql.append("title = ?,", title);
			sql.append("`body` = ?;", body);

			int id = DBUtil.insert(conn, sql);

			response.getWriter()
					.append(String.format("<script>alert('%d번 글이 생성되었습니다'); location.replace('list');</script>", id));

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