<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
int totalCnt = (int) request.getAttribute("totalCnt");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>
	<div>
		<a href="write">글쓰기</a>
		<br>
		<a href="../member/join">회원가입</a>
		<br>
		<a href="../member/login">로그인</a>
	</div>

	<h1>게시물 리스트</h1>

	<table style="border-collapse: collapse; border-color: green"
		border="2px">

		<tr>
			<th>번호</th>
			<th>작성날짜</th>
			<th>제목</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<tr style="text-align: center;">
			<td><%=articleRow.get("id")%></td>
			<td><%=articleRow.get("regDate")%></td>
			<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
			<td><a href="modify?id=<%=articleRow.get("id")%>">수정</a></td>
			<td><a href="doDelete?id=<%=articleRow.get("id")%>">삭제</a></td>
		</tr>
		<%
		}
		%>

	</table>

	<style type="text/css">
.page {
	background-color: gold;
}

.page>a {
	color: black;
}

.page>a.red {
	color: red;
}
</style>

	<div class="page">
		<%
		if (cPage > 1) {
		%>
		<a href="list?page=1">◀◀</a>
		<%
		}
		// 페이징  처리
		if (totalCnt > 0) {
			// 총 페이지의 수
			int pageCount = totalCnt / 10 + (totalCnt % 10 == 0 ? 0 : 1);
			// 한 페이지에 보여줄 페이지 블럭(링크) 수
			int pageBlock = 10;
			// 한 페이지에 보여줄 시작 및 끝 번호(예 : 1, 2, 3 ~ 10 / 11, 12, 13 ~ 20)
			int startPage = ((cPage - 1) / pageBlock) * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;

			// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			if (startPage > pageBlock) { // 페이지 블록수보다 startPage가 클경우 이전 링크 생성
		%>
		<a href="list?page=<%=startPage - 10%>">[이전]</a>
		<%
		}

		for (int i = startPage; i <= endPage; i++) { // 페이지 블록 번호
		if (i == cPage) { // 현재 페이지에는 링크를 설정하지 않음
		%>
		<a class="<%=cPage == i ? "red" : ""%>" href="list?page=<%=i%>">[<%=i%>]</a>
		<%
		} else { // 현재 페이지가 아닌 경우 링크 설정
		%>
		<a href="list?page=<%=i%>">[<%=i%>]
		</a>
		<%
		}
		} // for end

		if (endPage < pageCount) { // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
		%>
		<a  href="list?page=<%=startPage + 10%>">[다음]</a>
		<%
		}
		}
		%>
		<%
		if (cPage < totalPage) {
		%>
		<a href="list?page=<%=totalPage%>">▶▶</a>
		<%
		}
		%>
	</div>


</body>
</html>