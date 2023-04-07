<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<div>
		<a href="https://www.naver.com" target="_blank">네이버</a>
		<a href="http://localhost:8081/2023_04_JSP_AM/article/list"
			target="_blank">버튼1</a>
		<a href="/2023_04_JSP_AM/article/list" target="_blank">버튼2</a>
		<a href="detail" target="_blank">디테일</a>
	</div>

	<h1>게시물 리스트</h1>

	<ul>
		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<li><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("id")%>번,
				<%=articleRow.get("regDate")%>,
				<%=articleRow.get("title")%>,
				<%=articleRow.get("body")%></a></li>
		<%
		}
		%>
	</ul>
</body>
</html>