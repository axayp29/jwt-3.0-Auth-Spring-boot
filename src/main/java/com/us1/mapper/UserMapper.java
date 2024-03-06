package com.us1.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.us1.model.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from user")
	List<User> findAllUsers();
	
	@Select("select * from user where username=#{username}")
	User findByUsername(String username);

}
