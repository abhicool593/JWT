package com.jwtfinal.jwtproj.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//if we want to connect with db then we have to call here our repository method
		if(username.equals("Abhishek")) {
			//username,password and arraylist is to store permission or authority or roles.
			return new User("Abhishek","Abhishek123",new ArrayList<>());
			
		}else {
			throw new UsernameNotFoundException("user not found");
		}
	}
	
	

}
