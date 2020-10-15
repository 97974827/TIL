
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
	<hr>
	<%@
		include file="includeheader.jspf"
	%>
	
	<%= visit %>
</body>
</html>