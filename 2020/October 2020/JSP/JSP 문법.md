### jsp 파일 생성하여 Java코드 주입



#### JSP 태그의 개념 이해 

- Servlet은 java언어를 이용하여 문서를 작성하고, 출력객체(PrintWriter)를 이용하여 html 코드를 삽입합니다. 
- jsp는 서블릿과 반대로 html코드에 java언어를 삽입하여 동적 문서를 만들 수 있습니다.
- html코드 안에 java코드를 삽입하기 위해서는 jsp태그를 이용해야 하며, 이러한 태그를 학습해야 합니다.
- **웹 브라우저 실행결과** 
  - `웹 코드만 보이고 자바코드는 보이지 않았음 `



#### JSP 태그의 종류  (문법)

1. 스크립트릿(scriptlet): **`<%   %>`** 거의 모든 자바코드 기술 가능. 

   - 메서드 안이라고 생각하면됨
   - import, 외부 class, 메서드, class 멤버변수, 접근지정자 는 안됨 
   - 기술 가능한 내부 코드를 작성 할 수 있다 
   - `System.out.prinln()` -> 자바 콘솔에 출력됨 
   - `out.println() `              -> 웹 브라우저에 출력됨 

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

	
	<h2>이클립스와 JSP파일로 렌더링 하기 </h2>
	<p> 안녕하세요. 오늘은 2020년 10월 14일 수요일입니다. <br> 스크립트 연습을 하고 있습니다. </p>
	<hr>
	
	<h3>구구단 3단 출력</h3>
	<% 
		for(int j=1; j<10; j++){  
			out.println("3 x " + j + " = " + (3*j) + "<br>" );
		} 
	%>
</body>
</html>
```



2. 지시자(directive): **<%@  %>** 페이지 속성을 지정. 

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
	<%-- JSP 외부 import 방법 --%>
	<%@ page import="java.util.Date" %>
	
	<%-- JSP 주석입니다. --%>
	
	<%
		Date date = new Date();
		out.println("날짜 : " + date.getYear());
		out.println("날짜 : " + date.getMonth());
		out.println("날짜 : " + date.getDay());
	%>
	
</body>
</html>
```



3. 선언자(declaration): **<%!  %>** 변수나 메서드 선언시 사용. 

- 자바에선 클래스 블록 내부에서 선언한 것과 같은 의미 

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
```



4. 표현식(expression): **<%=  %>** 결과 값을 출력할 때 사용. 
   - 표현식은 `out.print()` 을 대체 한다.

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

	<%!	
		public String name = "강현진";
	%>
	
	<%
		String addr = "광주광역시";
	%>
	
	<%= "이름 :" + name + "<br>"%>
	<%= "주소 :" + addr + "<br>"%>
		

</body>
</html>
```



5. 주석(comments): **<%--   --%>** 코드 주석처리 시에 사용.





\* 스크립트릿(Scriptlet) 

- JSP페이지에서 JAVA언어를 사용하기 위한 요소 중 가장 많이 사용하는 태그입니다. 

- 스크립트릿 안에는 우리가 알고 있는 거의 모든 JAVA코드를 사용할 수 있습니다. 



\* 선언(Declaration) 

- JSP 페이지 내에서 사용되는 변수 또는 메서드를 선언할 때 사용합니다. 
- 여기 선언된 변수 및 메서드는 전역의 의미로 사용됩니다.



\*표현식(Expression) 

- JSP 페이지 내에서 사용되는 변수의 값 또는 메서드 호출 결과 값을 출력하기 위해 사용됩니다. 
- 결과 값의 데이터 유형은 String이며, 세미콜론(;)을 사용할 수 없습니다. 
- 표현식은 out.println()을 대체합니다. 



\*주석(Comments) 

- 실제 프로그램 실행에는 영향이 없고, 프로그램 설명 등의 목적으로 사용되는 태그입니다.
- HTML 및 JSP 주석이 각각 별도로 존재합니다. - HTML 주석은  표기하고, 웹 브라우저에서 페이지 소스보기를 하면 주석도 표기됩니다.  
- JSP 주석은 <%-- --%> 표기하고, 웹 브라우저에서 소스보기를 해도 나타나지 않습니다. - JAVA의 주석도 혼용 사용 가능합니다. 



\*지시자(directive) 

- JSP페이지의 전체적인 속성을 지정할 때 사용합니다. 

1. page: JSP페이지에 대한 정보를 지정한다.

-  JSP가 생성하는 문서의 타입, 출력 버퍼의 크기, 에러 페이지 등 페이지에서 필요로 하는 정보를 설정합니다. 

  

- 페이지 디렉티브에 선언 될 수 있는 속성들 

- a. language - 언어를 지정합니다. java만 지정할 수 있습니다. 

- b. import - 패키지를 import할 때 사용합니다. 

- c. errorPage - 설정 된 페이지에서 에러가 발생했을 시에 미리 만들어 둔 에러 페이지를 호출합니다. 

- d. contentType - text/html이라고 쓰면 결과가 HTML문서로 만들어집니다. 

- e. pageEncoding - 출력 문자 인코딩입니다. 기본은 ISO-8859-1이며 한글을 사용하려면 utf-8로 지정합니다. 

- f. trimDirectiveWhitespaces - 디렉티브나 스크립트릿 코드로 인해서 만들어진 공백 문자를 제거하는 기능. 



2. include: JSP 페이지의 특정 영역에 다른 문서를 포함시킵니다. 
3. taglib: JSP 페이지에서 사용할 태그 라이브러리를 지정합니다.





#### JSP 아키텍쳐 

- .jsp파일을 실행(요청)하면 웹 서버에서 우선 jsp파일을 java코드로 변환합니다. 
- 그 이후 그 파일을 컴파일하고 html로 응답합니다. 
- ex) helloworld.jsp -> helloworld_jsp.java(서버에서 servlet화) -> helloworld_jsp.class(서블릿 클래스 컴파일)