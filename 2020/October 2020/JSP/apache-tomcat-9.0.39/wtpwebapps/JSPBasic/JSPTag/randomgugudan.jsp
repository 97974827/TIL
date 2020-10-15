<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%!
	int total = 0;
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

	<p>
		페이지 누적 요청 수 <%= ++total %>
		<br>매 10번째 방문자에게는 기프티콘을 드립니다.
		<%
			if (total%10==0){
				out.println("<br>당첨되었습니다!<br>");
			}
			int ran_gu = (int)(Math.random() * 8 )+ 2;
		%>
	</p><hr>
	
	<h2>랜덤 구구단</h2>
	<p>
		이번에 나온 구구단은 <%= ran_gu %> 단 입니다.<br><br>
		<%
			for(int i=1; i<10; i++){
				out.println(ran_gu + " x " + i + " = " + ran_gu*i + "<br>");
			}
		%>
	</p>
	
	
	
</body>
</html>