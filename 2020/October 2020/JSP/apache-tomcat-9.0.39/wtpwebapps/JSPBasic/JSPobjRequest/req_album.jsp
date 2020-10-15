<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="req_album_info.jsp">
		<table border="1">
			<th>선택</th>
			<th>앨범 커버</th>
			<th>가수</th>
			<th>앨범 제목</th>
			<th>날짜</th>
			<tr><!-- 첫번째 줄 시작 -->
			    <td><input type="radio" name="user_select" value="loco"></td>
			    <td><img src="loco.jpg" width="200px" height="200px"></td>
			    <td>로꼬</td>
			    <td>잠이 들어야 (Feat. 헤이즈)</td>
			    <td>2020.10.14</td>
			</tr><!-- 첫번째 줄 끝 -->
			
			<tr><!-- 두번째 줄 시작 -->
			 	<td><input type="radio" name="user_select" value="환불원정대"></td>
			    <td><img src="donttouch.jpg" width="200px" height="200px"></td>
			    <td>환불원정대</td>
			    <td>DON'T TOUCH ME</td>
			    <td>2020.10.10</td>
			</tr><!-- 두번째 줄 끝 -->
	    </table>
    <hr><input type="submit" value="확인"><br>
    </form>
</body>
</html>