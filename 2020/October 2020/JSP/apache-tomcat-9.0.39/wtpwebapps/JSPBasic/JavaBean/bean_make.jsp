<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%@ page import="kr.co.koo.jspbasic.user.UserBean" %>
<jsp:useBean id="user" class="kr.co.koo.jspbasic.user.UserBean" scope="request" />

<%
	request.setCharacterEncoding("utf-8");

	// String id = request.getParameter("id");
	// String pw = request.getParameter("pw");
	// String name = request.getParameter("name");
	// String email = request.getParameter("email");
%>

<%-- 자바빈 멤버변수와 프로퍼티가 일치할 경우 * 로 지정하여 자동으로 파라미터 값을 전부 저장  --%>
<jsp:setProperty name="user" property="*" />

<%--<jsp:setProperty name="user" property="id" value="<%= id %>"/>
	<jsp:setProperty name="user" property="pw" value="<%= pw %>"/>
	<jsp:setProperty name="user" property="name" value="<%= name %>"/>
	<jsp:setProperty name="user" property="email" value="<%= email %>"/> --%>
<%	
	// user.setId(id);
	// user.setPw(pw);
	// user.setName(name);
	// user.setEmail(email);
	
	// UserBean user = new UserBean(id, pw, name, email);
	// request.setAttribute("user", user);
	
	/*request.setAttribute("user_id", id);
	request.setAttribute("user_pw", pw);
	request.setAttribute("user_name", name);
	request.setAttribute("user_email", email);*/
%>
<jsp:forward page="bean_use.jsp" />