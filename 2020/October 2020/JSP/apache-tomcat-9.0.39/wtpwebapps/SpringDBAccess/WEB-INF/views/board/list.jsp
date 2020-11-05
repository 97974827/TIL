<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${art.size() <= 0}">
		<p>게시물이 존재하지 않습니다.</p>
	</c:if>
	
	<c:if test="${art.size() > 0}">
		<h2>게시글 목록</h2>
		<table border="1">
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>제목</td>
				<td>비고</td>
			</tr>
			
			<c:forEach var="i" items="${art}">
				<tr>
					<td>${i.boardNo}</td>
					<td>${i.writer}</td>
					<td>
						<a href="/database/board/content?boardNo=${i.boardNo}">${i.title}</a>
					</td>
					<td>
						<a href="/database/board/delete?boardNo=${i.boardNo}">[삭제]</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<form action="/database/board/searchList">
		<input type="text" name="keyword" placeholder="작성자 이름을 입력하세요">
		<input type="submit" value="검색">
	</form>
	
	<p>
		<a href="/database/board/write">게시글 작성하기</a>
	</p>
	
</body>
</html>