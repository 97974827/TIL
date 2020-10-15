### JSP 문법 실습

1. 페이지 방문수 / 행운의 숫자를 임의 추출하여 색깔 추출 

```jsp
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
```



2. List 를 이용한 로또뽑기

```jsp

<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		List<Integer> lotto = new ArrayList<>();
		
		while(lotto.size() < 6){
			int num = (int) (Math.random() * 45) + 1;
			if(!lotto.contains(num)){
				lotto.add(num);
			}
		}
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>오늘의 로또번호는 ? </h2>
	
	<br>
	<%
		Collections.sort(lotto);
		for(int j=0; j<lotto.size(); j++){
			out.println(lotto.get(j) + " ");
		}
	%>
</body>
</html>
```



