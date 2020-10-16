package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.util.DBUtil;

public class ClinicDaoImpl implements ClinicDao{

	private static ClinicDao clinicDao = new ClinicDaoImpl();
	private ClinicDaoImpl() {
		
	}
	public static ClinicDao getClinicDao() {
		return clinicDao;
	}
	
	@Override
	public List<ClinicDto> searchClinics(String gugun) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ClinicDto> clinics = new ArrayList<ClinicDto>();
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c.date, c.extract, c.sido, c.gugun, c.name, c.address, c.weekOp, c.satOp, c.sunOp, c.tel "
					+ " from guguncode g join clinic c "
					+ " on g.gugun_name = c.gugun "
					+ " where substr(g.gugun_code, 1, 5) = ? ");

			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gugun);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ClinicDto dto = new ClinicDto();
				dto.setDate(rs.getString("date"));
				dto.setExtract(rs.getString("extract"));
				System.out.println("aaaaa");
				dto.setSido(rs.getString("sido"));
				dto.setGugun(rs.getString("gugun"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setWeekOp(rs.getString("weekOp"));
				dto.setSatOp(rs.getString("satOp"));
				dto.setSunOp(rs.getString("sunOp"));
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
