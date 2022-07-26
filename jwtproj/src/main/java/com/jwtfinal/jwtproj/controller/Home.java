package com.jwtfinal.jwtproj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
	
	@RequestMapping("/welcome")
	public String welcome() {
		String text = "this is the secured text";
		text+=" this is not for unauthorized users";
		return text;
	}
	
	@RequestMapping("/getusers")
	public String getUsers() {
		
		return "{\"name\":\"Abhishek\"}";
	}

}
