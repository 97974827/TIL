<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<h2>이클립스와 JSP파일로 렌더링 하기 </h2>
	<p> 안녕하세요. 오늘은 2020년 10월 14일 수요일입니다. <br> 스크립트 연습을 하고 있습니다. </p>
	<hr>
	
	<h3>구구단 3단 출력</h3>
	<% 
		for(int j=1; j<10; j++){  
			out.println("3 x " + j + " = " + (3*j) + "<br>" );
		} 
	%>
</body>
</html>