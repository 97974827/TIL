package com.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.mvc.user.repository.IUserMapper;

import com.spring.mvc.user.model.UserVO;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserMapper mapper;
	
	@Override
	public void register(UserVO user) {
		mapper.register(user);
	}
	
	@Override
	public void delete(String account) {
		mapper.delete(account);	
	}
	
	@Override
	public UserVO selectOne(String account) {
		return mapper.selectOne(account); 
	}
	
	@Override
	public void update(UserVO user) {
		mapper.update(user);
	}
	
	@Override
	public List<UserVO> selectAll() {
		return mapper.selectAll();
	}
	
}
