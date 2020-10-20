<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>당신의 성적을 입력하세요</h3>
	<form action="score_bean_make.jsp" method="post">
		<table border="1">
			<tr>
				<td>국어 점수는? </td>
				<td><input type="text" name="kor" size="5" placeholder="국어점수"></td>
				<td>영어 점수는? </td>
				<td><input type="text" name="eng" size="5" placeholder="영어점수"></td>
				<td>수학 점수는? </td>
				<td><input type="text" name="math" size="5" placeholder="수학점수"></td>
			</tr>
		</table>
		<input type="submit" value="통계 구하기">
	</form>
</body>
</html>