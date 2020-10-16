package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;

public interface HospitalService {
	
	/**
	 * 구군 단위 안심병원 리스트 반환
	 * @param gugun
	 * @return
	 * @throws Exception
	 */
	List<HospitalDto> searchHospitals(String gugun) throws Exception;
	
	
}
