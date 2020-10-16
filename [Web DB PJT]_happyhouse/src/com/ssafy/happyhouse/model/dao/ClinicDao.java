package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.ClinicDto;

public interface ClinicDao {

	List<ClinicDto> searchClinics(String gugun) throws Exception;
	
}
