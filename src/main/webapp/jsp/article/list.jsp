<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    List<Map<String, Object>> articleRows =(List<Map<String, Object>>) request.getAttribute("articleRows");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>

	<h1>게시물 리스트</h1>

	<ul>
		<li>
			<%=articleRows.get(0).get("id") %>번, <%=articleRows.get(0).get("regDate") %>, <%=articleRows.get(0).get("title") %>, <%=articleRows.get(0).get("body") %>
		</li>
	</ul>
</body>
</html>