<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String singer = request.getParameter("user_select");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>선택하신 앨범 정보 입니다</h2>
	<p>
		- 가수 : <%= singer %> <br>
		<% if (singer.equals("loco")){ %>
			<img src="loco.jpg" width="200px" height="200px">
			<iframe width="800" height="600" src="https://www.youtube.com/embed/c0gZnxJ5U6c?rel=0;apm;autoplay=1" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		<% } else if (singer.equals("환불원정대")){ %>
			<img src="donttouch.jpg" width="200px" height="200px">
			<iframe width="800" height="600" src="https://www.youtube.com/embed/Ydv6XPWqD-A?rel=0;apm;autoplay=1" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		<% } %>
	</p>
</body>
</html>