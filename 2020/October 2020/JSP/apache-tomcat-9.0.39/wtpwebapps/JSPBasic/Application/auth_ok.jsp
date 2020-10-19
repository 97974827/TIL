<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String code = request.getParameter("authcode"); // 사용자 입력값
	String auth = (String) session.getAttribute("authcode"); // 랜덤 생성된 인증번호 세션에서 가져오기  
		
	if (auth.equals(code)){
		session.setAttribute("auth_pass", true); // 인증을 했다는 증표 남김 
		response.sendRedirect("concert_reserve.jsp");
	} else { 
%>
		<script type="text/javascript">
			alert("인증코드가 일치 하지 않습니다.");
			history.back();
		</script>
	
	
 <% } %>
