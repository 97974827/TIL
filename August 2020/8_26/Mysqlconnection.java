//package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Mysqlconnection {
	
	public static void main(String[] args) {
		Connection conn = null; // connection object  
		Statement stmt = null;  // query execute object
		ResultSet rs = null;    // query result object

		//String url = "jdbc:mysql://glstest.iptime.org:30002/glstech?serverTimezone=UTC"; // host
		String url = "jdbc:mysql://localhost:3306/glstech?serverTimezone=UTC";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 1. driver loading 
			
			conn = DriverManager.getConnection(url, "pi", "1234"); // 2. connect(url, user, password)
			System.out.println("conection success");
			
			stmt = conn.createStatement();     // 3. to query execute Statement object add
			
			String sql = "select * from card"; // 4. SQL query 
			
			rs = stmt.executeQuery(sql);       // 5. query execute
			
			while(rs.next()) { 
				String no = rs.getString(1); // param : Column index (1)   
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
			System.out.println("Driver Loading Failed");
		} catch(SQLException e) {
			System.out.println("Error : " + e);
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
