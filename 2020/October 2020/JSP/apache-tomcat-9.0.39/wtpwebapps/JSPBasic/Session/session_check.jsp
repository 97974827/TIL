<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	// 세션 데이터 가져오기 
	String name = session.getAttribute("nickname").toString(); // 세션 데이터는 정해진 데이터를 받을 수 있음 return type Object
	String[] hobbys = (String[]) session.getAttribute("hobby");
	
	out.println(name + "<br>");
	out.println(Arrays.toString(hobbys) + "<hr>");
	
	// session.setMaxInactiveInterval(10); // 세션 시간 10초로 설정 
	int time = session.getMaxInactiveInterval(); // 세션시간 디폴트 : 1800초 (30분)
	out.println("세션 시간 : " + time + "<br>");
	
	// 특정 세션 데이터 삭제 
	session.removeAttribute("nickname");
	
	out.println("<br>삭제후 세션 상태<br>");
	out.println("<br>" + name + "<br>");
	out.println(Arrays.toString(hobbys) + "<hr>");
	
	// 모든 세션 데이터 삭제
	session.invalidate();
	
	// 세션 확인
	if(request.isRequestedSessionIdValid()){
		out.println("세션이 존재");
	} else {
		out.println("세션이 사라짐");
	}
%>