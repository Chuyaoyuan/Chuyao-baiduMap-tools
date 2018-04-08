package com.cn.yaomvc.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.cn.yaomvc.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long seq);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long seq);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectBycheckUsername(User user);
    
    User findUsreName(@Param("id")String id);

    ArrayList<User> findUsreNames(@Param("id")String id);
    
    ArrayList<User> findUsreNamesql(@Param("id")String id,@Param("sql")String sql);
}