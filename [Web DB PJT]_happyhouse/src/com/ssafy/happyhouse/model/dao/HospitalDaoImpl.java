package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.util.DBUtil;

public class HospitalDaoImpl implements HospitalDao {
	
	private static HospitalDao hospitalDao = new HospitalDaoImpl();
	private HospitalDaoImpl() {
		
	}
	public HospitalDao getHospitalDao() {
		return hospitalDao;
	}
	
	@Override
	public List<HospitalDto> searchHospitals(String gugun) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HospitalDto> clinics = new ArrayList<HospitalDto>();
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT h.date, h.sido, h.gugun, h.name, h.address, h.type, h.tel "
					+ " from guguncode g join hospital h "
					+ " on g.gugun_name = h.gugun "
					+ " where substr(g.gugun_code, 1, 5) = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gugun);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HospitalDto dto = new HospitalDto();
				dto.setDate(rs.getString("date"));
				dto.setSido(rs.getString("sido"));
				dto.setGugun(rs.getString("gugun"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setType(rs.getString("type"));
				dto.setTel(rs.getString("tel"));
				clinics.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return clinics;
	}

}
