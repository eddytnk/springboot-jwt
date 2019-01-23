package com.eddy.springbootrestfuljwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eddy.springbootrestfuljwt.domains.UserInfo;
import com.eddy.springbootrestfuljwt.repositories.UserInfoRepo;

@Service
public class UserService {
	
	@Autowired
	UserInfoRepo userRepos;

	public List<UserInfo> getUsers(){
		return userRepos.findAll();
	}
	
	public Optional<UserInfo> getUser(Long id){
		return userRepos.findById(id);
	}
	
	
	public boolean isUserExist(UserInfo user) {
		Optional<UserInfo> u = this.getUser(user.getId());
		if(u.isPresent())return true;
		return false;
	}
	
	public UserInfo create(UserInfo user) {
		UserInfo u = userRepos.save(user);
		return u;
	}

	public void deleteUserById(Long id) {
		userRepos.deleteById(id);
	}
	
	
	public UserInfo getActiveUserByUsername(String username) {
		short enabled = 1;
		UserInfo user  = userRepos.findByUsernameAndEnabled(username, enabled);
		return user;
	}
	public UserInfo getActiveUserByUsernameAndPassword(String username, String password) {
		short enabled = 1;
		UserInfo user  = userRepos.findByUsernameAndPasswordAndEnabled(username, password, enabled);
		return user;
	}

	public UserInfo update(UserInfo user) {
		UserInfo u = userRepos.save(user);
		return u;
	}
}
