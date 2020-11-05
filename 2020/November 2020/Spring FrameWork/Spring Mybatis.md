# Spring Mybatis

#### 장점

- 인터페이스만 설계하고 구현클래스는 직접 만들지 않아도 된다 
- JdbcTemplate 보다 간편하게 지원
- 깔끔한 소스코드
- ResultSet - RowMapper 셋팅 안해줘도됨



#### 개념

- SQL 고급 매필을 지원하는 프레임워크

- JDBC 코드오 ㅏ수동으로 셋팅하는 파라미터와 결과 매핑을 제거 

  - ? 자리를 알아서 채워줌

- DAO계층을 대신함 

  - 기존 DAO의 Interface의 구현클래스를 xml파일이 대신함 

- **스프링에서 사용하려면 `MyBatis-Spring Module`을 다운받아야 한다.** - 스프링 전용은 아님

- #### 마이바티스 사용 시 기본적으로 spring-jdbc 라이브러리가 있어야 함 

  - `SQLSessionFactory` 객체 빈등록

