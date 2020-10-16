package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.ClinicDto;

public interface ClinicService {

	List<ClinicDto> searchClinics(String gugun) throws Exception;
	
}
