<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8080/JSPBasic/JSPobj/req_user_info.jsp"> <!-- 페이지 전환 주소 정해줌 : 절대,상대경로 둘다가능-->
		<p>
			- 이름 : <input type="text" name="user_name" size="10px"><br>
			- ID : <input type="text" name="user_id" size="10px"><br>
			- PW : <input type="password" name="user_pw" size="10px"><br>
			- 성별 : <input type="radio" name="user_sex" value="남성"> 남성 <!-- on 이라고 나오는 속성 바꾸기 : value -->
			<input type="radio" name="user_sex" value="여성"> 여성  <br>
			
			- 취미 : <input type="checkbox" name="hobby" value="축구"> 축구 
			<input type="checkbox" name="hobby" value="볼링"> 볼링  
			<input type="checkbox" name="hobby" value="드럼"> 드럼  
			<input type="checkbox" name="hobby" value="영화"> 영화  <br>
			
			- 지역 : 
			<select name="region">
				<option>서울</option>
				<option>제주</option>
				<option>강원</option>
				<option>전남</option>
			</select><br>
			
			<input type="submit" value="확인">
		</p>
	
	</form>
	
</body>
</html>