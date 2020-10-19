<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %> <%-- Exception e랑 같음  --%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		죄송합니다. 요청하신 페이지를 찾을 수 없습니다..<br>
		빠른시일 안에 복구 하겠습니다 ㅠㅠ<br>
		잠시만 기다려주세요 !!<br>
	</p>
	<p>
		<strong>에러 원인 : <%=exception.getMessage() %></strong>
	</p>
</body>
</html>