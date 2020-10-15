package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.util.DBUtil;

public class HouseDealDaoImpl implements HouseDealDao{

	private static HouseDealDao houseDealDao = new HouseDealDaoImpl();
	private HouseDealDaoImpl() {
		
	}
	public static HouseDealDao getHouseDealDao() {
		return houseDealDao;
	}

	@Override
	public List<HouseDealDto> searchDealDetails(String dong, String jibun) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseDealDto> deals = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT no, dong, AptName, code, dealAmount, buildYear, dealYear, area, floor, jibun, type "
					+ " from housedeal WHERE dong = ? AND jibun = ? \n");
			sql.append("ORDER BY AptName");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			pstmt.setString(2, jibun);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseDealDto dto = new HouseDealDto();
				dto.setNo(rs.getInt("no"));
				dto.setDong(rs.getString("dong"));
				dto.setAptName(rs.getString("AptName"));
				dto.setCode(rs.getString("code"));
				dto.setDealAmount(rs.getString("dealAmount"));
				dto.setBuildYear(rs.getString("buildYear"));
				dto.setDealYear(rs.getString("dealYear"));
				dto.setArea(rs.getString("area"));
				dto.setFloor(rs.getString("floor"));
				dto.setJibun(rs.getString("jibun"));
				dto.setType(rs.getString("type"));
				deals.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return deals;
	}

}
