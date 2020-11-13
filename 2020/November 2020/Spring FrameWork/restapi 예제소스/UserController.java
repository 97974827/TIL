package com.spring.mvc.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	// 회원가입 요청 처리 
	// RestAPI에서 Insert -> POST
	//@RequestMapping(value="/", method=RequestMethod.POST)  // 구버전 방식
	@PostMapping("/") // 신버전 방식
	public String register(@RequestBody UserVO user) { // 리퀘스트바디 파라미터 붙여야 전송가능 
		System.out.println("/user POST 요청발생");
		System.out.println("param : " + user);
		
		service.register(user);
		return "joinSuccess";
	}
	
	// 아이디 중복확인 요청 처리
	@PostMapping("/checkId")
	public String checkId(@RequestBody String account) {
		System.out.println("/user/checkId POST 발생");
		System.out.println("param : " + account);
		String result = null; 
		
		Integer checkNum = service.checkId(account);
		if (checkNum == 1) {
			System.out.println("아이디 중복");
			result = "NO";
		} else {
			System.out.println("아이디 사용가능!");
			result = "OK";			
		}
		return result;
	}
	
	// 로그인 요청 처리
	/*
	 # 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를 조회해서 불러온다음 값비교를 통해
	 1. 아이디가 없을 경우 클라이언트측으로 문자열 "idFail" 전송
	 2. 비밀번호가 틀렸을 경우 문자열 "pwFail" 전송
	 3. 로그인 성공시 문자열 "loginSuccess" 전송
	 */
	@PostMapping("/loginCheck")
	public String loginCheck(@RequestBody UserVO inputData, HttpSession session/*HttpServletRequest request*/) {
		
		// 서버에서 세션객체를 얻는방법 
		/*
		 1. HttpServletRequest 객체사용
		 * */
		// HttpSession session = request.getSession();
		
		String result = null;
		System.out.println("/user/loginCheck POST 발생");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		UserVO dbData = service.selectOne(inputData.getAccount());
		
		if(dbData == null) {
			System.out.println("아이디가 없음");
			result = "idFail";
		} else {
			if(dbData.getAccount().equals(inputData.getAccount())) { 
				System.out.println("아이디 존재");
				if(encoder.matches(inputData.getPassword(), dbData.getPassword())) {
					session.setAttribute("login", dbData); // 로그인 성공 시 세션에 저장
					System.out.println("비밀번호 맞음");
					result = "loginSuccess";
				} else {
					System.out.println("비밀번호 틀림");
					result = "pwFail";
				}
			}
		}
		
		return result;
	}
	
	@GetMapping("/logout")
	public void logout(HttpSession session, HttpServletResponse response) {
		UserVO user = (UserVO) session.getAttribute("login");
		
		if(user!=null) {
			session.removeAttribute("login");
			session.invalidate();
//			response.sendRedirect("/");
		}
		
		//return new ModelAndView("redirect:/");
	}
	
	// 회원가입 삭제 처리
	// RestAPI에서 Delete -> DELETE
	// @RequestMapping(value="/", method=RequestMethod.DELETE)  // 구버전 방식
	@DeleteMapping("/{account}")
	public String delete(@PathVariable String account) { // PathVariable : 경로에서 데이터를 읽겠다는의미
		System.out.println("/user DELETE 요청발생");
		
		service.delete(account);
		return "delSuccess";
	}
	
	// 회원 정보 조회 
	@RequestMapping(value="/{account}", method=RequestMethod.GET)
	public UserVO selectOne(@PathVariable String account) {
		System.out.println("/selectOne GET 요청");
		System.out.println("account : " + account);
		
		return service.selectOne(account);
	}
	
	// 회원조회 
	@GetMapping("/")
	public List<UserVO> selectAll(){
		return service.selectAll();
	}
	
	// 회원 수정
	@PutMapping("/{account}/{password}/{name}")
	// @RequestMapping(value="/", method=RequestMethod.PUT)
	public String update(/*@RequestBody UserVO user*/ @PathVariable("account") String account, 
													  @PathVariable("password") String password,
													  @PathVariable("name") String name) {
		System.out.println("/PUT 요청  : " + account + ", " + password + ", " + name);
		service.update(account,password,name);
		return "UpdateSuc";
	}
	
}
