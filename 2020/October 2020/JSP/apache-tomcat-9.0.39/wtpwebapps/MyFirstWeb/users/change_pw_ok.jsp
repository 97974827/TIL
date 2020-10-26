<%@page import="kr.co.koo.izone.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    /*
    oldPw, newPw 처리
       기존 비밀번호가 맞는지 확인 (userCheck 활용)
       기존 비밃번호가 틀렸다면 "현재 비밀번호를 다시 확인하세요"알림창 뒤로가기 실행
       기존 지밀번호가 맞다면 새로운 비밀번호로 정상 변경 수행후 "비밀번호가 정상적으로 변경되었습니다" 알림창 홈화면 
    */
    
	request.setCharacterEncoding("utf-8");
	String oldPw = request.getParameter("oldPw");
	String newPw = request.getParameter("newPw");
	String id = (String) session.getAttribute("user_id");
	
	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.memberLogin(id, oldPw); // 로그인 검증
	
	// 기존 비밀번호 맞다면 
	if(result == dao.LOGIN_SUCCESS){
		int res = dao.changePassWord(id, newPw);
		
		// UPDATE문 정상 수행시 
		if(res != 0) { %>
			<script>
				alert('비밀번호가 정상적으로 변경되었습니다.');
				location.href='/izone';
			</script>
<%		} else { %>
			<script>
				alert('비밀번호 변경에 실패하였습니다.');
				history.back();
			</script>	
<%		}
 	} else {  %>
		<script>
			alert('기존 비밀번호를 다시 확인 하세요 .');
			history.back();
		</script>
<%	}  %>