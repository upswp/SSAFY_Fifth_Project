package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;

public class HospitalServiceImpl implements HospitalService {

	/*==========Singleton ==========*/
	private static HospitalService hospitalService;
	
	private HospitalServiceImpl() {}

	public static HospitalService getHospitalService() {
		if (hospitalService == null) {
			hospitalService = new HospitalServiceImpl();
		}
		return hospitalService;
	}
	/*==========Singleton ==========*/

	@Override
	public List<HospitalDto> searchHospitals(String gugun) throws Exception {

		return HospitalServiceImpl.getHospitalService().searchHospitals(gugun);
	}
	
}
