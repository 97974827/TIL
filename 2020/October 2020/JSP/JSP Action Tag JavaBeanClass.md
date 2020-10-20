### JSP Action Tag / Java Bean class



#### JSP Action Tag 

요청 페이지에서 위임 페이지로 넘길 때 request,response 객체 사용 여부 차이점 

- `response.sendRedirect("요청페이지 URL")`
  - 한 페이지에 request, response 객체들이 일어나 소멸하고 , 다른페이지에 자동 재요청,응답이 일어나 새로운 request, response 객체들이 생성됨 
  - 한번 요청응답이 끝난 이후 재 요청응답이 일어남 
  - 처음에 전달받은 데이터는 소멸됨 
  - 보통 요청을 처리한 다음 새로운 요청으로 작업을 해야하는 경우 사용된다. - 회원가입 후 요청페이지
- `<jsp:forward page="요청페이지 URL">`
  - request 요청 시 요청페이지는 위임페이지로 떠넘기고 위임 페이지에서 요청 시 생성된 request, response 객체를 그대로 사용할 수 있다.



### Java Bean Class

- 자바 빈이란 자바언어의 데이터(변수)와 기능(메서드)으로 이루어진 클래스 
- getter,setter로 이루어짐 
- 데이터 변수하나하나가 아닌 객체를 넘겨줌으로서 원활하게 데이터 전달을 할 수 있음 
- 사용방식 
  - `<jsp:useBean id="자바빈 객체 이름" class="자바빈이 위치한 실제 경로" scope="객체를 사용할 범위" />`
- 속성
  - id - JSP 페이지에서 자바빈 객체에 접근할 때 사용할 이름을 지정 
  - class - 패키지 이름을 포함한 자바빈 클래스의 **완전한 경로**를 입력
  - scope - 자바빈 객체를 저장할 영역을 지정함 (생략하면 해당 페이지 안에서만 사용 )

- 자바 패키지 지정 시 보통 회사 도메인 페키지를 이름으로 함 (역순)
  - ex) `kr.co.koo.jspbasic.user`

<img src="./자바빈 액션태그 사용법.PNG">



id, pw, name, email 입력받는 예제

`UserBean.java`

```java
package kr.co.koo.jspbasic.user;

/*
  자바빈은 데이터 베이스와 반복 데이터 작업을 쉽게 처리하기 위한 클래스.
  일반적으로 데이터베이스의 컬럼과 1:1로 매칭되는 멤버변수(필드)를 선언한다.
  자바빈 클래스는 은닉을 사용하여 데이터를 보호함 
 */
public class UserBean {
	
	private String id;
	private String pw;
	private String name;
	private String email;
	
	
	// 자바빈 클래스는 일반적으로 기본 생성자 1개(필수)와 모든 멤버변수를 초기화 하는 생성자(선택)를 선언한다
	public UserBean() {
	}
	
	public UserBean(String id, String pw, String name, String email) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

```



`bean_make.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%@ page import="kr.co.koo.jspbasic.user.UserBean" %>
<jsp:useBean id="user" class="kr.co.koo.jspbasic.user.UserBean" scope="request" />

<%
	request.setCharacterEncoding("utf-8");

	// String id = request.getParameter("id");
	// String pw = request.getParameter("pw");
	// String name = request.getParameter("name");
	// String email = request.getParameter("email");
%>

<%-- 자바빈 멤버변수와 프로퍼티가 일치할 경우 * 로 지정하여 자동으로 파라미터 값을 전부 저장  --%>
<jsp:setProperty name="user" property="*" />

<%--<jsp:setProperty name="user" property="id" value="<%= id %>"/>
	<jsp:setProperty name="user" property="pw" value="<%= pw %>"/>
	<jsp:setProperty name="user" property="name" value="<%= name %>"/>
	<jsp:setProperty name="user" property="email" value="<%= email %>"/> --%>
<%	
	// user.setId(id);
	// user.setPw(pw);
	// user.setName(name);
	// user.setEmail(email);
	
	// UserBean user = new UserBean(id, pw, name, email);
	// request.setAttribute("user", user);
	
	/*request.setAttribute("user_id", id);
	request.setAttribute("user_pw", pw);
	request.setAttribute("user_name", name);
	request.setAttribute("user_email", email);*/
%>
<jsp:forward page="bean_use.jsp" />
```



`bean_use.jsp`

```jsp
<%@page import="kr.co.koo.jspbasic.user.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% // UserBean user = (UserBean) request.getAttribute("user"); %>
	<jsp:useBean id="user" class="kr.co.koo.jspbasic.user.UserBean" scope="request" />
	
	ID <jsp:getProperty name="user" property="id" /><br>
	PW <jsp:getProperty name="user" property="pw" /><br>
	Name <jsp:getProperty name="user" property="name" /><br>
	Email <jsp:getProperty name="user" property="email" /><br>
	
	<%-- ID <%= user.getId() %><br>
	PW <%= user.getPw() %><br>
	Name <%= user.getName() %><br>
	Email <%= user.getEmail() %><br> --%>
</body>
</html>
```



### setProperty, getProperty 사용법

<img src="./자바빈 액션태그 프로퍼티.PNG">



`bean_form.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="bean_make.jsp" method="post"/>
		<table border="1">
			<tr>
				<td> ID </td>
				<td><input type="text" name="id" size="10" placeholder="ID"></td>
				<td> PW </td>
				<td><input type="password" name="pw" size="10" placeholder="Pw"></td>
			</tr>
			<tr>
				<td> Name </td>
				<td><input type="text" name="name" size="10" placeholder="name"></td>
				<td> Email </td>
				<td><input type="text" name="email" size="10" placeholder="email"></td>
			</tr>
			<tr>
				<input type="submit" value="회원가입">
			</tr>
		</table>
	</form>
</body>
</html>
```



점수 3개를 입력 받아 총점, 평균 구하기

`score_form.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>당신의 성적을 입력하세요</h3>
	<form action="score_bean_make.jsp" method="post">
		<table border="1">
			<tr>
				<td>국어 점수는? </td>
				<td><input type="text" name="kor" size="5" placeholder="국어점수"></td>
				<td>영어 점수는? </td>
				<td><input type="text" name="eng" size="5" placeholder="영어점수"></td>
				<td>수학 점수는? </td>
				<td><input type="text" name="math" size="5" placeholder="수학점수"></td>
			</tr>
		</table>
		<input type="submit" value="통계 구하기">
	</form>
</body>
</html>
```

`score_bean_make.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.koo.jspbasic.score.ScoreBean"%>
<jsp:useBean id="score" class="kr.co.koo.jspbasic.score.ScoreBean" scope="request" />

<% request.setCharacterEncoding("utf-8"); %>

<jsp:setProperty name="score" property="*" />

<%
		// ScoreBean score = (ScoreBean) request.getAttribute("score");
	int total = score.getKor() + score.getEng() + score.getMath();
	double avg = (double) total / 3;
	avg = Double.parseDouble(String.format("%.1f", avg));
%>
	
<jsp:setProperty name="score" property="total" value="<%= total %>"/>
<jsp:setProperty name="score" property="avg" value="<%= avg %>"/>


<jsp:forward page="score_get.jsp" />
```



`score_get.jsp`

```jsp
<%@ page import="kr.co.koo.jspbasic.score.ScoreBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="score" class="kr.co.koo.jspbasic.score.ScoreBean" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>당신의 성적표 입니다 </h3><br>
	Korea  : <jsp:getProperty name="score" property="kor"/><br>
	English: <jsp:getProperty name="score" property="eng"/><br>
	math   : <jsp:getProperty name="score" property="math"/><br>
	Total  : <jsp:getProperty name="score" property="total"/><br>
	Avarage: <jsp:getProperty name="score" property="avg"/><br>
	
</body>
</html>
```

