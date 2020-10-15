package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.util.DBUtil;

public class LoginDaoImpl implements LoginDao {
	/*Singleton Pattern*/
	private static LoginDao loginDao;
	private LoginDaoImpl() {}
	public static LoginDao getLoginDao() {
		if (loginDao == null) {
			loginDao = new LoginDaoImpl();
		}
		return loginDao;
	}
	/*Singleton Pattern*/
	
	/**
	 * login
	 */
	@Override
	public MemberDto login(String userid, String userpwd) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select username, userid, email \n");
			sql.append("from member \n");
			sql.append("where userid = ? and userpwd = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserid(rs.getString("userid"));
				memberDto.setUsername(rs.getString("username"));
				memberDto.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			memberDto = null;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return memberDto;
	}

	/**
	 * 회원가입
	 */
	@Override
	public int join(MemberDto memberDto) {
		int cnt = 0;
		/*1단계*/
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			/*2단계*/
			conn = DBUtil.getConnection();
			/*3단계*/
			StringBuilder sql = new StringBuilder();
			sql.append("insert into member (userid, username, userpwd, email, address, joindate)\n");
			sql.append("values(?,?,?,?,?,now())\n");
			/*4단계*/
			psmt = conn.prepareStatement(sql.toString());
			
			int i = 0;
			psmt.setString(++i, memberDto.getUserid());
			psmt.setString(++i, memberDto.getUsername());
			psmt.setString(++i, memberDto.getUserpwd());
			psmt.setString(++i, memberDto.getEmail());
			psmt.setString(++i, memberDto.getAddress());
			
			/*5단계*/
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			/*6단계*/
			DBUtil.close(psmt);
			DBUtil.close(conn);
		}
		
		return cnt;
	}

}
