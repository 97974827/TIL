<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	session.setAttribute("nickname", "홍길동"); // 세션 속성 추가 : key - String, value - Object
	session.setAttribute("hobby", new String[]{"축구", "게임", "독서"} );
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="session_check.jsp">세션 데이터 확인하기</a>
</body>	
</html>