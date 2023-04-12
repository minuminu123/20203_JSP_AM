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

@WebServlet("/article/doDelete")
public class ArticleDoDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// DB 연결
		Connection conn = DB.conn(response);
		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMemberId") == null) {
			response.getWriter().append(
					String.format("<script>alert('로그인 후 이용해주세요'); location.replace('../member/login');</script>"));
			return;
		}

		

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());

			response.getWriter().append("Success!!!");

			int id = Integer.parseInt(request.getParameter("id"));

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("WHERE id = ? ;", id);

			Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

			int loginedMemberId = (int) session.getAttribute("loginedMemberId");

			if (loginedMemberId != (int) articleRow.get("memberId")) {
				response.getWriter().append(
						String.format("<script>alert('해당 게시글에 대한 권한이 없습니다'); location.replace('list');</script>", id));
				return;
			}

			sql = SecSql.from("DELETE");
			sql.append("FROM article");
			sql.append("WHERE id = ? ;", id);

			DBUtil.delete(conn, sql);

			response.getWriter()
					.append(String.format("<script>alert('%d번 글이 삭제되었습니다'); location.replace('list');</script>", id));

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