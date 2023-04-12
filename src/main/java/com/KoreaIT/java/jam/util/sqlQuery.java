package com.KoreaIT.java.jam.util;

import javax.servlet.http.HttpServletRequest;

public class sqlQuery {

	public static SecSql authentication(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		SecSql sql = SecSql.from("SELECT A.*, M.name AS writer");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.id = ? ;", id);
		return sql;
	}

}
