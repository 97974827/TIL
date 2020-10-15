<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 페이지</h2>
	
	<div align="center">
		<form action="res_login_ok.jsp" method="post">
			<table border="1">
				<tr>
					<td><input type="text" name="id" placeholder="아이디" size="10px"></td>
					<td rowspan="2"><input type="submit" value="로그인" width="80px" height="60px"></td>
				</tr>
				<tr>
					<td><input type="password" name="pw" placeholder="패스워드" size="10px"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>