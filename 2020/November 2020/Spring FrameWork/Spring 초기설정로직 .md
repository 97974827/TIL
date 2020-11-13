# Spring 초기설정로직 (중요함)

##### 경로

```
/SpringWebMvcProject/src/main/webapp/WEB-INF/spring/mvc-config.xml
/SpringWebMvcProject/src/main/webapp/WEB-INF/spring/servlet-config.xml
/SpringWebMvcProject/src/main/webapp/WEB-INF/web.xml
/SpringWebMvcProject/pom.xml
```



항상 뒷단 부터 만들어놔야함 `DB - 서버 - UI`

1. **`pom.xml`**  version 설정
   
   - Spring `5.2.3`
   
   - servlet `4.0.0`
   
   - tomcat `9.0.39`
   
   - Junit test 4.7 -> `4.12`
   
   - plugin maven-compiler-plugin  2.5.1 -> `3.5.1`
   
   - 하위태그 source, target 1.6 -> `1.8`
   
   - 메이븐 외부 라이브러리 다운
   
     

2. 톰캣 설정 기본포트 80 교체 후 , `Servers프로젝트 - server.xml`  
3. context path=/  수정
4. 서버 동기화 누를 때 no 누르기 - yes하면 변경 반영이 안됨
5.  utf-8 한글 수정 `web.xml`
6. spring폴더의 설정파일 이름변경 후 `web.xml`에서 수정 - `web.xml` 파일에 `9,23번 라인 파일이름 변경` `selevt-context.xml`  `root-context.xml` 
7. 실행해보기 - helloworld 출력 성공 **(항상 설정이 끝나면 실행해보기)**
8. src/main/resources에 - 새 폴더 만들어서 sql파일 생성  - `백업용도`
9. 데이터베이스, 테이블 만들기
10. 모델 VO 클래스 만들기
11. 마이바티스를 쓸땐 Repository 인터페이스를 만들기
12. DB 빈 등록 root-context.xml  원본

    - 빈 등록 하면서 빈즈그래프에 저장됬나 확인하기
    - 히카리 커넥션풀,  데이터 소스, 마이바티스, mapper 인터페이스 경로 스캔
13. scr/main/resources에 새폴더 mapper생성 뒤 xml파일생성 - 인터페이스 설계 xml파일

    - 파일 이름형식 : *Mapper.xml
14. *Mapper.xml - 인터페이스 mapper namespace="풀경로" 지정 
15. JUnit test : src/test/java

    - DB 컨넥션 테스트 클래스 만들어 별도 테스트 하기
16. 컨넥션 성공하면 CRUD 순차적으로 테스트 - **`Insert->select->update->delete`**
    1. Mapper 구현클래스 작성  `*Mapper.xml`
    2. Test 클래스 작성 - 단 테스트 시 저장 후 outline에서 단일 컴파일 테스트 진행하기!
17. DB 테스트가 끝나면 서비스 클래스(인터페이스, 구현클래스) 작성 튀 컨트롤러 작성하기 
18. UI 만들어 마지막 테스트 
19. UI 꺠질때 `servlet-context.xml` 수정 
    - 정적자원 (html, css, img, js) static resources 등을 URI 절대경로로 사용하기 위한 매핑처리 
    - `src/main/webapp/resources/하위폴더들` 15번라인 밑 전부 추가 

```xml
<!-- 정적자원 static resources 등을 URI 절대경로로 사용하기 위한 매핑처리 -->
<!-- mapping : 어떤 uri로 매핑할 것인가 , location : 경로 -->
<resources mapping="/resources/**" location="/resources/" />
<resources mapping="/css/**" location="/resources/css/" />
<resources mapping="/js/**" location="/resources/js/" />
<resources mapping="/img/**" location="/resources/img/" />
<resources mapping="/scss/**" location="/resources/scss/" />
<resources mapping="/vendor/**" location="/resources/vendor/" />
```







### Junit Test 파일 만들기 

```java
package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})

public class PagingAlgorithmTest {
	
	/*
	 *** 페이징 알고리즘 만들기
	 
	  1. 사용자가 보게 될 페이지 화면 
	  - 한 화면에 페이지를 10개씩 끊어서 보여준다면?  
	  ex) 1 2 3 4 ... 9 10 [다음] // [이전] 31 32 33 ... 39 40 [다음]
	  
	  - 만약에 총 게시물의 수가 67개라면?
	  ex) 1 2 3 4 5 6 7 // 이전, 다음 비활성화  
	  
	  - 총 게시물 수가 142이고 현재 12페이지에 사용자가 머물러 있다면?
	  ex) [이전] 11 12 13 14 15
	  
	 */
	
	// 메서드 테스트 
	@Test
	public void test() {
		
	}
}

```

