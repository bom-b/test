package com.shopping.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SuperDao {
	
	protected Connection conn = null; //접속 객체
	
	 //단계2 : 접속객체 구하기
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "shopping";
		String password = "oracle";
		
		try {
			this.conn = DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	 //단계1 : 생성자에서 드라이버 로딩하기
	public SuperDao() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
		} catch (Exception e) {
			
		}
	}
}
