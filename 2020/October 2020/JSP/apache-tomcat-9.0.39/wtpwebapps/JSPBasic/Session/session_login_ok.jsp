<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String nick = request.getParameter("nick");
	
	if (id.equals("admin") && pw.equals("1234")){
		
		// 페이지 이동하기 전에 세션에 정보 담음
		session.setAttribute("user_id", id); 
		session.setAttribute("user_nick", nick);
		
		response.sendRedirect("session_login_welcome.jsp");
	} else {
%>
		<!-- HTML 내부에 자바 스크립트 코드 주입 -->
		<script type="text/javascript">
			alert("로그인에 실패했습니다.");
			history.back();
		</script>

<% } %>