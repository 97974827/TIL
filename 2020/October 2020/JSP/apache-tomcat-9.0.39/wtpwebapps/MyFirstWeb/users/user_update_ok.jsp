<%@page import="kr.co.koo.izone.member.model.MemberVO"%>
<%@page import="kr.co.koo.izone.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	/*
		요청 파라미터 값 처리 후 DB연동을 통해 정보 수정처리 완료
		수정 성공 시 알림창으로 회원정보가 정상적으로 수정되었습니다 출력 후 홈으로 안내
		수정 실패 시 회원정보 수정에 실패했습니다 출력 후 홈으로 안내 
		세션 관련 처리를 잘 생각해 볼것 
		DAO 메소드 이름은 updateMember
	*/
	request.setCharacterEncoding("utf-8");
	
	String id = (String) session.getAttribute("user_id");
	
	MemberDAO dao = MemberDAO.getInstance();
	MemberVO member = dao.getMemberInfo(id);

	String oldName = (String) session.getAttribute("user_name");
	String newName = request.getParameter("userName");
	
	String oldEmail = member.getUserEmail();
	String newEmail = request.getParameter("userEmail");
	
	// 변경 완료 
	if (dao.updateMember(newName, newEmail, id) == dao.LOGIN_SUCCESS ){ %>
		<script>
			alert('회원정보가 정상적으로 수정되었습니다.');
			location.href='/izone';
		</script>
<% 	} else { %>
		<script>
			alert('회원정보 수정에 실패했습니다.');
			location.href='/izone';
		</script>
<% 	} %>
	
	
