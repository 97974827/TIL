<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id = request.getParameter("id");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 실패 페이지</h2>
	<form action="res_login.jsp" method="post">
		<p>
			<%= id %> 라는 아이디는 존재하지 않습니다!<hr>
			<input type="submit" value="처음으로"><br>
		</p>
	</form>
</body>
</html>