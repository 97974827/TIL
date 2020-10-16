<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8"); // post방식 한글 처리
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	String check = request.getParameter("id_check"); // 체크 : yes, 체크 X : null
	
	if(id.equals("admin") && pw.equals("1234")){
		Cookie idcookie = new Cookie("idcookie", id);
		idcookie.setMaxAge(10);
		response.addCookie(idcookie);
		
		// 아이디 저장 눌렀다면 
		if(check != null){
			Cookie idMemory = new Cookie("remember_id", id);
			idMemory.setMaxAge(10);
			response.addCookie(idMemory);
		}
		
		response.sendRedirect("cookie_login_welcome.jsp");
	} else {
		response.sendRedirect("cookie_login_fail.jsp");
	}
	
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>