<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Cookie[] cookies = request.getCookies();

	for(Cookie c : cookies){
		String name = c.getName();
		String value = c.getValue();
		
		out.println("<br>쿠키이름 : " + name + "<br>");
		out.println("쿠키   값 : " + value);
	}
%>