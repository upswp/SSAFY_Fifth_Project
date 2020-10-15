package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;

public interface HouseDealDao {

	/**
	 * 상세 거래정보 리스트 반환
	 * @param dong
	 * @param jibun
	 * @return
	 * @throws Exception
	 */
	List<HouseDealDto> searchDealDetails(String dong, String jibun) throws Exception;
	
}
