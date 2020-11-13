package com.spring.mvc.service;

import java.util.List;

import com.spring.mvc.user.model.UserVO;

public interface IUserService {
	
	// 회원 가입기능
	void register(UserVO user);

	// 회원 삭제기능 
	void delete(String account);
	
	// 회원정보 조회
	UserVO selectOne(String account);
	
	// 회원정보 수정
	void update(UserVO user);
	
	// 모든 회원정보 조회
	List<UserVO> selectAll();
	
}
