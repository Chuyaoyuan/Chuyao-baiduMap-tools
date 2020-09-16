package com.cn.yaomvc.service;

import com.cn.yaomvc.pojo.User;

import java.util.ArrayList;

public interface IUserService {
	
	public User findUsreName(String id );

	public ArrayList<User> findUsreNames(String id );

	public User getUserselectBycheckUsername(User user);
	
	public ArrayList<User> findUsreNamesql(String id,String sql);
	
	public int insertSelective(User record);
	
	public int updateByPrimaryKeySelective(User record);
	
	public  int deleteByPrimaryKey(Long seq);
	
}
