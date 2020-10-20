<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="bean_make.jsp" method="post"/>
		<table border="1">
			<tr>
				<td> ID </td>
				<td><input type="text" name="id" size="10" placeholder="ID"></td>
				<td> PW </td>
				<td><input type="password" name="pw" size="10" placeholder="Pw"></td>
			</tr>
			<tr>
				<td> Name </td>
				<td><input type="text" name="name" size="10" placeholder="name"></td>
				<td> Email </td>
				<td><input type="text" name="email" size="10" placeholder="email"></td>
			</tr>
			<tr>
				<input type="submit" value="회원가입">
			</tr>
		</table>
	</form>
</body>
</html>