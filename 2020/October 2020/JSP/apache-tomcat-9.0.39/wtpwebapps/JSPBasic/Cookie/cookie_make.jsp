<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	/*
	# 서버에서 쿠키 생성방법
	1. 쿠키객체를 생성 - 생성자의 매개값으로 쿠키의 이름과 저장할 데이터를 입력
	*/
	Cookie apple = new Cookie("apple", "사과맛쿠키"); // 매개변수 꼭들어가야함 (저장데이터) - Object 타입 
	Cookie peanut = new Cookie("peanut", "땅콩맛쿠키");
	
	// 2. 쿠키 클래스의 setter 메서드로 쿠키의 속성들을 저장 
	apple.setMaxAge(60*60); // 쿠키의 유효 시간 설정 (60초*60초)
	peanut.setMaxAge(20);
	
	// 3. HTTP 응답 시 리스폰스 객체에 생성된 쿠키를 삭제하여 클라이언트에 전송
	response.addCookie(apple);
	response.addCookie(peanut);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="cookie_check.jsp">쿠키 확인하기~~</a>
</body>
</html>
