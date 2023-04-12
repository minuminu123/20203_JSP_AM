package com.KoreaIT.java.jam.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.dto.Article;
import com.KoreaIT.java.jam.service.ArticleService;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;

		this.articleService = new ArticleService(conn);
	}

	public void showList() throws ServletException, IOException {
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int itemsInAPage = articleService.getItemsInAPage();

		int totalPage = articleService.getTotalPage();

		List<Article> articles = articleService.getForPrintArticles(page);

		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("articles", articles);
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

}