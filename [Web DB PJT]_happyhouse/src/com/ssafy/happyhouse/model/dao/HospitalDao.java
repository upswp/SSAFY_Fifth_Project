package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;

public interface HospitalDao {

	List<HospitalDto> searchHospitals(String gugun) throws Exception;
	
}
