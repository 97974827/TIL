<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 로그인 정보 쿠키 로직  
	boolean isLogin = false; 		// 로그인 사용자 플래그 
	boolean isID = false; 		    // 아이디 저장 플래그 
	Cookie[] cookies = request.getCookies();
	
	String user_id = null; 
	if(cookies != null){
		for(Cookie c : cookies){
			/*if (c.getName().equals("idcookie")){ // 로그인 사용자 쿠키 
				isLogin = true;
			}*/
			if (c.getName().equals("remember_id")){
				user_id = c.getValue();
				isID = true;
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% if (!isLogin) { %>
		<form action="cookie_login_ok.jsp" method="post">
			<p>
				<h3>어서오세요~</h3>
				<% if (isID) { %>
					ID  <input type="text" name="id" size="10px" placeholder="아이디" value="<%= user_id %>">
				<% } else { %>
					ID  <input type="text" name="id" size="10px" placeholder="아이디">
				<% } %>
				<input type="checkbox" name="id_check" value="yes">
				<small> 아이디 기억하기</small><br>
				PW <input type="password" name="pw" size="10px" placeholder="패스워드"><br>
				
				<input type="submit" value="로그인">
			</p>
		</form>
	<% } else {  %>
		<h3>이미 로그인한 사용자 입니다.</h3>
		<a href="cookie_login_welcome.jsp">환영 페이지로 들어가기 </a>
	<% } %>
</body>
</html>