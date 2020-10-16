### JSP Cookie & Session & Application

- 쿠키를 제외한 세션, 페이지, 어플리케이션, 리퀘스트 객체는 모두 내장객체이다.
- 데이터 저장 범위 (Scope)
  - Page 는 한페이지 - 사실상 의미 없음 (한페이지에 변수만 지정하면 됨)
  - Request 는 위임 된 페이지 까지 연결 범위
  - Application 은 서버가 다운되거나, 오류가 있을때가지 저장함 



##### Cookie

- HTTP 프로토콜 통신이 한번 수행되면 서버는 데이터를 잃어버리는걸 방지하기 위해 사용 

- ex)
  - 사용자가 네이버 페이지에서 로그인 하고 웹툰을 보고 재밌어서 댓글을 달려 게시판에 들어가는데 또 로그인 창이 나오는 걸 방지 하기위해 서버에 쿠키라는 것을 구워놓는 개념 
  - 서버에서 응답 할 때 클라이언트 측에게 로그인 정보의 쿠키의 수명(기간)을 설정하여 보내서 클라이언트측(로컬PC) 에 정보 저장함
  - 서버에서 응답 할 때 클라이언트 측(로컬PC)에 쿠키의 정보가 있다면 재로그인을 시키지 않게 함
  - ID 자동로그인, 오늘 하루동안 팝업 보지않기, 아이디 저장 등등
- **서버에서 생성하여 클라이언트에 저장한다**





- 예제) 
  - 사과, 땅콩 쿠키를 생성하여 땅콩쿠키의 존재여부 확인 



`cookie_make.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	/*
	# 서버에서 쿠키 생성방법
	1. 쿠키객체를 생성 - 생성자의 매개값으로 쿠키의 이름과 저장할 데이터를 입력
	*/
	Cookie apple = new Cookie("apple", "사과맛쿠키"); // 매개변수 꼭들어가야함 (저장데이터) - Object 타입 
	Cookie peanut = new Cookie("peanut", "땅콩맛쿠키");
	
	// 2. 쿠키 클래스의 setter 메서드로 쿠키의 속성들을 저장 
	apple.setMaxAge(60*60); // 쿠키의 유효 시간 설정 (60초*60초)
	peanut.setMaxAge(20);
	
	// 3. HTTP 응답 시 리스폰스 객체에 생성된 쿠키를 삭제하여 클라이언트에 전송
	response.addCookie(apple);
	response.addCookie(peanut);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="cookie_check.jsp">쿠키 확인하기~~</a>
</body>
</html>

```



`cookie_check.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	boolean INFO_COOKIE = false;	

	// 클라이언트에서 쿠키를 가져오는 방법 
	Cookie[] cookies = request.getCookies(); // 쿠키 전부 가져옴
	
	for(Cookie c : cookies){
		String cookie_name = c.getName();
		
		if (cookie_name.equals("peanut")){
			String value = c.getValue(); // 쿠키값 읽어오는 메서드 
			out.println("<h3>땅콩 쿠키가 존재합니다.</h3>");
			INFO_COOKIE = true;
			break;
		}
	}
	
	if(!INFO_COOKIE){
		out.println("<h3>땅콩 쿠키가 사라졌습니다.</h3>");
	}
%>
```



`cookie_all.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Cookie[] cookies = request.getCookies();

	for(Cookie c : cookies){
		String name = c.getName();
		String value = c.getValue();
		
		out.println("<br>쿠키이름 : " + name + "<br>");
		out.println("쿠키   값 : " + value);
	}
%>
```



결과 : 

```
[쿠키 확인하기~~](http://localhost:8080/JSPBasic/Cookie/cookie_check.jsp)

### 땅콩 쿠키가 존재합니다.

[모든 쿠키보기~~](http://localhost:8080/JSPBasic/Cookie/cookie_all.jsp)

-- 생성된 쿠키 -- 
쿠키이름 : apple
쿠키 값 : 사과맛쿠키
쿠키이름 : peanut
쿠키 값 : 땅콩맛쿠키
쿠키이름 : JSESSIONID
쿠키 값 : CDF0D3C533695C7469AFF6DA5A25CEAC
```



- 실습 ) 
  - 로그인 해서 아이디 저장하기 



`cookie_login.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 로그인 정보 쿠키 로직  
	boolean isLogin = false;
	Cookie[] cookies = request.getCookies();

	if(cookies != null){
		for(Cookie c : cookies){
			if (c.getName().equals("idcookie")){
				
				isLogin = true;
			}
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
	<% if (!isLogin) { %>
		<form action="cookie_login_ok.jsp" method="post">
			<p>
				<h3>어서오세요~</h3>
				ID  <input type="text" name="id" size="10px" placeholder="아이디">
				<input type="checkbox" name="id_check" value="yes">
				<small> 아이디 기억하기</small><br>
				PW <input type="password" name="pw" size="10px" placeholder="패스워드"><br>
				
				<input type="submit" value="로그인">
			</p>
		</form>
	<% } else {  %>
		<h3>이미 로그인한 사용자 입니다.</h3>
		<a href="cookie_login_welcome.jsp">환영 페이지로 들어가기 </a>
	<% } %>
</body>
</html>
```



`cookie_login_ok.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8"); // post방식 한글 처리
	
	String id = request.getParameter("id");
	// String id_remember = request.getParameter("");
	String pw = request.getParameter("pw");
	
	if(id.equals("admin") && pw.equals("1234")){
		Cookie idcookie = new Cookie("idcookie", id);
		idcookie.setMaxAge(10);
		response.addCookie(idcookie);
		
		// out.println("<h3>" + id + "님 로그인 성공!</h3>");
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
```



`cookie_login_fail.jsp`

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
	<h3>아이디가 존재 하지 않습니다!</h3>
</body>
</html>
```



`cookie_login_welcome.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Cookie[] cookies = request.getCookies();
	
	String user_id = null;
	
	for(Cookie c : cookies){
		if(c.getName().equals("idcookie")){
			user_id = c.getValue();
			
			break;
		}
	}
	
	if(user_id != null){ // 쿠키 존재 
		out.println("<h3>" + user_id + "님 로그인 환영합니다~</h3><br>");
		out.println("<a href=cookie_login.jsp>메인으로 돌아가기</a>");
	} else { // 쿠키 사라짐 
		out.println("<h3>시간이 지나 자동로그아웃 처리되었습니다.</h3><br>");
		out.println("<a href=cookie_login.jsp>다시 로그인 하기</a>");
	}
%>
```



#### Session

- 쿠키와 마찬가지로 서버에 데이터 유지 하기 위함 
- 서버당 하나의 세션 객체를 유지할 수 있다
- **세션 객체는 브라우저를 종료하면 삭제된다** (쿠키 X)
- 세션은 서버에서만 접근이 가능하여 , 보안이 좋고 저장할 수 있는 데이터에 한계가 없다 

- 쿠키는 용량 제한이 있지만 세션은 용량제한이 없다 



`session_make.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	session.setAttribute("nickname", "홍길동"); // 세션 속성 추가 : key - String, value - Object
	session.setAttribute("hobby", new String[]{"축구", "게임", "독서"} );
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="session_check.jsp">세션 데이터 확인하기</a>
</body>	
</html>
```



`session_check.jsp`

```jsp
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
```





실습) 

세션을 이용한 로그인 페이지 구현



`session_login.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그인 정보가 있다면 
	if(session.getAttribute("user_id") != null){
		String nick = (String) session.getAttribute("user_nick");
%>
		<p><%= nick %>님이 현재 로그인 중입니다.</p>
		<a href="session_login_welcome.jsp">메인 페이지로</a>
 <% } else { 
 		
    } %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	fieldset{
		width:20%;
		align-content: center;
	}
</style>

</head>
<body>
	<form action="session_login_ok.jsp" method="post">
		<fieldset>
			<legend>로그인폼</legend>
			
			ID <input type="text" name="id" size="10" placeholder="아이디"><br>
			PW <input type="password" name="pw" size="10" placeholder="패스워드"><br>
			별명 <input type="text" name="nick" size="10" placeholder="별명"><br>
			<input type="submit" value="로그인"><br>
		</fieldset>
	</form>
</body>
</html>
```



`session_login_ok.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String nick = request.getParameter("nick");
	
	if (id.equals("admin") && pw.equals("1234")){
		
		// 페이지 이동하기 전에 세션에 정보 담음
		session.setAttribute("user_id", id); 
		session.setAttribute("user_nick", nick);
		
		response.sendRedirect("session_login_welcome.jsp");
	} else {
%>
		<!-- HTML 내부에 자바 스크립트 코드 주입 -->
		<script type="text/javascript">
			alert("로그인에 실패했습니다.");
			history.back();
		</script>

<% } %>
```



`session_login_welcome.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 로그인 하지 않은 사용자가 이 페이지에 들어오려할때
	if(session.getAttribute("user_id") == null){
		response.sendRedirect("session_login.jsp");
	}

	String id = (String) session.getAttribute("user_id");
	String nick = (String) session.getAttribute("user_nick");
	
	out.println("<h3>" + nick + "(" + id + ")" + "님 환영합니다~~~~</h3>");%>
	<a href="../JSPobjRequest/req_album.jsp">앨범 리스트 보기 </a>
	<a href="session_login.jsp">로그인 페이지로 </a>
	<a href="session_logout.jsp">로그아웃 </a><br>
```



`session_logout.jsp`

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	
	session.invalidate();
	response.sendRedirect("session_login.jsp");
%> 
```

