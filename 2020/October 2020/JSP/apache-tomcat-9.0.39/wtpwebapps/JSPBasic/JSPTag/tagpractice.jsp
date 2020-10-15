<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>오늘의 운세!</h2>
	
	<%-- 지시자 directive --%>
	<%@ 
		page import="java.util.Random"
	%>
	
	<%!
		// declaration 선언자
		// jsp 파일에서 사용할 멤버변수, 메소드를 선언할 때 사용
		
		int total = 0;
		
		public int randomNumber(){
			// return (int) (Math.random() * 10) + 1; // 1~10
			Random r = new Random();
			return r.nextInt(10) + 1;
		}
		
		public String randomColor(){
			String color = "";
			double num = Math.random(); // 0.0 >= , < 1.0
			
			if(num >= 0.66){
				color = "빨강";
			} else if(num >= 0.33) {
				color = "노랑";
			} else {
				color = "파랑";
			}
			
			return color;
		}
	%>
	
	<% 
		int each = 0;
		each++;
		out.println("페이지 누적 요청수 : " + total++);
		out.println("페이지 개별 요청수 : " + each + "<br>");
	%>
	<h2>오늘의 행운의 숫자</h2><br>
	<%
		out.println("행운의 숫자 : " + randomNumber() + "<br>");
		out.println("행운의 색깔 : " + randomColor() + "<br>");
	%>
	
</body>
</html>