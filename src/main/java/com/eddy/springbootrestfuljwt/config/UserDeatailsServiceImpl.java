package com.eddy.springbootrestfuljwt.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eddy.springbootrestfuljwt.domains.UserInfo;
import com.eddy.springbootrestfuljwt.services.UserService;

@Service
public class UserDeatailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userService.getActiveUserByUsername(username);
		
		UserDetails userDetails = (UserDetails) new User(user.getUsername(),
				user.getPassword(), (Collection<? extends GrantedAuthority>) user.getRoles());
		System.out.println(userDetails);
		return userDetails;
	}
	
}