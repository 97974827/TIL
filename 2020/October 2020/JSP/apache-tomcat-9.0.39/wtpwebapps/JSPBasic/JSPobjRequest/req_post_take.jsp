<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");	
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Post 결과</h2>
	<p>
		- 아이디    : <%= id %> <br>
		- 패스워드 : <%= pw %> <br>
		- 이    름   : <%= name %> <br>
	</p>
</body>
</html>