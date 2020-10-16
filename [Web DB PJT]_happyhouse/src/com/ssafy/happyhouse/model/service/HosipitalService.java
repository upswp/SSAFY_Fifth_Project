package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HosipitalDto;

public interface HosipitalService {

	/**
	 * 시도 단위 안심병원 리스트 반환
	 * @param sido
	 * @return
	 * @throws Exception
	 */
	List<HosipitalDto> getSido(String sido) throws Exception;
	
	/**
	 * 구군 단위 안심병원 리스트 반환
	 * @param gugun
	 * @return
	 * @throws Exception
	 */
	List<HosipitalDto> getGugunInSido(String gugun) throws Exception;
	
	
}
