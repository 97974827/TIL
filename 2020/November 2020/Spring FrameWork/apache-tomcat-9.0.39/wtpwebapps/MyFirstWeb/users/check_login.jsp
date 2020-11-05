<%@page import="kr.co.koo.izone.member.model.MemberVO"%>
<%@page import="kr.co.koo.izone.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	/*
		1. 요청 파라미터 처리 
		2. 모델을 찾아 DB연동처리
		3. DB처리에 결과에 따른 알림창 설정
		- 로그인 성공시 세션에 user_id, user_name 이란 
		이름으로 로그인한 회원의 아이디와 이름을 저장후 홈으로 안내
		
		- 존재하지 않는 ID일 경우 알림창으로 존재하지 않는 ID입니다 출력후 뒤로가기 실행 history.bach()
		- 비밀번호가 틀렸을 경우 알림창으로 비밀번호가 틀렸습니다. 출력 후 뒤로가기 
	*/
	
	request.setCharacterEncoding("utf-8");
	
	String userId = request.getParameter("user_id");
	String userPw = request.getParameter("user_pw");
	
	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.memberLogin(userId, userPw);
	String str = "";
	
	// 로그인 성공 
	if(result == dao.LOGIN_SUCCESS){
		MemberVO user = dao.getMemberInfo(userId);		
		session.setAttribute("user_id", userId);
		session.setAttribute("user_name", user.getUserName());
		str = "LOGIN_OK";
		// response.sendRedirect("/izone");
 	} else if (result == dao.LOGIN_FAIL_PW){  
		str = "NOT_PW";
	} else {  
		str = "NOT_ID";
 	}
	out.println(str);
%>



