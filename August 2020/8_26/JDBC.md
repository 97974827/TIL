### JDBC 

- [참고 - Java JDBC 사용하기](https://victorydntmd.tistory.com/145)

- **Jar file**

  - 여러 클래스들을 모아 놓은 파일 명시 

- **File Name **

  - **mysql-connector-java-*-bin.jar**

    

#### Windows

- 참고 - 이클립스 설정 https://blog.naver.com/50after/220912861796 

- **Linux - jar 파일 똑같이 받아서 설치해주면 됨 `jar xvf 파일명`** 

  

1.  **JDBC jar 파일 라이브러리 필요**	
   - 운영체제에 맞게 다운	
   - 다운로드 파일 
     - https://dev.mysql.com/downloads/connector/j/#downloads



2. **jdk-jre-lib 파일에 jar복사** 
3. **이클립스 - build path jar 파일 등록** 
4. **서버 - my.cnf 설정** 

```shell
# MariaDB-specific config file.
# Read by /etc/mysql/my.cnf

[client]
# Default is Latin1, if you need UTF-8 set this (also in server section)
#default-character-set = utf8

[mysqld]
#
# * Character sets
#
# Default is Latin1, if you need UTF-8 set all this (also in client section)
#
#character-set-server  = utf8
collation-server      = utf8_general_ci
character_set_server   = utf8
skip-character-set-client-handshake
lower_case_table_names = 1
#default-time-zone = Asia/Seoul
#defalut-time-zone = '+9:00'
#collation_server       = utf8_general_ci
# Import all .cnf files from configuration directory
!includedir /etc/mysql/mariadb.conf.d/

```



5. **connection source**
   - Connector 5.x 이상 부터 The server time zone value 에러 나옴 
   - 호스트 url 부분에 마지막라인 추가 `?serverTimezone=UTC`
   - 테스트 코드는 카드 테이블의 데이터 가져옴 

```java
//package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Mysqlconnection {
	
	public static void main(String[] args) {
		Connection conn = null; // 컨넥션 객체  
		Statement stmt = null;  // 쿼리 실행 객체 
		ResultSet rs = null;    // 쿼리 결과 객체 

		//String url = "jdbc:mysql://glstest.iptime.org:30002/glstech?serverTimezone=UTC"; // host
		String url = "jdbc:mysql://localhost:3306/glstech?serverTimezone=UTC";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 1. 드라이버 로딩 
			
			conn = DriverManager.getConnection(url, "pi", "1234"); // 2. 연결(url, user, password)
			System.out.println("연결성공");
			
			stmt = conn.createStatement();     // 3. 쿼리 수행을 위한 Statement 객체생성
			
			String sql = "select * from card"; // 4. SQL 쿼리작성 
			
			rs = stmt.executeQuery(sql);       // 5. 쿼리수행
			
			while(rs.next()) { // 데이터 마지막 까지반복 
				String no = rs.getString(1); // param : Column index (1부터 시작)   
				String card_num = rs.getString(2);
				String total_mny = rs.getString(3);
				String current_mny = rs.getString(4);
				String current_bonus = rs.getString(5);
				String charge_money = rs.getString(6);
				String before_mny = rs.getString(7);
				String card_price = rs.getString(8);
				String kind = rs.getString(9);
				String datetime = rs.getString(10);
				String state = rs.getString(11);
				String reader_type = rs.getString(12);
				
				System.out.println(no + ", " + card_num + ", " + total_mny + ", " + current_mny + ", " + current_bonus
						+ ", " + charge_money + ", " + before_mny + ", " + card_price + ", " + kind + ", " + datetime + ", "
						+ state + ", " + reader_type);
			}
			
			
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch(SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}	

```



