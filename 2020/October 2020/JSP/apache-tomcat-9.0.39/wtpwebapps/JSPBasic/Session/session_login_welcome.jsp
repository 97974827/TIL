<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 로그인 하지 않은 사용자가 이 페이지에 들어오려할때
	if(session.getAttribute("user_id") == null){
		response.sendRedirect("session_login.jsp");
	}

	String id = (String) session.getAttribute("user_id");
	String nick = (String) session.getAttribute("user_nick");
	
	out.println("<h3>" + nick + "(" + id + ")" + "님 환영합니다~~~~</h3>");%>
	<a href="../JSPobjRequest/req_album.jsp">앨범 리스트 보기 </a>
	<a href="session_login.jsp">로그인 페이지로 </a>
	<a href="session_logout.jsp">로그아웃 </a><br>
		
    
