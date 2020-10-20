<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String nick = request.getParameter("nick");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		이  름 : <%= name %><br>
		이메일: <%= email %><br>
		닉네임: <%= nick %><br>
	</p>
</body>
</html>