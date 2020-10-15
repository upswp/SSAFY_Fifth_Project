package com.ssafy.happyhouse.model.dao;

import java.util.List;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapDao {

	/**
	 * 시도 리스트 반환
	 * @return
	 * @throws Exception
	 */
	List<SidoGugunCodeDto> getSido() throws Exception;
	/**
	 * 군구 리스트 반환
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
	 * 동에 있는 아파트 리스트 반환
	 * @param dong
	 * @return
	 * @throws Exception
	 */
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
}
