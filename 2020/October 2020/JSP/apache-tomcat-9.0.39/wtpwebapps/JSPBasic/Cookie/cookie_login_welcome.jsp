<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Cookie[] cookies = request.getCookies();
	
	String user_id = null;
	
	for(Cookie c : cookies){
		if(c.getName().equals("idcookie")){
			user_id = c.getValue();
			
			break;
		}
	}
	
	if(user_id != null){ // 쿠키 존재 
		out.println("<h3>" + user_id + "님 로그인 환영합니다~</h3><br>");
		out.println("<a href=cookie_login.jsp>메인으로 돌아가기</a>");
	} else { // 쿠키 사라짐 
		out.println("<h3>시간이 지나 자동로그아웃 처리되었습니다.</h3><br>");
		out.println("<a href=cookie_login.jsp>다시 로그인 하기</a>");
	}
%>