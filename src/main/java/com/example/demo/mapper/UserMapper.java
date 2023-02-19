package com.example.demo.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {

	 public List<User> selectAll();
	 
	 public void Signup_User(User user);
	 
	 public List<User> Signin_User(String name,String password);
}
