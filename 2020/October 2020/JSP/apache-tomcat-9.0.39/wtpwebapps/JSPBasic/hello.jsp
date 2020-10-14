<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>이클립스로 JSP 파일 만들기 </h2>
	<a href="http://www.naver.com" >네이버로 이동</a>
	<hr>
	<!-- 문단을 넣는 태그입니다  -->
	<% for(int i=0; i<5; i++){
		System.out.println("<p>" + "안녕 안녕" + "</p>");
	%>
	<% } %>
	<b>2020 10 14 수요일입니다 </b>
</body>
</html>