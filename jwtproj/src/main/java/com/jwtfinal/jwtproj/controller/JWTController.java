package com.jwtfinal.jwtproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwtfinal.jwtproj.helper.JwtUtil;
import com.jwtfinal.jwtproj.model.JWTRequest;
import com.jwtfinal.jwtproj.model.JWTResponse;
import com.jwtfinal.jwtproj.services.CustomUserDetailsService;

@RestController
public class JWTController {
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest);
		try {
			
			this.authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		//fine area
		UserDetails userdetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		String token=this.jwtutil.generateToken(userdetails);
		System.out.println("JWT token:"+token);
		
		//{"token":"value"}
		
		return ResponseEntity.ok(new JWTResponse(token));
	}

}
