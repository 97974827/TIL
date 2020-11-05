# JSP MVC Model 2 Architecture

#### EL (Expression Language)

- EL은 일종의 스크립트 언어로  자료타입, 수치연산자, 논리연산자, 비교연산자 등을 제공하며 표현식을 대체할 수 있다.

- 사용이유 : 자바 & HTML 코드를겹쳐쓰면 지저분해보임 , 자바 코드깔끔하게 쓰는방법 

- **EL 태그로 자바의 객체를 활용하려면 request, session, application, 객체에 자바의 객체를 저장시켜야한다!**

- 사용법

  - ```jsp
    // 출력
    <%= value %>; // EL $(value)  ㅇ
    
    request.getParameter("name")
    // 리퀘스트 객체 대신 사용가능 
    이름 : ${param.name }<br> // getter를 만들어놨으면 바로 속성값 떙길수 있음 
    ```



#### JSTL (JSP Standard Tag Library)

- JSP 경우 HTML 태크오 같이 사용되어 전체적인 코드의 가독성이 떨어짐
- 이런 단점을 보완하고 만들어진 것이 JSTL이다
- JSTL을 사용하면 자바의 제어문을 HTML 태그화 시킬 수 있다.
- JSTL의 경우, 우리가 사용하는 Tomcat 기본 컨테이너에 포함되어 있지 않으므로, 별도의 라이브러리를 설치하고 사용한다. 

- 번외 
  - 스프링에서는 코드로 다운로드를 해버림 
  - JSP에서만 필요 



설치방법

1. `http://jakarta.apache.org` 접속
2. 좌측 **Taglibs** 클릭
3. 링크 Apache Standard Taglib 클릭
4. 1.1 버전 다운클릭 (안정화버전) 
5. 폴더 `binarys - jakarta-taglibs-standard-1.1.2.zip`  다운 



#### JSTL Core Library

- 코어 라이브러리는 기본적인 라이브러리로 출력, 제어문, 반복문 같은 기능이 포함되어 있다 
- 코어 라이브러리를 사용하기 위해서 반드시 JSP 파일내의 지시자 태그로

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> // 디렉티브선언
```



라이브러리 위치 : 이클립스 프로젝트 패키지 - **WebContent - WEB-INF - lib** 



##### 주요문법

```jsp
// 조건문 
<c:if test="${param.name == '홍길동'}">
    <p>name 파라미터의 값은 ${ param.name }입니다.
</c:if>
<c:if test="${param.name == '이순신'}">
    <p>name 파라미터의 값은 ${ param.name }입니다.
</c:if>

// 중첩 조건문 
<c:choose>
    <c:when test="${ param.name == '김철수' }">  <%-- if --%>
        <p>당신의 이름은 김철수입니다.</p>
    </c:when>
    <c:when test="${param.age > 19 }">          <%-- else if --%>
        <p>당신은 성인입니다.</p>
    </c:when>
    <c:otherwise>								<%-- else --%>
        <p>당신의 이름은 김철수가 아니고 미성년자입니다.</p>
    </c:otherwise>
</c:choose>
```



```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${param.age >= '90' }">
			<p>당신의 학점은 "A" 입니다.</p>
		</c:when>
		<c:when test="${param.age >= '80' }">
			<p>당신의 학점은 "B" 입니다.</p>
		</c:when>
		<c:when test="${param.age >= '70' }">
			<p>당신의 학점은 "C" 입니다.</p>
		</c:when>
		<c:when test="${param.age >= '60' }">
			<p>당신의 학점은 "D" 입니다.</p>
		</c:when>
		<c:otherwise>
			<p>당신의 학점은 "F" 입니다.</p>
		</c:otherwise>
	</c:choose>
</body>
</html>
```



반복문

```jsp
<%--
    int sum = 0;
    for(int n=1; n<=100; n++){
        sum += n;
    }
    out.print("<h4>1부터 100까지 누적합 : " + sum + "</h4>");
--%>

<h4>1부터 100까지의 합 </h4><br>
<c:set var="sum" value="0" />  				// 변수선언
<c:forEach var="n" begin="1" end="100" step="1"> // 반복문 작성 (step=기본값 1증가)
    <c:set var="sum" value="${sum + n}" />  // 
</c:forEach>
<p>1~100 까지의 누적합 : ${sum}</p>
```

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		int sum = 0;
		for(int n=1; n<=100; n++){
			sum += n;
		}
		out.print("<h4>1부터 100까지 누적합 : " + sum + "</h4>");
	--%>
	
	<h4>1부터 100까지의 합 </h4><br>
	<c:set var="sum" value="0" />
	<c:forEach var="n" begin="1" end="100">
		<c:set var="sum" value="${sum + n}" />
	</c:forEach>
	<p>1~100 까지의 누적합 : ${sum}</p>
	
	<h4>구구단 4단</h4><hr>
	<c:set var="num" value="4" />
	<c:forEach var="i" begin="1" end="9">
		<p>${num} x ${i} = ${num*i}</p>
	</c:forEach>
	
	<c:forEach var="dan" begin="2" end="9">
		<c:forEach var="i" begin="1" end="9">
			<c:if test="${i=='1'}">
				<br>
			</c:if>	
			<p>${dan} x ${i} = ${dan*i}</p>
		</c:forEach>
	</c:forEach>
    
    // 향상된 루프 
    <c set var="arr" value="<%= new int[] {1,3,5,7,9} %>" />
    <c:forEach var="i" items="${arr}">
    	${i} &nbsp;
    </c:forEach>
	
</body>
</html>
```



### MVC model2 전체적인 설계구조

- **Front Controller** 하나에 컨트롤러에 의해서 운영됨
  - **모든 요청을 받고 처리하게 되는 시스템이다. (진입점)**
  - 생성은 new - servlet 으로 생성 
- .jsp 파일은 View 역할로만 만들어져야함 
  - **jsp 파일은 웹서버로 들어오면 자바 클래스로 변함 - (서블릿이라 부름) **
  - 앞으로는 서블릿 클래스를 직접 구현해야함 
- VO 클래스는 DB의 데이터들을 손쉽게 관리해주는 클래스
- DAO 클래스는 DB 데이터에 직접적으로 접근하는 클래스 
- **MVC model 2 방법은 자주 사용하기 때문에 면접 질문에서 나올수 있다 준비하자!**



#### 서블릿이란?

- 자바 클래스 (HttpServlet 클래스)
- `hello.jsp` -> 웹서버로 실행 시 -> `hello_jsp.java`로 변경 
- 기능이 복잡해지면 서블릿 으로 보통 직접 구현함 
- **하나의 컨트롤러  = 서블릿**

<img src="./MVC 패턴 구조.PNG">

#### 구현방법

```java
package ko.co.koo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

// URL Mapping : 페이지의 URL을 사용자 정의로 지정하는 방식 - 자바 코드 노출 보안상 이유 
@WebServlet("/apple")
public class ServletBasic extends HttpServlet{  // 서블릿 클래스 선언방법 : HttpServlet 클래스 상속받음
	
	// 기본 생성자 선언
	public ServletBasic() {
		System.out.println("apple페이지 서블릿 객체 생성");
	}
	
	// HttpServlet 클래스에서 상속받은 메서드들을 오버라이딩한다.
	// init(), doGet(), doPost(), destroy() 등
	
	
	@Override
	public void init() throws ServletException {
		/*
		 페이지 요청이 들어왔을 때 처음 실행할 로직을 작성
		 init()메서드는 컨테이너에 의해 서블릿 객체가 생성될 때 
		 최초 1회 자동 호출된다.
		 생성자가 하는 역할을 대신함 
		 */
		System.out.println("init 메서드 호출");
	} 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Http Get요청이 들어왔을 때 호출되는 메서드
		System.out.println("doGet메서드 호출");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Http Post요청이 들어왔을 때 호출되는 메서드
		System.out.println("doPost메서드 호출");
	}
	
	@Override
	public void destroy() {
		// 서블릿 객체가 소멸할 때 호출되는 메서드 (객체 소멸시 1회호출)
		System.out.println("destory 메서드 호출");
	}
	
}
```



#### URL-Pattern 

* 1. 디렉토리 패턴: 디렉토리 형태로 서버의 해당 컴포넌트를 찾아서 실행하는 구조입니다. 
     - ex) http://localhost:8181/cr/Hello --> /Hello 서블릿    
     - http://localhost:8181/cr/World --> /World 서블릿 
  2. 확장자 패턴: 확장자 형태로 서버의 해당 컴포넌트를 찾아서 실행하는 구조입니다. 
     - ex) http://localhost:8181/cr/Hello.do --> *.do 서블릿    
     - http://localhost:8181/cr/World.do --> *.do 서블릿
     - **단, 확장자 이름은 마음대로 정할 수 있다 ** 



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
	<a href="/MVCModel2Basic/join.do">회원 가입 요청 </a><br>
	<a href="/MVCModel2Basic/login.do">로그인 요청 </a><br>
	<a href="/MVCModel2Basic/update.do">정보 수정 요청 </a><br>
	<a href="/MVCModel2Basic/delete.do">회원 탈퇴 요청 </a><br>
</body>
</html>
```



`FrontController.java`

```java
package kr.co.koo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 요청발생!");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		System.out.println(conPath);
		System.out.println(uri);
		String com = uri.substring(conPath.length());
		
		
		if(com.equals("/join.do")) {
			System.out.println("회원가입 요청이 들어옴");
		} else if (com.equals("/login.do")) {
			System.out.println("로그인 요청이 들어옴");
		} else if (com.equals("/update.do")) {
			System.out.println("정보 수정 요청이 들어옴");
		} else if (com.equals("/delete.do")) {
			System.out.println("회원 탈퇴 요청이 들어옴");
		} 
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}

```



