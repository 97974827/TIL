<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
	UUID uuid = UUID.randomUUID(); // 인증번호 랜덤으로 만드는 API
	// System.out.println(uuid.toString()); // ex) 199dedf0-d8c1-45f9-ae0d-454a2e0b8287

	String[] uuids = uuid.toString().split("-");
	System.out.println(Arrays.toString(uuids)); // 배열의 내부값을 볼땐 Arrays.toString(배열명)
	System.out.println(uuids[1]); 
	
	session.setAttribute("authcode", uuids[1]);
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>콘서트 예매</title>
</head>
<body>
	<h2>회원 인증문자</h2>
	<h1>인증코드 : <del><%= uuids[1] %></del></h1>
	<form action="auth_ok.jsp" method="post">
		인증문자를 입력해 주세용~~<br>
		인증코드  <input type="text" name="authcode" size="20" height="20" placeholder="인증코드를 입력하세요">
		<input type="submit" value="확인">
	</form>
</body>
</html>