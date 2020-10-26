### JSP MVC Model 1 Architecture



#### MVC model 탄생 개요

- 클라이언트 - WAS 통신 : 프론트엔드 역할
- WAS - DB 통신 : 백엔드 역할
- 자바 WAS는 프로그램을 돌리면 결국 자바 클래스로 변해 서블릿으로 활용된다 
- 그러나 이렇게 서비스를 이용하면 WAS 가 하는역할이 너무 많아진다. 그래서 자바의 특성을 살린 객체지향 접근법을 통한 MVC 모델이 탄생 



**`Controller`**  - 두뇌역할

**`Model`** : DB 요청을 전담하는 클래스 , 결과를 Controller에 전달함 

**`View`** :  각 상황에 맞는 UI

<img src="./MVC 개념.PNG">

### 구현 방법

- **MVC Model 1 Architecture** 
  - 작은 프로그램에 사용
  - Model 부분만 따로 뺴서 구현하는 것 
- **MVC Model 2 Architecture**
  - 큰 프로그램에 사용



#### MVC Model?

- DAO (Data Access Object) 클래스
  - DB 접속하여 데이터의 추가, 삭제, 수정 등의 작업을 하는 클래스이다.
  - DB 테이블로부터 데이터를 읽어와 자바 객체로 변환하거나 자바 객체의 값을 테이블에 저장한다.
  - 보통 한 개의 테이블 마다 한개의 DAO 클래스를 작성함 
  - DAO를 구현하려면 테이블의 컬럼과 매핑되는 값을 갖는 자바빈 클래스를 항상 작성해야한다.  **이 자바빈 클래스를 VO 클래스라** 부른다.
- VO (Value Object) 클래스 / DTO (Data Transfer Object) 클래스
  - DB연동을 원활하게 하기위함 
  - 데이터들을 포장해논 객체 
  - 일반 변수보다 객체에 저장해서 원활하게 작업가능함 



#### 이상적인 구조

- 자바 클래스 : DB 통신 / 주요 비즈니스 로직 구현
- 서블릿 : 사용자의 요청 처리/제어 컨트롤러 구현
- JSP : 화면에 보이는 페이지 작성 



### Connection Pool?

개요

- 사용자가 많아지게 되면 커넥션 객체를 일일히 그떄 그떄 만들어서 생성해서 없애는 방식으로 하면 시스템에 부하가 생기기때문에 커넥션 풀을 사용함



#### 설정 방법

- 이클립스 - Servers탭  - tomcat 폴더 - `context.xml`에 context 마지막에 아래 내용 추가 

```xml
<!-- Connection Pool 설정 -->
    <Resource
    	auth="Container"
    	driverClassName = "com.mysql.cj.jdbc.Driver"
    	url = "jdbc:mysql://localhost:3306/jsp_practice?serverTimezone=UTC"
    	username = "root"
    	password = "mysql"
    	name = "jdbc/mysql"
    	type = "javax.sql.DataSource"
    	maxActive = "300"
    	maxWait = "1000" 
    />
```



- [참고사이트 : jsp & mysql 커넥션 풀 ](https://ecsimsw.tistory.com/entry/%EC%BB%A4%EB%84%A5%EC%85%98-%ED%92%80-DBCP)

- 서버 설정이 바뀌면 항상 Servers 탭에 Republish -> synchronized 확인 