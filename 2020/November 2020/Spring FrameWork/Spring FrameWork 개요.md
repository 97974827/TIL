# Spring FrameWork?

### 개요

- 4버전 부터 REST API 기능 도입 

- 최신 버전은 5버전 
- 설정이 조금 까다로움 
- 레거시 프로젝트 



#### Spring Boot 

- 최신버전 : 2버전
- 부트 프로젝트라고 함 
- 설정할게 기본적으로 다 들어있음 
- 현업에서 아직 활성화 되지 않음 - 스타트업에서 신기술을 많이이용하는 곳에는 많이쓰임 



#### 프레임워크란? - 뼈대를 이루는 코드들의 묶음 

#### 특징

##### POJO(Plain Old Java Object) 기반의 프레임워크

- 자바 객체의 라이프사이클을 스프링 컨테이너가 직접 관리하며, 스프링 **컨테이너**로부터 필요한 객체를 얻어올 수 있다.

##### DI(Dependency Injection) 지원

- 각 계층이나 서비스 들 사이 또는 객체들 사이에 **의존성**이 존재할 경우 스프링 프레임워크가 서로를 연결시켜준다. 

- 이는 클래스들 사이에 약한 결합을 가능케한다.

##### AOP (Aspect Oriented Programming)를 지원 

- 트랜젝션, 로깅, 보안 등 여러 모듈에서 공통적으로 지원하는 기능을 **분리**하여 사용할 수 있다.

#### 확장성이 높다.

- 스프링 프레임워크의 소스는 모두 라이브러리로 분리시켜 놓음으로써 필요한 라이브러리만 가져다 쓸 수 있다. 
- 많은 외부 라이브러리들도 이미 스프링 프레임워크와 연동되고 있다.

##### Model2 방식의 MVC Framework를 지원 

- Front Controller가 이미 만들어져있음 



#### 스프링 컨테이너(IoC)

- 제어의 역전
- 객체를 필요할 때 생성해서 사용하던 방식을 미리생성해 놓고 꺼내 사용하는 형태로 변경



#### Maven 프로젝트 생성 

- **Maven ? - 자바 프로젝트를 서버에서 빌드하는 도구 중 하나**

- 핵심 기능 : 필요한 라이브러리를 특정 문서 **`pom.xml`** 에 정의해 놓으면 네트워크를 통해서 라이브러리들을 

자동으로 다운받아준다.

- **메이븐 리파지토리** : 원하는 라이브러리 검색하여 코드로 써서 다운가능 
  - https://mvnrepository.com/

`pom.xml` 내용기술  - 저장 후 **Alt + F5 **로 메이븐 프로젝트 새로고침 

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/200ㄴ1/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.spring</groupId>
  <artifactId>basic</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
 <!-- 부모태그 dependencies 넣어줘야함   Spring Context 모듈 -->
<dependencies>
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>5.1.5.RELEASE</version>
	</dependency>
</dependencies>
  
  
<!-- 컴파일러에 대한 버전 설정 -->
<build>
	<plugins>
		<plugin>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.5.1</version>
			<configuration>
				<source>1.8</source>
				<target>1.8</target>
				<encoding>utf-8</encoding>
			</configuration>
		</plugin>
	</plugins>
</build>

</project>
```

pom.xml 파일은 메이븐 설정파일로 메이븐은 라이브러리를 연결해주고, 빌드를 위한 플랫폼이다.        ->  한마디로 메이븐을 이용하면 라이브러리를 설정으로 바로 사용할 수 있다

라이브러리는 아래의 경로에서 확인 가능하다.

```
C:\Users\사용자 \.m2\repository\org\springframework 
```

※ 참고로 모듈의 라이브러리 파일명은 artifactId + “-” + 버전명+ “.jar” 로표시된다.

**정전,벼락 상황으로 라이브러리 다운을 못받을때 과감하게 m2파일을 날려버린다** 

- xml 저장으로 다시 생김 



### 버전교체설정

1. **pom.xml**

스프링 5.2.3

서블릿 4.0.0

톰캣은 9.0.39

Junit test 4.7 -> 4.12

plugin maven-compiler-plugin  2.5.1 -> 3.5.1

하위 source, target 1.6 -> 1.8

메이븐 라이브러리 다운



2. 톰캣 설정 기본포트 80 교체 후 , Servers프로젝트 - server.xml  context path=/  수정
3. 한글 수정 web.xml
4. 저장할 떄 no버튼클릭
5. 실행해보기 - helloworld 출력 성공
6. `web.xml` 파일에 9,23번 라인 파일이름 변경`selevt-context.xml`  `root-context.xml` 
7. src/main/resources에 - 새 폴더 만들어서 xml파일 생성 - 마이바티스
8. 데이터베이스, 테이블 만들기
9. 모델 VO 클래스 만들기
10. 마이바티스를 쓸땐 Repository 인터페이스를 만들어야함 



