# Spring Session / Interceptor

#### spring Security 모듈  -> pom.xml에 추가

- 별도 빈 등록은 안해도됨 

```xml
<!-- Spring Security 모듈-->		
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>${org.springframework-version}</version>
</dependency>
```



- 세션객체 - jsp 랑똑같이 사용
  - HttpServletRequest
  - HttpServletResponse
  - HttpSession