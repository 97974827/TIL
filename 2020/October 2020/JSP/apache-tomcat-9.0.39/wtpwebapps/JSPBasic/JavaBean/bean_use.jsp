<%@page import="kr.co.koo.jspbasic.user.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% // UserBean user = (UserBean) request.getAttribute("user"); %>
	<jsp:useBean id="user" class="kr.co.koo.jspbasic.user.UserBean" scope="request" />
	
	ID <jsp:getProperty name="user" property="id" /><br>
	PW <jsp:getProperty name="user" property="pw" /><br>
	Name <jsp:getProperty name="user" property="name" /><br>
	Email <jsp:getProperty name="user" property="email" /><br>
	
	<%-- ID <%= user.getId() %><br>
	PW <%= user.getPw() %><br>
	Name <%= user.getName() %><br>
	Email <%= user.getEmail() %><br> --%>
</body>
</html>