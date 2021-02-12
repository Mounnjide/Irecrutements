package com.irecrutements.spring.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping(value="/login")
	public String login(){
//		System.out.println("login");
		
		return "login";
	}
	
	@RequestMapping(value="/403")
	public String accessDenied(){
//		System.out.println("403");
		return "403";
	}
	
	
}
