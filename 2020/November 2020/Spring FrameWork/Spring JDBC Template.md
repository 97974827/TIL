# 1. Spring JDBC 설정

pom.xml 내용추가

- 자바버전, 스프링버전, 서블릿버전 교체

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
		    <groupId>mysql</groupId>
		    <artifactId>mysql-connector-java</artifactId>
		    <version>8.0.15</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/com.zaxxer/HikariCP -->
		<dependency>
		    <groupId>com.zaxxer</groupId>
		    <artifactId>HikariCP</artifactId>
		    <version>2.7.8</version>
		</dependency>
```



root-context.xml

<!-- JDBC, DB 관련 빈을 등록하고 관리하는 설정 파일 -->

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
	
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
	
	<!-- JDBC 템플릿 클래스 빈 등록 -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="ds" />
	</bean>
	
</beans>

```



`servlet_config.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:beans="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

	<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
	
	<!-- Enables the Spring MVC @Controller programming model -->
	<annotation-driven />

	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
	<resources mapping="/resources/**" location="/resources/" />

	<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	
	<!-- 기본 패키지 경로지정 -->
	<context:component-scan base-package="com.spring.database" />
	
	
	
</beans:beans>

```



# 2. JDBC 활용 예제



```java
package com.spring.database.jdbc.board.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.database.jdbc.board.model.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {

	@Autowired
	private JdbcTemplate template; // 의존성 주입
	
	// select 쿼리 내부클래스 
	class BoardMapper implements RowMapper<BoardVO>{
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO board = new BoardVO();
			board.setBoardNo(rs.getInt("board_no"));
			board.setWriter(rs.getString("writer"));
			board.setTitle(rs.getString("title"));
			board.setContent(rs.getString("content"));
			return board;
		}
	}
	
	@Override
	public List<BoardVO> getArticles() {
		String sql = "SELECT * FROM jdbc_board";
		return template.query(sql, new BoardMapper());
	}

	@Override
	public void insertArticle(BoardVO article) {
		String sql = "INSERT INTO jdbc_board (writer, title, content) VALUES (?,?,?)";
		template.update(sql, article.getWriter(), article.getTitle(), article.getContent());
	}

	@Override
	public void deleteArticle(int index) {
		String sql = "DELETE FROM jdbc_board WHERE board_no=?";
		template.update(sql, index);
	}

	@Override
	public BoardVO getContent(int index) {
		String sql = "SELECT * FROM jdbc_board WHERE board_no=?";
		return template.queryForObject(sql, new BoardMapper(), index);
	}

	@Override
	public void modifyArticle(BoardVO article, int index) {
		String sql = "UPDATE jdbc_board SET writer=?, title=?, content=? WHERE board_no=?";
		template.update(sql, article.getWriter(), article.getTitle(), article.getContent(), index);
	}
	
	@Override
	public List<BoardVO> getSearchList(String keyword) {
		String sql = "SELECT * FROM jdbc_board WHERE writer LIKE ? ORDER BY board_no desc";
		return template.query(sql, new BoardMapper(), keyword);
	}
    // queryForObject : Single row를 리턴할 때 사용한다.
	// query : multi row를 리턴할 때 사용함,
    // 가변인자는 항상 마지막에 넣어줌

}

```

