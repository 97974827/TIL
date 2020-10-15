<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String name = request.getParameter("user_name");
	String id = request.getParameter("user_id"); 
	String pw = request.getParameter("user_pw");
	String sex = request.getParameter("user_sex");
	String[] arr_hobby = request.getParameterValues("hobby");
	String region = request.getParameter("region");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>form 태그로 전달된 데이터 읽기 연습</h2>
	<p>
		이름 : <%= name %><br>
		ID : <%= id %><br>
		PW : <%= pw %><br>
		성별 : <%= sex %><br>
		취미 : <%= Arrays.toString(arr_hobby) %><br>
		지역 : <%= region %><br>
	</p>
</body>
</html>