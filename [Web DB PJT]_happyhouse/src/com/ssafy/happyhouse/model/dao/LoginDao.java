package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.MemberDto;

public interface LoginDao {
	/**
	 * 회원가입
	 * @param memberDto 유저정보 DTO
	 * @return
	 */
	public int join(MemberDto memberDto);
	
	
	/**
	 * 로그인
	 * @param userid 유저 아이디
	 * @param userpwd 유저 페스워드
	 * @return
	 * @throws SQLException
	 */
	public MemberDto login(String userid, String userpwd) throws SQLException;
	
}
