<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- JSP 외부 import 방법 --%>
	<%@ page import="java.util.Date" %>
	
	<%-- JSP 주석입니다. --%>
	
	<%
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String day = sdf.format(date);
		
	%>
	<%= day %>
</body>
</html>