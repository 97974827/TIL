<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<form action="/database/score/selectOne?stuId=${stu.stuId}">
		<p>
			조회할 학번 : <input name="stuId" size="5">
			<input type="submit" value="조회">
		</p>
	</form>
</body>
</html>