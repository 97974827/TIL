<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = request.getParameter("id");

	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% try { %>
<h4><%= id.toUpperCase() %></h4>
<% } catch(Exception e) { %> <!-- 에러가 났을때 톰캣 제공 에러페이지가 아닌 만들어놓은 페이지를 띄워줌 -->
		<h4>죄송합니다. 서버에 오류가 발생했습니다.</h4>
		<p>빠른 시일내에 해결 하겠습니다. </p>
<% } %> 
</body>
</html>