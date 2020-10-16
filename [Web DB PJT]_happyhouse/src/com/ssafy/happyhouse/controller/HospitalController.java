package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.service.HospitalServiceImpl;

@WebServlet("/hospital")
public class HospitalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String act = request.getParameter("act");

		if("showHospitals".equals(act)) {							
		
			String gugun = request.getParameter("gugun");
			List<HospitalDto> hospitals = null;
			String path = "/index.jsp";	
			JSONArray arr = new JSONArray();
			
			try {
				hospitals = HospitalServiceImpl.getHospitalService().searchHospitals(gugun);
				
				for(HospitalDto dto : hospitals) {
					JSONObject obj = new JSONObject();
					obj.put("date", dto.getDate());
					obj.put("sido", dto.getSido());
					obj.put("gugun", dto.getGugun());
					obj.put("name", dto.getName());
					obj.put("address", dto.getAddress());
					obj.put("type", dto.getType());
					obj.put("tel", dto.getTel());
					arr.add(obj);
					path = "/hospitals.jsp";

					request.setAttribute("hospitalJson", arr.toJSONString());
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			request.getRequestDispatcher(path).forward(request, response);
		}
		
		
	}

}
