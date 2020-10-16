<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그인 정보가 있다면 
	if(session.getAttribute("user_id") != null){
		String nick = (String) session.getAttribute("user_nick");
%>
		<p><%= nick %>님이 현재 로그인 중입니다.</p>
		<a href="session_login_welcome.jsp">메인 페이지로</a>
 <% } else { 
 		
    } %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	fieldset{
		width:20%;
		align-content: center;
	}
</style>

</head>
<body>
	<form action="session_login_ok.jsp" method="post">
		<fieldset>
			<legend>로그인폼</legend>
			
			ID <input type="text" name="id" size="10" placeholder="아이디"><br>
			PW <input type="password" name="pw" size="10" placeholder="패스워드"><br>
			별명 <input type="text" name="nick" size="10" placeholder="별명"><br>
			<input type="submit" value="로그인"><br>
		</fieldset>
	</form>
</body>
</html>