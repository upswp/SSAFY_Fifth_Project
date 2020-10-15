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

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.service.HouseDealServiceImpl;

@WebServlet("/deal")
public class HouseDealController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	/**
	 * 아파트 거래 정보를 위한 process
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String act = request.getParameter("act");
		
		if("deal-detail".equals(act)) {
			String path = "/index.jsp";
			String dong = request.getParameter("dong");
			String jibun = request.getParameter("jibun");
			String lat = request.getParameter("lat");
			String lng = request.getParameter("lng");
			List<HouseDealDto> deals = null;
			JSONArray arr = new JSONArray();
			try {
				deals = HouseDealServiceImpl.getHouseDealService().searchDealDetails(dong, jibun);
				for(HouseDealDto dto : deals) {
					JSONObject obj = new JSONObject();
					obj.put("no", dto.getNo());
					obj.put("dong", dto.getDong());
					obj.put("aptName", dto.getAptName());
					obj.put("code", dto.getCode());
					obj.put("dealAmount", dto.getDealAmount());
					obj.put("buildYear", dto.getBuildYear());
					obj.put("dealYear", dto.getDealYear());
					obj.put("area", dto.getArea());
					obj.put("floor", dto.getFloor());
					obj.put("jibun", dto.getJibun());
					obj.put("type", dto.getType());
					obj.put("lat", lat);
					obj.put("lng", lng);
					arr.add(obj);
					path = "/dealDetailDatas.jsp";
					request.setAttribute("dealDetailJson", arr.toJSONString());
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} 
			request.getRequestDispatcher(path).forward(request, response);
		}
		
	}
}
