# Spring - Controller에서 요청 파라미터 처리 

#### post 방식 한글 처리

- web.xml 파일 수정
- web-app 태크안에 아래 코드 삽입

```xml
<!-- 한글 인코딩 필터 설정 (톰캣 내부의 한글 처리 -->
	<filter>
	    <filter-name>encodingFilter</filter-name>
	    <filter-class>
				org.springframework.web.filter.CharacterEncodingFilter
			</filter-class>
	    <init-param>
	      <param-name>encoding</param-name>
	      <param-value>UTF-8</param-value>
	    </init-param>
	    <init-param>
	      <param-name>forceEncoding</param-name>
	      <param-value>true</param-value>
	    </init-param>
    </filter>
    <!-- 위에 지정한 encodingFilter이름을 모든 패턴에 적용 -->
    <filter-mapping>
	    <filter-name>encodingFilter</filter-name>
	    <url-pattern>/*</url-pattern>
    </filter-mapping>
```







#### 메서드 처리방식

```java
package com.spring.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.web.model.UserVO;

// 클라이언트에서 요청이 왔을 떄 처리하는 컨트롤러 

// 자동으로 스프링 컨테이너에 해당 클래스의 빈을 등록하는 어노테이션 
@Controller
@RequestMapping("/request")
public class RequestController {
	
	public RequestController() {
		System.out.println("RequestCon 작동");
	}
	
	// RequestMapping : 어떤 URL로 이 메서드를 동작 시킬것 인가 대한 설정
	@RequestMapping("/test") // 메소드처리방식 지정하지 않을시 GET,POST 둘다 처리함 
	public String testCall() {
		System.out.println("/request/test 요청이 들어옴 ");
		return "test";
	}
	
	@RequestMapping("/req")
	public String reqEx01() {
		System.out.println("/request/req 요청들어옴 ");
		return "request/req_ex01";
	}
	
	@RequestMapping(value="/basic01", method=RequestMethod.GET)
	//@GetMapping("/request/basic01")
	public String basicGet() {
		System.out.println("/request/basic01 요청이 들어옴 : GET");
		return "request/req_ex01";
	}
	
	@RequestMapping(value="/basic01", method=RequestMethod.POST)
	//@PostMapping("/request/basic01")
	public String basicPost() {
		System.out.println("/request/basic01 요청이 들어옴 : POST");
		return "request/req_ex01";
	}
	
	// 요청 파라미터 받아오기
	@RequestMapping("/param")
	public String paramTest(HttpServletRequest request) {
		System.out.println("/request/param 요청 : GET");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		return "";
	}
	
	// 컨트롤러의 요청메서드를 void 리턴타입으로 지정
	@RequestMapping("/req-ex02")
	public void reqEx02() { // void형 메서드 : uri와 똑같은 jsp파일을 찾음 (다를떈 String형 메서드로 열어줄 파일명 리턴해야함)
		System.out.println("/request/req-ex02 요청");
	}
	
	/////////////////////////////////////////////////////////////////
	
	@GetMapping("/join")
	public void register() {
		System.out.println("/request/join : GET");
	}
	
	// 1. 전통적인 jsp/servlet의 파라미터 읽기처리 방법
	/*@PostMapping("/join")
	public String register(HttpServletRequest request) {
		System.out.println("/request/join : POST");
		System.out.println("ID    : " + request.getParameter("userId"));
		System.out.println("PW    : " + request.getParameter("userPw"));
		System.out.println("NAME  : " + request.getParameter("userName"));
		System.out.println("HOBBY : " + Arrays.toString(request.getParameterValues("hobby")));
		
		return "request/join";
	}*/
	
	// 2. @RequestParam을 이용한 요청 파라미터처리
	
	/*@PostMapping("/join")
	public void register(@RequestParam("userId") String id, 
						 @RequestParam("userPw") String pw, 
 						 @RequestParam("userName") String name,
						 @RequestParam(value="hobby", required=false, defaultValue="no hobby person") List<String> hobbys) {
		
		System.out.println("ID    : " + id);
		System.out.println("PW    : " + pw);
		System.out.println("NAME  : " + name);
		System.out.println("HOBBY : " + hobbys.toString());
		
	}*/
	
	
	// 3. 커맨드 객체를 활용한 처리
	// model 클래스만 만들고 setter, getter 만들면 알아서 값 주입
	@PostMapping("/join")
	public void register(UserVO user) {
		System.out.println(user.getUserId());
		System.out.println(user.getUserPw());
		System.out.println(user.getUserName());
		System.out.println(user.getHobby());
		
	}
	
	@RequestMapping(value="/quiz", method=RequestMethod.GET)
	public String quiz() {
		return "request/req-quiz";
	}
	
	@PostMapping("/quiz")
	public String quiz(@RequestParam("userId") String id, 
					 @RequestParam("userPw") String pw) {
		
		if (id.equals("abc1234") && pw.equals("xxx4321")) {
			return "request/login-success";
		} else {
			return "request/login-fail";
		}
	}
	
}

```



