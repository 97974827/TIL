# Spring Mybatis

이클립스 

- 컨트롤 + a + i : 줄 맞추기 

  

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



#### 설정 (항상 수정 후 저장, Alt+F5(프로젝트 반영))

##### 1. pom.xml 내용추가 

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis --> 마이바티스
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.6</version>
</dependency>

마이바티스 스프링
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring --> 
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>1.3.2</version>
</dependency>
```



##### 2. root-context.xml 내용추가

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
	xsi:schemaLocation="http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring-1.2.xsd
		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
	
	<!-- JDBC, DB 관련 빈을 등록하고 관리하는 설정 파일 -->
	
	<!-- 히카리 커넥션을 빈 등록  -->
	<bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig">
		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
		<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul" />
		<property name="username" value="root"/>
		<property name="password" value="mysql"/>
	</bean>
	
	<!-- 히카리 데이터 소스 빈 등록 -->
	<bean id="ds" class="com.zaxxer.hikari.HikariDataSource">
		<constructor-arg ref="hikariConfig"/>
	</bean>
	
	<!-- 템플릿과 마이바티스 중 하나만 결정해서 등록함 -->
	<!-- JDBC 템플릿 클래스 빈 등록 -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="ds" />
	</bean>
	
	<!-- 마이바티스 SQL동작을 위한 핵심 객체 빈 등록  SqlSessionFactory-->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="ds"/>
		<property name="mapperLocations" value="classpath:/mappers/**/*Mapper.xml" />
	</bean>
	
	<!-- 마이바티스 sql파일을 빈으로 등록하기 위한 스캔 설정 : @Repository 설정 -->
	<mybatis-spring:scan base-package="com.spring.database.mybatis.score.repository"/>
	
</beans>

```



##### 3. src/main/resources 디렉토리에 새 폴더(mappers/score)만들고 xml생성

- 이 파일은 SQL 구현클래스 처럼 작성하면 된다 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
			"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	
<!-- 구현 클래스 설계 부분 -->		
<mapper namespace="com.spring.database.mybatis.score.repository.IScoreMapper">
	
	<!-- 오버라이딩 구현 -->
	
	<!-- DB컬럼명과 VO의 필드명을 맞추는 ResultMap 선언 -->
	<resultMap id="ScoreMap" type="com.spring.database.mybatis.score.model.ScoreVO">
		<id property="stuId" column="stu_id" /> <!-- primary keㅛ 매핑 -->
		<result property="stuName" column="stu_name"/>
		<result property="kor" column="kor"/>
		<result property="eng" column="eng"/>
		<result property="math" column="math"/>
		<result property="total" column="total"/>
		<result property="average" column="average"/>
	</resultMap>
	
	<!-- 점수 등록기능 -->
	<insert id="insertScore">
		INSERT INTO scores (stu_name, kor, eng, math, total, average)
		VALUES (#{stuName}, #{kor}, #{eng}, #{math}, #{total}, #{average}) <!-- 자바 변수 주입 -->
	</insert>
	
	<!-- 점수 목록 조회기능  -->
	<select id="selectAllScores" resultMap="ScoreMap">
		SELECT * FROM scores
	</select>
	
	<!-- 점수 삭제 기능 -->
	<delete id="deleteScore">	
		DELETE FROM scores WHERE stu_id=#{stuId}
	</delete>
	
	<!-- 점수 개별 조회기능 -->
	<select id="selectScore" resultMap="ScoreMap">
		SELECT * FROM scores WHERE stu_id=#{stuId}
	</select>
	
	
	
</mapper>
```



