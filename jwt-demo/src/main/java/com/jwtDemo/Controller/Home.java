package com.jwtDemo.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Home {
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "Hello";
	}

}
