package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.dao.ClinicDao;
import com.ssafy.happyhouse.model.dao.ClinicDaoImpl;

public class ClinicServiceImpl implements ClinicService{

	private static ClinicService clinicService = new ClinicServiceImpl();
	private ClinicServiceImpl() {
		
	}
	public static ClinicService getClinicService() {
		return clinicService;
	}
	
	public List<ClinicDto> searchClinics(String gugun) throws Exception {
		return ClinicDaoImpl.getClinicDao().searchClinics(gugun);
	}

}
