package net.utility;

import java.sql.*;

import org.springframework.stereotype.Component;

@Component
public class DBClose {
	
	public DBClose() {
		System.out.println("Start DBOpen Object");
	}

	public void close(Connection con) {
		try {
			if (con != null) con.close();
		} 
		catch (Exception e) {
			System.out.println("** Connection Close Error ** \n"+e);
		}
	}

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null) pstmt.close();
		} 
		catch (Exception e) {
			System.out.println("** Connection Close Error ** \n"+e);
		}
		close(con);
	}

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
		}
		catch (Exception e) {
			System.out.println("** ResultSet Close Error ** \n"+e);
		}
		close(con,pstmt);
	}

}
