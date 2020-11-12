package com.Enterprise.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Enterprise.dao.CustomerDao;


public class DbUtils {
	
	 private String dbUrl="jdbc:mysql://localhost:3306"
	 		+ "/db_enterprise?useUnicode=true&characterEncoding=utf8";
	 private String user="root";
	 private String password="root";
	 private String jdbcDrive="com.mysql.jdbc.Driver";
	 
	 private Connection conn;
	 
	 
	 public  Connection getConn() {
		try {
			Class.forName(jdbcDrive);
			conn = DriverManager.getConnection(dbUrl, user, password);
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 return conn;
	 }
	 
	 public static void closeCon(Connection con) {
		 if(con!=null) {
			 try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				con=null;
			}
		 }
	 }
	 
}
