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

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.service.ClinicServiceImpl;

@WebServlet("/clinic")
public class ClinicController extends HttpServlet {
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
		String gugun = request.getParameter("gugun");
		String path = "/index.jsp";									/////////////
		if("showClinics".equals(act)) {									////////////			
			try {
				List<ClinicDto> clinics = ClinicServiceImpl.getClinicService().searchClinics(gugun);
				JSONArray arr = new JSONArray();
				for(ClinicDto dto : clinics) {
					JSONObject obj = new JSONObject();
					obj.put("date", dto.getDate());
					obj.put("extract", dto.getExtract());
					obj.put("sido", dto.getSido());
					obj.put("gugun", dto.getGugun());
					obj.put("name", dto.getName());
					obj.put("address", dto.getAddress());
					obj.put("weekOp", dto.getWeekOp());
					obj.put("satOp", dto.getSatOp());
					obj.put("sunOp", dto.getSunOp());
					obj.put("tel", dto.getTel());
					arr.add(obj);
					path = "/clinics.jsp";

					request.setAttribute("aptJson", arr.toJSONString());
				}
				
				request.getRequestDispatcher(path).forward(request, response);
				
			} catch (Exception e) {
				System.out.println(e);
			}	
		}
		
		
	}

}
