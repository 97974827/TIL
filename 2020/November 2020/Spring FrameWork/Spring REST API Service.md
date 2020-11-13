

# Spring REST API Service

[참고 -  REST란? REST API란? RESTful이란?](https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html)

### 목적 : 클라이언트 측에게 데이터 전송



### Rest API의 출발 -> JSON 

- Java Script Object Notation 약자 - **자바스크립트 객체 표기법** (간단하기 때문)
- 스프링 자바 서버(WAS)와 클라이언트 통신을 하려면 서블릿이 자동으로 웹 언어들과 통신하여 이루어준다. 
- 하지만 클라이언트가 안드로이드, IOS, 자바, 코틀린, C 등등.. 다양한 클라이언트들이 등장하고있는데 서블릿만으로는 통신을 할 수 없다.
- 이 클라이언트들과 자바 스프링서버가 통신을 하기위해선 언어적 표준을 맞춰야하는데 이 표준 중 하나가 JSON 역할이다. (기존엔 문자열이나, xml 으로 통신함 : 단점들 보이기 시작함)
- 이러한 JSON 데이터 방식때문에 자바스크립트가 떠오르고있고 프론트엔드 분야도 퍼블리셔와 프론트엔드 이렇게 또 나뉘어 지고있다 
  - **퍼블리셔** : CSS
  - **프론트엔드** : 자바스크립트 



#### 사용법 

- 자바 언어 예
  - 배열 , 리스트   -> JSON에서는 [] 대괄호로 표시함 
    - `List<String> list ` `["A", "B", "C", "D"]`
  - Map, 객체    -> JSON에서 {} 중괄호로 표시함  { "name":"홍길동" , "age":20 }

```
class user {
	String name;
	int age;
}
```

- `List<user> 객체가 3개일떄` 

```json
[ List 1개
	{user},
    {user},
    {user}
]
```



### Spring config

`pom.xml`  -> jackson-databind 라이브러리 추가

```xml
<!-- jackson-databind : 데이터를 JSON형태로 파싱해주는 라이브러리 -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.10.1</version>
</dependency>
```

- **따로 빈등록을 할 필요없고 바로 클래스를 이용하여 사용가능 하다** 



### RestController 메서드 호출 방식 

```java
package com.spring.mvc.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController // 스프링 4 이상부터 사용가능 - ResponseBody를 안써도 됨 
@RequestMapping("/rest")
public class RestControllerTest {
	
	/*
	 * 
	 # @ResponseBody
	 - 리턴 데이터를 ViewResolver에게 전달하지 않고
	 클라이언트에게 해당 데이터를 바로 응답하게 한다.
	 
	 */
	
	@GetMapping("/hello")
	@ResponseBody
	public String Hello() {
		return "Hello World!"; // TODO : 결과 문자열을 바로 쏴줌
	} // Hello World!
	
	@GetMapping("/hobbys")
	@ResponseBody
	public List<String> hobbys(){
		List<String> hobbys = Arrays.asList("축구", "수영", "음악감상");
		return hobbys;
	} // ["축구","수영","음악감상"]
	
	@GetMapping("/study")
	@ResponseBody
	public Map<String, Object> study(){
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "Java");
		subject.put("JSP", "Java Server Pages");
		subject.put("Spring", "Spring FrameWork");
		return subject;
	} // {"JSP":"Java Server Pages","자바":"Java","Spring":"Spring FrameWork"}
	
	@GetMapping("/person")
	@ResponseBody
	public Person person() {
		Person p = new Person();
		p.setName("김철수");
		p.setAge(30);
		p.setHobbys(Arrays.asList("수영", "탁구", "요가"));
		return p;
	} // {"name":"김철수","age":30,"hobbys":["수영","탁구","요가"]}

	
}

```



### RESTAPI 구축 방식 

1. VO 모델 클래스 설계 

2. Mapper (DB) 클래스 구축 
3. Service 클래스 구축
4. 컨트롤러 클래스 구축 전 DB JUnit 테스트 (안정적으로 돌리려면 하는게 좋긴함)
5. 컨트롤러 클래스 구축
6. 클라이언트 테스트 



