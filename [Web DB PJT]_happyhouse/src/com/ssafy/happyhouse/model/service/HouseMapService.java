package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapService {
	
	/**
	 * 시도 리스트 반환
	 * @return
	 * @throws Exception
	 */
	List<SidoGugunCodeDto> getSido() throws Exception;
	/**
	 * 구군 리스트 반환
	 * @param sido
	 * @return
	 * @throws Exception
	 */
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	/**
	 * 구군의 동 리스트 반환
	 * @param gugun
	 * @return
	 * @throws Exception
	 */
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	/**
	 * 동의 아파트 리스트 반환
	 * @param dong
	 * @return
	 * @throws Exception
	 */
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
}
