<%@page import="kr.co.koo.izone.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	
	request.setCharacterEncoding("utf-8");
	String user_id = request.getParameter("user_id");
	
	MemberDAO dao = MemberDAO.getInstance();
	boolean check = dao.isIdCheck(user_id);
	
	String str = "";
	
	if(check){ // 중복 
		str = "NO";
		out.println(str);
	} else {  
		str = "YES";
		out.println(str);
    } 
%>
