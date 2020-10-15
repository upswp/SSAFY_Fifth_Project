package com.ssafy.happyhouse.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.LoginServiceImpl;


@WebServlet("/member")
public class HouseMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String root = request.getContextPath();
		String act = request.getParameter("act");
		if("mvlogin".equals(act)) {
			response.sendRedirect(root + "/user/login.jsp");
		} else if("mvjoin".equals(act)) {
			response.sendRedirect(root + "/user/join.jsp");
		} else if("login".equals(act)) {
			login(request, response);
		} else if("join".equals(act)) {
			join(request, response);
		} else if("logout".equals(act)) {
			logout(request, response);
		} else if("mvjoin".equals(act)) {
			response.sendRedirect(root + "/user/memberDetail.jsp");
		} else if("memberDetail".equals(act)) {
			memberdetail(request,response);
		} else if("memberUpdate".equals(act)) {
			memberupdate(request,response);
		} else if("memberDelete".equals(act)) {
			memberdelete(request, response);
		}
	}
	/**
	 * 회원정보 삭제 기능
	 * @param request
	 * @param response
	 */
	private void memberdelete(HttpServletRequest request, HttpServletResponse response) {
		
	}

	/**
	 * 회원정보 업데이트
	 * @param request
	 * @param response
	 */
	private void memberupdate(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
//		MemberDto memberDto = new MemberDto(userid,username,userpwd,email,address, address);
		
	}

	/**
	 * 회원 정보 확인
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void memberdetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		MemberDto memberDto = new MemberDto();
		memberDto.setUserid(userid);
		request.setAttribute("userid", userid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./user/memberDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 회원가입
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/index.jsp";
		
		//1.data get
		MemberDto memberDto = new MemberDto();
		memberDto.setUserid(request.getParameter("userid"));
		memberDto.setUsername(request.getParameter("username"));
		memberDto.setUserpwd(request.getParameter("userpwd"));
		memberDto.setEmail(request.getParameter("emailid") + "@" + request.getParameter("emaildomain"));
		memberDto.setAddress(request.getParameter("zipcode")+ " "+request.getParameter("address")+ " "+ request.getParameter("address_detail"));
		
		//2. 1번 data를 가지고 service(logic) call
		try {
			//회원가입 성공여부를 cnt로 판단.
			int cnt = LoginServiceImpl.getLoginService().join(memberDto);
			
			if (cnt != 0) { // 회원가입 성공
				path = "/user/joinok.jsp";
				request.setAttribute("registerinfo", memberDto);
			}else {			// 회원가입 실패 
				path = "/user/joinfail.jsp";
				request.setAttribute("msg", "서버에 문제가 있어 회원가입에 실패했습니다.<br>다음 기회에 다시 시도해 주십시오.");
				
			}
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원가입 처리 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
		
		//3. 2번 결과에 따라 응답페이지로 이동.
	}

	/**
	 * 로그아웃
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
		session.invalidate();
		System.out.println(request.getContextPath());
		response.sendRedirect(request.getContextPath());
	}


	/**
	 * 로그인
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		
		try {
			MemberDto memberDto = LoginServiceImpl.getLoginService().login(userid, userpwd);
			if(memberDto != null) {
//				session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				
//				cookie 설정
				String idsave = request.getParameter("idsave");
				if("saveok".equals(idsave)) {//아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("userid", userid);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
					response.addCookie(cookie);
				} else {//아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("userid".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
			} else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 로그인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 문제가 발생했습니다.");
			path = "/error/error.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
