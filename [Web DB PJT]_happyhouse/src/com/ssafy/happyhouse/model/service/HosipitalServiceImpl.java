package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HosipitalDto;

public class HosipitalServiceImpl implements HosipitalService {

	/*==========Singleton ==========*/
	private static HosipitalService hosipitalService;
	
	public HosipitalServiceImpl() {}

	public static HosipitalService getHosipitalService() {
		if (hosipitalService == null) {
			hosipitalService = new HosipitalServiceImpl();
		}
		return hosipitalService;
	}
	/*==========Singleton ==========*/
	
	@Override
	public List<HosipitalDto> getSido(String sido) throws Exception {
		return null;
	}

	@Override
	public List<HosipitalDto> getGugunInSido(String gugun) throws Exception {
		return null;
	}

		
}
