<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	boolean INFO_COOKIE = false;	

	// 클라이언트에서 쿠키를 가져오는 방법 
	Cookie[] cookies = request.getCookies(); // 쿠키 전부 가져옴
	
	for(Cookie c : cookies){
		String cookie_name = c.getName();
		
		if (cookie_name.equals("peanut")){
			String value = c.getValue(); // 쿠키값 읽어오는 메서드 
			out.println("<h3>땅콩 쿠키가 존재합니다.</h3>");
			INFO_COOKIE = true;
			break;
		}
	}
	
	if(!INFO_COOKIE){
		out.println("<h3>땅콩 쿠키가 사라졌습니다.</h3>");
	}
%>

<a href="cookie_all.jsp">모든 쿠키보기~~</a>