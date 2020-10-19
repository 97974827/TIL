### JSP 에러페이지 처리

- 예외 상황이 발생했을 경우 웹 컨테이너(톰캣)에서 제공되는 기본적인 예외페이지가 보여진다
- 개발 과정에선 코드도 나오기 때문에 오류 수정하는데 도움이 된다.
- 하지만 사용자에게 이런 딱딱한 페이지가 보여지면 불쾌감과 사이트 신뢰도가 낮아진다
- 그래서 개발자가 따로 만들어 둔 에러 페이지로 유도하여 사용자에게 친숙한 페이지를 보여준다



### HTTP 주요 응답 상태 코드

- 404 : 요청한 URL을 찾을 수 없는 경우
- 500 : 서버측 내부 오류로 인해 페이지가 나타나지 않는 경우 (java, JSP 페이지 내의 코드오류)
- 200 : 요청을 성공적으로 처리함
- 307 : 임시로 페이지를 리다이렉트함
- 400 : 클라이언트의 요청이 잘못된 구문으로 작성됨
- 405 : 요청 방식을 허용하지 않음 (GET, POST 등)
- 503 : 서버가 일시적으로 서버스를 제공할 수 없음 (일시적 서버과부하, 서버 임시 보수 등 )



#### 처리하는 방법

1. **일반적인 자바 try catch 문으로 예외처리 가능**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = request.getParameter("id");

	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% try { %>
<h4><%= id.toUpperCase() %></h4>
<% } catch(Exception e) { %> <!-- 에러가 났을때 톰캣 제공 에러페이지가 아닌 만들어놓은 페이지를 띄워줌 -->
		<h4>죄송합니다. 서버에 오류가 발생했습니다.</h4>
		<p>빠른 시일내에 해결 하겠습니다. </p>
<% } %> 
</body>
</html>
```



2. **디렉티브 로 이동** 

     `<%@ page isErrorPage="true" %> <%-- Exception e랑 같음  --%>    `

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error_page02.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= request.getParameter("id").toLowerCase() %>
</body>
</html>
```

```jsp
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
```



3. **WEB-INF 폴더에 `web.xml` 파일에서 에러페이지 경로 삽입**

```jsp
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1">
  <display-name>JSPBasic</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  
  <error-page>
  	<error-code>404</error-code>
  	<location>/Exception/error_404.jsp</location>
  </error-page>
  
  <error-page>
  	<error-code>500</error-code>
  	<location>/Exception/error_500.jsp</location>
  </error-page>  	
  
  <%-- 특정 예외상황일때 지정한 페이지를 보여주고 싶을때  --%>
  <error-page>
  	<exception-type>java.lang.NullPointerException</exception-type>
  	<location>/Exception/error_null.jsp</location>
  </error-page>
    
</web-app>
```



#### 에러 페이지의 우선순위

- 에러 페이지를 여러 방법으로 지정한 경우 다음의 우선순위에 따라 페이지 선택한다

1. 페이지 지시자 태그의 errorPage속성에 지정 페이지
2. `web.xml`에 지정한 에러 타입에 따른 페이지
3. `web.xml`에 지정한 응답 상태 코드에 따른 페이지
4. 위 3항목에 해당하지 않을 경우 톰캣이 제공하는 에러 페이지 