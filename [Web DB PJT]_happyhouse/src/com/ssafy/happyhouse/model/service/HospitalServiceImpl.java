package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.dao.HospitalDaoImpl;

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

		return HospitalDaoImpl.getHospitalDao().searchHospitals(gugun);
	}
	
}
