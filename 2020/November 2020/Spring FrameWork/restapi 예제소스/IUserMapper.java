package com.spring.mvc.user.repository;

import java.util.List;

import com.spring.mvc.user.model.UserVO;

// mvc-config 디비 설정파일에 마이바이트 기본 패키지 설정해야함
public interface IUserMapper {
	
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
