package com.cn.yaomvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.yaomvc.dao.UserMapper;
import com.cn.yaomvc.pojo.User;
import com.cn.yaomvc.service.IUserService;


@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userDao;

	@Override
	public User findUsreName(String id) {
		// TODO Auto-generated method stub
		return this.userDao.findUsreName(id);
	}
	@Override
	public ArrayList<User> findUsreNames(String id) {
		// TODO Auto-generated method stub
		return this.userDao.findUsreNames(id);
	}
	@Override
	public ArrayList<User> findUsreNamesql(String id,String sql) {
		// TODO Auto-generated method stub
		return this.userDao.findUsreNamesql(id,sql);
	}
	
	@Override
	public User getUserselectBycheckUsername(User user) {
		// TODO Auto-generated method stub
		return this.userDao.selectBycheckUsername(user);
	}
	@Override
	public int insertSelective(User record){
		// TODO Auto-generated method stub
		return this.userDao.insertSelective(record);
	}
	@Override
	public int updateByPrimaryKeySelective(User record){
		// TODO Auto-generated method stub
		return this.userDao.updateByPrimaryKeySelective(record);
	}
	@Override
	public  int deleteByPrimaryKey(Long seq){
		// TODO Auto-generated method stub
		return this.userDao.deleteByPrimaryKey(seq);
	}
}
