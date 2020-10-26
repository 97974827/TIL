<%@page import="kr.co.koo.izone.member.model.MemberVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="kr.co.koo.izone.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	/*
		MemberVO 에 회원 데이터 객체로 포장
		DAO 객체를 통한 DB Insert 
		회원 가입 성공여부에 따른 페이지 안내
		- 성공시 : alert('회원가입을 축하합니다.);
	    location.href='/izone'; -> 해당 URL로 이동 
	    - 실패시 : alert('회원가입에 실패했습니다.);
   	    location.href='/izone'; -> 해당 URL로 이동
	*/
	
	request.setCharacterEncoding("utf-8");
   	    
   	
	//String id = request.getParameter("userId");
	//String pw = request.getParameter("userPw");
	//String name = request.getParameter("userName");
	//String email = request.getParameter("userEmail");
	
	
   	// Date date = new Date();
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// String user_date = sdf.format(date); 	
	
%>
<!-- userBean : 객체 만들기 , setProperty : 변수 값 set -->
<jsp:useBean id="members" class="kr.co.koo.izone.member.model.MemberVO" />
<jsp:setProperty name="members" property="*" />

<%
	// MemberVO members = new MemberVO();
	// members.setUserId(id);
	// members.setUserPw(pw);
	// members.setUserName(name);
	// members.setUserEmail(email);
	// members.setUserDate(user_date);

	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.memberInsert(members);
	
	// 성공
	if(result != 0){ %>
		<script>
			alert('회원가입을 축하합니다!');
			location.href='/izone';
		</script>
<%	} else { %>
		<script>
			alert('회원가입에 실패했습니다!');
			location.href='/izone';
		</script>
<%  } %>