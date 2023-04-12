package com.KoreaIT.java.jam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.exception.SQLErrorException;

public class DB {

	public static Connection conn(HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");

		// DB 연결
		Connection conn = null;
		
		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("예외 : 클래스가 없습니다");
			System.out.println("프로그램을 종료합니다");
			return conn;
		}
		
		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());
		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

}
