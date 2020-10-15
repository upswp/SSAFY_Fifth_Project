package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.dao.HouseDealDaoImpl;

public class HouseDealServiceImpl implements HouseDealService{

	private static HouseDealService houseDealService = new HouseDealServiceImpl();
	private HouseDealServiceImpl() {
		
	}
	public static HouseDealService getHouseDealService() {
		return houseDealService;
	}
	@Override
	public List<HouseDealDto> searchDealDetails(String dong, String jibun) throws Exception {
		return HouseDealDaoImpl.getHouseDealDao().searchDealDetails(dong, jibun);
	}

}
