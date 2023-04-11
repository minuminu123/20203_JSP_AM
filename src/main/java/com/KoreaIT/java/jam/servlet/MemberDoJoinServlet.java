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

import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		// DB 연결
		String url = "jdbc:mysql://127.0.0.1:3306/JSPAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		String user = "root";
		String password = "";
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("예외 : 클래스가 없습니다");
			System.out.println("프로그램을 종료합니다");
			return;
		}

		try {
			conn = DriverManager.getConnection(url, user, password);

			request.setCharacterEncoding("UTF-8");

			String loginId =(String) request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String name = request.getParameter("name");

//			if(!loginPw.equals(loginPwConfirm)) {
//				response.getWriter().append(String
//						.format("<script>alert('비밀번호가 일치하지 않습니다.'); location.replace('join');</script>"));
//			}
			SecSql sql = SecSql.from("INSERT INTO `member`");
			sql.append("SET regDate = NOW(),");
			sql.append("loginId = ?,", loginId);
			sql.append("loginPw = ?,", loginPw);
			sql.append("`name` = ?;", name);
			
//			SecSql sql2 = SecSql.from("SELECT * ");
//			sql2.append("FROM `member` ");
//			sql2.append("WHERE loginId = ?;", loginId);

			int id = DBUtil.insert(conn, sql);
//			Map<String, Object> idCheck = DBUtil.selectRow(conn, sql2);
//
//			if(idCheck.get("loginId").equals(loginId)) {
//				response.getWriter().append(String
//						.format("<script>alert('아이디가 중복됩니다.'); location.replace('join');</script>"));
//			}
			
			response.getWriter().append(String
					.format("<script>alert('%s님 회원가입이 완료되었습니다.'); location.replace('../article/list');</script>", name));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
