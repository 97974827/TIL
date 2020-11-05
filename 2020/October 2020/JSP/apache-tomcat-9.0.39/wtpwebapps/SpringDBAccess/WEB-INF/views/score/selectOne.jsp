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
	<h3>${stu.stuName}학생 성적 정보 조회</h3>
	
	<p>
		# 국어 : ${stu.kor} 점<br>
		# 영어 : ${stu.eng} 점<br>
		# 수학 : ${stu.math} 점<br>
		# 총점 : ${stu.total} 점<br>
		# 평균 : ${stu.average} 점<br>
		
		<a href="/database/score/register">다른 점수 등록하기</a>
		<a href="/database/score/list">점수 전체 조회</a>
		<a href="/database/score/search">점수 개별 조회</a>
		
	</p>
</body>
</html>