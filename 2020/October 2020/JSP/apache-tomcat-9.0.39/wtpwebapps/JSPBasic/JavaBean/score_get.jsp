<%@ page import="kr.co.koo.jspbasic.score.ScoreBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="score" class="kr.co.koo.jspbasic.score.ScoreBean" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>당신의 성적표 입니다 </h3><br>
	Korea  : <jsp:getProperty name="score" property="kor"/><br>
	English: <jsp:getProperty name="score" property="eng"/><br>
	math   : <jsp:getProperty name="score" property="math"/><br>
	Total  : <jsp:getProperty name="score" property="total"/><br>
	Avarage: <jsp:getProperty name="score" property="avg"/><br>
	
</body>
</html>