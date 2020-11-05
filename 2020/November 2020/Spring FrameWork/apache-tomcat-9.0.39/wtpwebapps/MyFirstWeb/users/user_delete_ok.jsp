<%@page import="kr.co.koo.izone.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String id = (String) session.getAttribute("user_id");
	
	MemberDAO dao = MemberDAO.getInstance();
	
	if(dao.deleteMember(id) == dao.LOGIN_SUCCESS){	
		session.invalidate(); %>
	<script>
		alert('당신은 아이즈원 회원에서 탈퇴되었습니다 .. 안녕히 가세요 ^^');
		location.href='/izone';
	</script>
<% 	} else  { %>
 	<script>
		alert('회원 탈퇴에 실패햐였습니다. 다시 시도해 주세요.');
		location.href='/izone/users/mypage_delete_check.jsp';
	</script>
<%  
	} 
%>