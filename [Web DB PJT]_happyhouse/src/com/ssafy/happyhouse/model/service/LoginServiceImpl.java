package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.dao.LoginDaoImpl;

public class LoginServiceImpl implements LoginService {
	/*Singleton Pattern*/
	private static  LoginService loginService;
	private LoginServiceImpl() {}
	public static LoginService getLoginService() {
		if (loginService == null) {
			loginService = new LoginServiceImpl();
		}
		return loginService;
	}
	/*Singleton Pattern*/
	/**
	 * login
	 */
	@Override
	public MemberDto login(String userid, String userpwd) throws Exception {
		if(userid == null || userpwd == null)
			return null;
		return LoginDaoImpl.getLoginDao().login(userid, userpwd);
	}

	/**
	 * join
	 */
	@Override
	public int join(MemberDto memberDto) throws Exception {
		return LoginDaoImpl.getLoginDao().join(memberDto);
	}

}
