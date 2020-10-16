package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.ClinicDto;

public class ClinicDaoImpl implements ClinicDao{

	private static ClinicDao clinicDao = new ClinicDaoImpl();
	private ClinicDaoImpl() {
		
	}
	public static ClinicDao getClinicDao() {
		return clinicDao;
	}
	
	@Override
	public List<ClinicDto> searchClinics(String gugun) throws Exception {

		return null;
	}

}
