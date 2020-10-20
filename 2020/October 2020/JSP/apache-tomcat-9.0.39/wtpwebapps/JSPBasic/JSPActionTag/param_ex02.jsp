<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	
%>

<jsp:forward page="param_ex03.jsp">
	<jsp:param name="nick" value="멍멍이" /> <%-- 끝에 / 붙여야함 --%> 
</jsp:forward>

