package com.jafa.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jafa.Apptest;

public class MemberTest extends Apptest {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	@Ignore
	public void memberInsertTest() {
		String sql = "insert into member_tbl(userID,userPw,userName) values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "kku1031");
			pstmt.setString(2, passwordEncoder.encode("1234"));
			pstmt.setString(3, "강경구");
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void adminInsertTest() {
		String sql = "insert into member_tbl(userID,userPw,userName) values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "admin");
			pstmt.setString(2, passwordEncoder.encode("1234"));
			pstmt.setString(3, "관리자");
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	
}
