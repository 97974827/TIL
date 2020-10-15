<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>HttpRequest Post form 태그 실습</h2>
	<form action="req_post_take.jsp" method="post"> 
		- ID : <input type="text" name="id" size="10px"><br>
		- PW : <input type="password" name="pw" size="10px"><br>
		- 이름 : <input type="text" name="name" size="10px"><br>
		<input type="submit" value="다음으로 넘어가기">
	</form>
</body>
</html>