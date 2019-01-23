package com.eddy.springbootrestfuljwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eddy.springbootrestfuljwt.domains.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo,Long>{


	public UserInfo findByUsernameAndEnabled(String username, short enabled); 
	public UserInfo findByUsernameAndPasswordAndEnabled(String username, String password, short enabled);
	
}
