<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 성공 페이지</h2>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if (id.equals("admin")){
			//response.sendRedirect("res_login_ok.jsp"); %>
			<form action="res_login.jsp" method="post">
				<p>
					<%= id %> 님 로그인 성공!<hr>
					<input type="submit" value="처음으로"><br>
				</p>
			</form>
		<% } else {
			response.sendRedirect("res_login_fail.jsp");
		}
	%>
	
	
</body>
</html>