package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;
import com.ssafy.happyhouse.model.service.HouseMapServiceImpl;
import com.ssafy.model.GuestBookDto;
import com.ssafy.util.PageNavigation;

@WebServlet("/map")
public class HouseMapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	/**
	 * 아파트 정보를 위한 process
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String act = request.getParameter("act");
		if("sido".equals(act)) {
			PrintWriter out = response.getWriter();
			List<SidoGugunCodeDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = HouseMapServiceImpl.getHouseMapService().getSido();
				for(SidoGugunCodeDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("sido_code", dto.getSidoCode());
					obj.put("sido_name", dto.getSidoName());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//sido
		else if("gugun".equals(act)) {
			String sido = request.getParameter("sido");
			PrintWriter out = response.getWriter();
			List<SidoGugunCodeDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = HouseMapServiceImpl.getHouseMapService().getGugunInSido(sido);
				for(SidoGugunCodeDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("gugun_code", dto.getGugunCode());
					obj.put("gugun_name", dto.getGugunName());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//gugun
		else if("dong".equals(act)) {
			String gugun = request.getParameter("gugun");
			PrintWriter out = response.getWriter();
			List<HouseInfoDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = HouseMapServiceImpl.getHouseMapService().getDongInGugun(gugun);
				for(HouseInfoDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("code", dto.getCode());
					obj.put("dong", dto.getDong());
					arr.add(obj);
				}
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} finally {
				out.print(arr.toJSONString());
				out.close();
			}
		}//dong
		else if("apt".equals(act)) {
			String path = "/index.jsp";
			String dong = request.getParameter("dong");
			String gugun = request.getParameter("gugun");

			List<HouseInfoDto> list = null;
			JSONArray arr = new JSONArray();
			try {
				list = HouseMapServiceImpl.getHouseMapService().getAptInDong(dong);

				for(HouseInfoDto dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("no", dto.getNo());
					obj.put("dong", dto.getDong());
					obj.put("aptName", dto.getAptName());
					obj.put("code", dto.getCode());
					obj.put("jibun", dto.getJibun());
					obj.put("lat", dto.getLat());
					obj.put("lng", dto.getLng());
					arr.add(obj);
					path = "/houseDatas.jsp";

					request.setAttribute("aptJson", arr.toJSONString());
				}
				
				request.setAttribute("gugun", gugun);
				
			} catch (Exception e) {
				arr = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("message_code", "-1");
				arr.add(obj);
				e.printStackTrace();
			} 
			request.getRequestDispatcher(path).forward(request, response);
		}//dong
		
		
		else if ("listArticle".equals(act)) {

			String path = "/index.jsp";
			int currentPage = Integer.parseInt(request.getParameter("pg")); // 보고자하는 page번호
			String spp = request.getParameter("spp");
			int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);//sizePerPage
			String key = request.getParameter("key");//"" 검색조건
			String word = request.getParameter("word");//"" 검색어
			try {
				List<HouseInfoDto> list = HouseMapService.listArticle(currentPage, sizePerPage, key, word);
				PageNavigation pageNavigation = HouseMapService.makePageNavigation(currentPage, sizePerPage, key, word);
				request.setAttribute("articles", list);
				request.setAttribute("navigation", pageNavigation);
				path = "/guestbook/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글목록을 얻어오는 중 문제가 발생했습니다.");
				path = "/error/error.jsp";
			}
			request.getRequestDispatcher(path).forward(request, response);
		
		}
	}//process
}