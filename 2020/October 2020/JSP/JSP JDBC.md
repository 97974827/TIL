### JSP JDBC 



### SQL

```mysql
 CREATE USER 'jsp' IDENTIFIED BY 'jsp';
 GRANT ALL PRIVILEGES ON jsp_practice.* TO 'jsp';

CREATE TABLE member(
	id VARCHAR(20) primary key,
	pw VARCHAR(20) NOT NULL,
	NAME VARCHAR(30) NOT NULL,
	email VARCHAR(80)
);

SELECT * FROM member;
# INSERT INTO member (id,pw,NAME) VALUES('abc123', '1234', '홍길동');
# INSERT INTO member VALUES('def456', '4433', '이순신', 'aaa@bbb.com');
INSERT INTO member VALUES('weef777', '5687', '이순신', 'ca@bbdasdb.com');
INSERT INTO member VALUES('sadf222', '2020', '장보고', 'hon1@sad.com');
INSERT INTO member VALUES('eqq444', '2223', '유관순', '123333@sed.com');
INSERT INTO member VALUES('dsadfsdef456', '6655', '강감찬', 'kang1@naver.com');
INSERT INTO member VALUES('deasdfsdafsdaff', '5687', '안창호', 'ann3344@sasa.co.kr');
INSERT INTO member VALUES('flashWKDWKDaos', '8484', '이봉창', 'mrlee00@yahoo.co.kr');
INSERT INTO member VALUES('elqldjsdj', '3333', '전봉준', 'sksmsshren@zmzm.com');
INSERT INTO member VALUES('myWKd11', '4666', '각시탈', 'rkrtlxkfdlek@hanmail.net');
INSERT INTO member VALUES('apfhd882', '5365', '최무식', 'choi9876@daum.net');

SELECT * FROM member WHERE email LIKE '%naver%'

SELECT * FROM member ORDER BY name ASC;

SELECT * FROM member WHERE NAME LIKE '이%' ORDER BY id ASC;

INSERT INTO member VALUES ('가가가123', '123', '가가가', '가가가');
COMMIT;
INSERT INTO member VALUES ('나나나123', '123', '나나나', '나나나');
INSERT INTO member VALUES ('다다다123', '123', '다다다', '다다다');
ROLLBACK;
SELECT * FROM member;

# 내부 데이터 삭제 
TRUNCATE TABLE member; 

# 테이블 행 추가 
ALTER TABLE member add addr VARCHAR(100);

ALTER TABLE member DROP email;

```



#### 트랜젝션 처리

- insert, update, delete 문 수행 후 **commit (변경된 데이터를 최종작업수행)**을 수행하면 저장된다 
- DML 쿼리를 잘못 날렸을 경우 ROLLBACK 을 수행하여 이전 커밋단계로 되돌린다. **단,  DML 쿼리 조작문만 가능함**



### [참고 : JDBC 드라이버 에러](https://yenaworldblog.wordpress.com/2018/01/24/java-mysql-%EC%97%B0%EB%8F%99%EC%8B%9C-%EB%B0%9C%EC%83%9D%ED%95%98%EB%8A%94-%EC%97%90%EB%9F%AC-%EB%AA%A8%EC%9D%8C/)

#### **– 에러 유형 –**

**1. The server time zone value ‘KST’ is unrecognized or represents more than one time zone : mysql-connector-java 버전 5.1.X 이후 버전부터 KST 타임존을 인식하지 못하는 이슈**

: The server time zone value ‘KST’ is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.

– 해결 –

\1. config.xml 에서 url에 serverTimezone 추가

```
jdbc:mysql://ip:port/TestDB?characterEncoding=UTF-8&serverTimezone=UTC
```

jdbc:mysql://ip:port/TestDB?characterEncoding=UTF-8&serverTimezone=UTC

The reference to entity “serverTimezone” must end with the ‘;’ delimiter. 에러가 발생할 경우 & 대신에 &amp; 사용

```
jdbc:mysql://ip:port/TestDB?characterEncoding=UTF-8&amp;serverTimezone=UTC
```



 

\2. mysql 에서 타임존 추가

- 방법 1

Add in mysql config file in section **[mysqld]**

```
default_time_zone='+03:00'
```

And restart mysql server:

```
sudo service mysql restart
```

 

- 방법 2

mysql 서버의 타임존을 “**Asia/Seoul**” 로 지정 (http://blog.naver.com/wizardkyn/220852348757)

 

**2. CLIENT_PLUGIN_AUTH is required : SSL 미사용 에러**

: Error updating database. Cause: java.sql.SQLNonTransientConnectionException: CLIENT_PLUGIN_AUTH is required

– 해결 –

**mysql 버전이 낮으면 어쩔수 없음**

**mysql-connector-java 버전을 낮추거나 mysql 버전 업그레이드 진행 필요**

**1. url 에 파라미터 추가 (& 에러 발생시 & 로 대체)**

```
verifyServerCertificate=false&useSSL=false
```



#### 3. LOADING CLASS `COM.MYSQL.JDBC.DRIVER’. THIS IS DEPRECATED. THE NEW DRIVER CLASS IS `COM.MYSQL.CJ.JDBC.DRIVER’. THE DRIVER IS AUTOMATICALLY REGISTERED VIA THE SPI AND MANUAL LOADING OF THE DRIVER CLASS IS GENERALLY UNNECESSARY.

: driver 이름 변경 필요

– 해결 –

driver 명 수정

변경 전: com.mysql.jdbc.Driver

변경 후: com.mysql.cj.jdbc.Driver

 

**추가 이슈 확인**

https://bobswift.atlassian.net/wiki/spaces/SQL/pages/39256144/Data+source+configuration+-+problem+determination



- ID 입력받아 결과 레코드 출력

```java
package kr.co.koo.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcSelect {

	
	public static void main(String[] args) {
		
		String uid = "root";
		String upw = "mysql";
		String url = "jdbc:mysql://localhost:3306/jsp_practice?characterEncoding=UTF-8&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("ID를 조회해 주세요 : ");
		String id = sc.next();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, upw); // DB url, id, pw파라미터 connection 객체에 넣어줌 
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM member";
			rs = stmt.executeQuery(sql); // SQL 실행 결과 반환
			
			while(rs.next()) { // 한 라인씩 가져오기
				if(rs.getString("id").equals(id)) {
					System.out.println(rs.getString("id") + " ");
					System.out.println(rs.getString("pw") + " ");
					System.out.println(rs.getString("name") + " ");
					System.out.println(rs.getString("email") + " ");
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} 
		
	}
}

```



- 정보를 입력받아 해당 정보 DB insert

```java
package kr.co.koo.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class JdbcInsert {
	
	
	public static void main(String[] args) {
		String uid = "jsp";
		String upw = "jsp";
		
		// mysql 8.0 이상 서버URL 타임존 이슈
		String url = "jdbc:mysql://127.0.0.1:3306/jsp_practice?characterEncoding=UTF-8&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		Connection conn = null;
		Statement stmt = null;
		
		Scanner scan = new Scanner(System.in);
		
		String id = null;
		String pw = null;
		String name = null;
		String email = null;
		
		System.out.println("회원 정보 입력을 시작 합니다.");
		System.out.print("아이디 : ");
		id = scan.next();
		System.out.print("비밀번호 : ");
		pw = scan.next();
		System.out.print("이 름 : ");
		name = scan.next();
		System.out.print("이메일 : ");
		email = scan.next();
		
		
		try {
			System.out.println("DB접속");
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, uid, upw); // 컨넥션 객체 반환
			
			stmt = conn.createStatement(); // SQL실행가능 객체 반환
			
			String sql = "INSERT INTO member VALUES" + "('"+id+"','"+pw+"','"+name+"','"+email+"')";
			
			/*
			 INSERT, UPDATE, DELETE 문의 경우 executeUpdate() 메서드 실행 - 실행 성공시 성공한 쿼리 갯수 리턴, 실패시 0 리턴
			 SELECT의 경우 executeQuery() 싷행
			 */
			int resultNum = stmt.executeUpdate(sql);
			System.out.println("실행 쿼리갯수 : " + resultNum);
			
			if (resultNum!=0) {
				System.out.println("회원저장 성공");
			} else {
				System.out.println("회원저장 실패");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
				System.out.println("DB종료");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
}

```



#### PrepareStatement 객체

- INSERT , UPDATE , DELETE 문 수행 시 복잡한 sql문법을 피할 수 있다.

```jsp
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 	Connection conn = null;
 	PreparedStatement pstmt = null; // sql 문의 변수에 복잡한 콤마를 안쓰고 편하게 쓸수 있음 
 	ResultSet rs = null;
 	
 	String url = "jdbc:mysql://localhost:3306/jsp_practice?characterEncoding=UTF-8&serverTimezone=UTC";
 	String my_id = "root";
 	String my_pw = "mysql";
 	String driver = "com.mysql.cj.jdbc.Driver";
 	
 	String sql = "INSERT INTO member VALUES (?,?,?,?)";
 	
 	try {
 		Class.forName(driver); // 드라이버로드
 		conn = DriverManager.getConnection(url, my_id, my_pw);
 		pstmt = conn.prepareStatement(sql); // prepareStatement 객체를 만들때 sql문을 넣어줌 
 		
 		pstmt.setString(1, "chicken1234"); // setString, setInt
 		pstmt.setString(2, "4444");
 		pstmt.setString(3, "치킨");
 		pstmt.setString(4, "chicken@naver.com");
 		
 		pstmt.executeUpdate(); 
 		
 	} catch (Exception e ){
 		e.printStackTrace();
 	} finally {
 		try {
 			if(conn!=null) conn.close();
 			if(pstmt!=null) pstmt.close();
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>
```