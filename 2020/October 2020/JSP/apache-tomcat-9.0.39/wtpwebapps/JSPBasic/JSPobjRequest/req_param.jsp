<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String breakfast = request.getParameter("bf");  // 클라이언트에서는 요청 파라미터 메소드 안에 문자이름에 값을 넣어서 서버에보냄 
	String lunch = request.getParameter("lunch");
	String dinner = request.getParameter("dinner");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>요청 파라미터 읽기 연습 </h2>
	<br>
	<p>
		- 아침밥 : <%= breakfast %> <br>
		- 점심밥 : <%= lunch %> <br>
		- 저녁밥 : <%= dinner %> <br>
	</p>
	
</body>
</html>