package com.KoreaIT.java.jam.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

public class ArticleDao {

	private Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int getTotalCnt() {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
		sql.append("FROM article");

		return DBUtil.selectRowIntValue(conn, sql);
	}

	public List<Map<String, Object>> getArticleRows(int limitFrom, int itemsInAPage) {
		SecSql sql = SecSql.from("SELECT A.*, M.name AS writer");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT ?, ?;", limitFrom, itemsInAPage);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
		return articleRows;
	}

}