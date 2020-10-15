<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%
	StringBuffer url = request.getRequestURL(); // 서버 URL 주소 리턴
	String uri = request.getRequestURI();
	String protocol = request.getProtocol();
	String conPath = request.getContextPath();
	int serverPort = request.getServerPort();
	String userIP = request.getRemoteAddr();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		# 요청 URL   : <%= url %> <br>
		# 요청 URI   : <%= uri %> <br>
		# protocol : <%= protocol %> <br>
		# Path     : <%= conPath %> <br>
		# Port     : <%= serverPort %> <br>
		# 사용자 IP  : <%= userIP %> <br>
	</p>
</body>
</html>