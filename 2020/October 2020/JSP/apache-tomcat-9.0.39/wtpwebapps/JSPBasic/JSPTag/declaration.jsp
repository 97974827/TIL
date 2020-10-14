<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%!
		public int money = 0;
		public String str = "홍길동";	
		
		public int add (int n1, int n2){
			return n1 + n2;
		}
		
	%>
	
	<%
		int a = 4;
		int b = 7;
		out.println("첫번쨰 값 : " + a + "<br>");
		out.println("두번쨰 값 : " + b + "<br>");
		int result = add(a, b);
		out.println("더한 결과 값은 ? " + result + "<br>");
	%>
	
</body>
</html>