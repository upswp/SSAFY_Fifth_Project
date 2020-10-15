package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;

public interface LoginService {
	/**
	 * 회원가입
	 * @param memberDto
	 * @return
	 * @throws Exception
	 */
	public int join(MemberDto memberDto) throws Exception;
	/**
	 * 로그인
	 * @param userid
	 * @param userpwd
	 * @return
	 * @throws Exception
	 */
	public MemberDto login(String userid, String userpwd) throws Exception;
	
}
